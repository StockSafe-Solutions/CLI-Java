package com.stocksafe;

import com.stocksafe.dao.FuncionarioDao;
import com.stocksafe.dao.OpcoesDao;
import com.stocksafe.objeto.Funcionario;
import com.stocksafe.objeto.Maquina;
import com.stocksafe.objeto.Opcoes;

import java.util.List;
import java.util.Scanner;

public class Display {
    private final Scanner leitor;
    private final Scanner leitorString;
    private final OpcoesDao opcoesDao;
    private final Maquina maquina;


    public Display(OpcoesDao opcoesDao, Maquina maquina) {
        this.leitor = new Scanner(System.in);
        this.leitorString = new Scanner(System.in);
        this.opcoesDao = opcoesDao;
        this.maquina = maquina;
    }

    public Funcionario realizarLogin() {
        System.out.println(
        """
        +================================+
        | LOGIN - StockSafe Solutions    |
        +================================+
        """);

        System.out.println("Digite o seu email: ");
        String email = leitorString.nextLine();

        System.out.println("Digite a sua senha: ");
        String senha = leitorString.nextLine();

        return new Funcionario(email, senha);
    }

    public Boolean autenticarLogin(Funcionario usuario, FuncionarioDao funcionarioDao) {
        List<Funcionario> funcionarioCadastrado = funcionarioDao.getFuncionarioPorLogin(usuario);

        if (!funcionarioCadastrado.isEmpty()) {
            System.out.println("Usuário autenticado");
            return true;

        } else {
            System.out.println("Usuário inválido");
            return false;
        }
    }

    public void exibirMenuInicial() {
        System.out.println(
        """
        +--------------------------------------+
        | StockSafe Solutions                  |
        +--------------------------------------+
        | 1) Verificar Dados                   |
        | 2) Listar Processos                  |
        | 3) Mudar configurações de exibição   |
        |                                      |
        | 0) Sair                              |
        +--------------------------------------+
        """);
    }

    public void verificarDados () {
        Opcoes opcoes = opcoesDao.carregarOpcoes();

        if (opcoes.getMostrarUsoRam() != null) {
            for (int i = 0; i < 20; i++) {

                System.out.println("""
                +---------------------------------------------------------------------+
                | Dados Atuais
                +---------------------------------------------------------------------+""");
                if (opcoes.getMostrarUsoCpu().equals("1")) {
                    System.out.printf("| Uso de CPU: %.2f %%%n",
                            maquina.getPorcentagemUsoCpu());
                }
                if (opcoes.getMostrarUsoRam().equals("1")) {
                    System.out.printf("| Uso de RAM: %.2f %%%n",
                            maquina.getPorcentagemUsoRam());
                }
                if (opcoes.getMostrarTaxaTransferencia().equals("1")) {
                    System.out.printf("| Taxa de Transferência: %.2f Mb %n",
                            maquina.getTaxaDeTransferencia());
                }
                if (opcoes.getMostrarPacotesEnviados().equals("1")) {
                    System.out.printf("| Pacotes Enviados: %.0f %n",
                            maquina.getPacotesEnviados());
                }
                if (opcoes.getMostrarArmazenamentoTotal().equals("1")) {
                    System.out.printf("| Armazenamento Total: %.2f GB %n",
                            maquina.getArmazenamentoTotal());
                }
                if (opcoes.getMostrarArmazenamentoUsado().equals("1")) {
                    System.out.printf("| Armazenamento Usado: %.2f GB %n",
                            maquina.getArmazenamentoUsado());
                }
                    System.out.printf("| RAM disponível: %.2f %%%n",
                            maquina.getPorcentagemDisponivelRam());

                    System.out.printf("| RAM total: %.2f GB %n",
                            maquina.getTotalRam());

                System.out.println(
                        "+---------------------------------------------------------------------+");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void listarProcessos () {
        System.out.println(maquina.getStringProcessos());
    }
    public void mudarOpcoes () {
        Opcoes opcoes = opcoesDao.carregarOpcoes();
        System.out.println(opcoes);

        System.out.println("Insira um valor (1 = Mostrar / 0 = Ocultar): ");

        opcoes.setOpcoes(leitorString);

        opcoesDao.alterarOpcoes(opcoes);
    }

    public Integer solicitarOpcaoInt() {
        System.out.println("Selecione uma opção:");
        return leitor.nextInt();
    }

    public String solicitarOpcaoString() {
        System.out.println("Selecione uma opção:");
        return leitorString.nextLine();
    }

    public void exibirMensagemSair () {
        System.out.println("Saindo... ");
        System.exit(0);
    }

    public void opcaoInvalida () { System.out.println("Opção inválida"); }
}
