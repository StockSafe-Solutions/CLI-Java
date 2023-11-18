package com.stocksafe;

import com.stocksafe.dao.FuncionarioDao;
import com.stocksafe.dao.ServidorDao;
import com.stocksafe.objeto.Servidor;
import com.github.britooo.looca.api.core.Looca;
import com.stocksafe.conexao.Conexao;
import com.stocksafe.dao.MaquinaDao;
import com.stocksafe.dao.OpcoesDao;
import com.stocksafe.objeto.Funcionario;
import com.stocksafe.objeto.Maquina;
import com.stocksafe.objeto.Menu;
import com.stocksafe.objeto.Opcoes;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Timer timer = new Timer();

        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        Looca looca = new Looca();

        Scanner leitor = new Scanner(System.in);
        Scanner leitorString = new Scanner(System.in);

        FuncionarioDao funcionarioDao = new FuncionarioDao(con);
        Funcionario funcionario = new Funcionario();

        OpcoesDao opcoesDao = new OpcoesDao();
        Opcoes opcoes = new Opcoes();

        Maquina maquina = new Maquina(looca);
        MaquinaDao maquinaDao = new MaquinaDao(con);

        ServidorDao servidorDao = new ServidorDao(con);
        Servidor servidor;

        Menu menu = new Menu(leitor, leitorString, funcionarioDao, opcoesDao, maquina);

        if (opcoesDao.carregarOpcoes() == null){
            opcoesDao.criarOpcoes();
        }

        opcoes = opcoesDao.carregarOpcoes();

        Boolean isLogado = false;

        while (!isLogado) {
            System.out.println("Faça seu login");

            System.out.println("Digite o seu email: ");
            String email = leitorString.nextLine();

            System.out.println("Digite a sua senha: ");
            String senha = leitorString.nextLine();

            funcionario = new Funcionario(email, senha);

            List<Funcionario> funcionarioCadastrado = funcionarioDao.getFuncionarioPorLogin(funcionario);

            if (!funcionarioCadastrado.isEmpty()) {
                funcionario = funcionarioCadastrado.get(0);
                isLogado = true;

            } else {
                System.out.println("Usuário inválido");
            }
        }

        while (servidorDao.selecionarServidor(opcoes).isEmpty()) {
            opcoes.setCodigo(leitorString);

            opcoesDao.alterarOpcoes(opcoes);
        }

        servidor = servidorDao.selecionarServidor(opcoes).get(0);

        servidorDao.autenticarServidor(servidor, funcionario);

        TimerTask inserirDados = new TimerTask() {
            @Override
            public void run() {
                maquinaDao.inserirDadosPacote(servidor, maquina);
                maquinaDao.inserirDadosCpu(servidor, maquina);
                maquinaDao.inserirDadosRam(servidor, maquina);
                maquinaDao.inserirDadosTransferencia(servidor, maquina);
            }
        };

        do {

            // TODO: 18/11/2023 Fazer o timertask rodar de segundo plano
            timer.schedule(inserirDados, 0, 10000);

            menu.exibirMenuInicial();
            Integer opcaoEscolhida = menu.solicitarOpcaoInt();

            switch (opcaoEscolhida) {
                case 1:
                    menu.verificarDados();
                    break;
                case 2:
                    menu.listarProcessos();
                    break;
                case 3:
                    menu.mudarOpcoes();
                    break;
                case 0:
                    menu.exibirMensagemSair();
                    break;
                default:
                    menu.opcaoInvalida();
            }

            servidorDao.atualizarArmazenamento(servidor, maquina.getArmazenamentoTotal(), maquina.getArmazenamentoUsado());
        } while (true);
    }
}