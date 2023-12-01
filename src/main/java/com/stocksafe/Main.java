package com.stocksafe;

import com.stocksafe.dao.*;
import com.stocksafe.objeto.Servidor;
import com.github.britooo.looca.api.core.Looca;
import com.stocksafe.conexao.Conexao;
import com.stocksafe.objeto.Funcionario;
import com.stocksafe.objeto.Maquina;
import com.stocksafe.objeto.Opcoes;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

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

        ProcessoDao processoDao = new ProcessoDao(con);

        Display display =
                new Display(leitor, leitorString, funcionarioDao, opcoesDao, maquina);

        if (opcoesDao.carregarOpcoes() == null){
            opcoesDao.criarOpcoes();
        }

        opcoes = opcoesDao.carregarOpcoes();

        boolean isLogado = false;

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

        ColetaDados coletaDados = new ColetaDados(servidor, maquina, maquinaDao);
        Thread insereDados = new Thread(coletaDados);
        insereDados.start();

        do {
            display.exibirMenuInicial();
            Integer opcaoEscolhida = display.solicitarOpcaoInt();

            switch (opcaoEscolhida) {
                case 1:
                    display.verificarDados();
                    break;
                case 2:
                    display.listarProcessos();
                    processoDao.inserirDadosProcessos(servidor, maquina);
                    break;
                case 3:
                    display.mudarOpcoes();
                    break;
                case 0:
                    isLogado = false;
                    coletaDados.setInserindo(false);
                    display.exibirMensagemSair();
                    break;
                default:
                    display.opcaoInvalida();
            }

            System.out.println(insereDados.isAlive());

            servidorDao.atualizarArmazenamento(servidor, maquina.getArmazenamentoTotal(), maquina.getArmazenamentoUsado());
        } while (isLogado);
    }
}