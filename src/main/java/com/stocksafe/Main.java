package com.stocksafe;

import com.stocksafe.dao.*;
import com.stocksafe.objeto.Servidor;
import com.stocksafe.conexao.Conexao;
import com.stocksafe.objeto.Funcionario;
import com.stocksafe.objeto.Maquina;
import com.stocksafe.objeto.Opcoes;
import com.stocksafe.runnables.RegistroDeDado;
import com.stocksafe.runnables.RegistroDeProcesso;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        Scanner leitorString = new Scanner(System.in);

        FuncionarioDao funcionarioDao = new FuncionarioDao(con);
        MaquinaDao maquinaDao = new MaquinaDao(con);
        OpcoesDao opcoesDao = new OpcoesDao();
        ProcessoDao processoDao = new ProcessoDao(con);
        ServidorDao servidorDao = new ServidorDao(con);

        Maquina maquina = new Maquina();
        Funcionario funcionarioLogado = new Funcionario();

        Display display = new Display(opcoesDao, maquina);

        boolean isLogado = false;

        // ====================================== Carregando opções
        if (opcoesDao.carregarOpcoes() == null) {
            opcoesDao.criarOpcoes();
        }
        Opcoes opcoes = opcoesDao.carregarOpcoes();

        // ====================================== Autenticando Login
        while (!isLogado) {
            funcionarioLogado = display.realizarLogin();
            isLogado = display.autenticarLogin(funcionarioLogado, funcionarioDao);
        }

        // ====================================== Verificando servidor
        while (servidorDao.selecionarServidor(opcoes).isEmpty()) {
            opcoes.setCodigo(leitorString);
            opcoesDao.alterarOpcoes(opcoes);
        }
        Servidor servidor = servidorDao.selecionarServidor(opcoes).get(0);
        servidorDao.autenticarServidor(servidor, funcionarioLogado);

        // ====================================== Inserção de dados
        RegistroDeDado coletaDados = new RegistroDeDado(servidor, maquina, maquinaDao);
        Thread insereDados = new Thread(coletaDados);

        RegistroDeProcesso coletaProcessos = new RegistroDeProcesso(processoDao, maquina, servidor);
        Thread insereProcessos = new Thread(coletaProcessos);

        insereDados.start();
        insereProcessos.start();

        do {
            System.out.println("Iniciando coleta de dados...");

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

            servidorDao.atualizarArmazenamento
                    (servidor, maquina.getArmazenamentoTotal(), maquina.getArmazenamentoUsado());

        } while (isLogado);
    }
}