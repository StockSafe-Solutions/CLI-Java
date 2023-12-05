package com.stocksafe;

import com.stocksafe.Enums.CorTag;
import com.stocksafe.dao.FuncionarioDao;
import com.stocksafe.dao.OpcoesDao;
import com.stocksafe.dao.TagDao;
import com.stocksafe.objeto.Funcionario;
import com.stocksafe.objeto.Maquina;
import com.stocksafe.objeto.Opcoes;
import com.stocksafe.objeto.Tag;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Scanner;

public class Display {
    private final Scanner leitor;
    private final Scanner leitorString;
    private final OpcoesDao opcoesDao;
    private final Maquina maquina;
    private JdbcTemplate con;
    private JdbcTemplate conLocal;


    public Display(OpcoesDao opcoesDao, Maquina maquina, JdbcTemplate con, JdbcTemplate conLocal) {
        this.leitor = new Scanner(System.in);
        this.leitorString = new Scanner(System.in);
        this.opcoesDao = opcoesDao;
        this.maquina = maquina;
        this.con = con;
        this.conLocal = conLocal;
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
        | 4) Gerenciar tags                    |
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

    public void gerenciarTags(){
        boolean continuar = true;
        while(continuar) {
            System.out.println(
                    """
                            +--------------------------------------+
                            | Gerenciando tags                     |
                            +--------------------------------------+
                            | 1) Adicionar nova tag                |
                            | 2) Colocar tags nesse servidor       |
                            |                                      |
                            | 0) Voltar                            |
                            +--------------------------------------+
                            """);
            int opcaoTags = solicitarOpcaoInt();
            switch (opcaoTags) {
                case 0 -> continuar = false;
                case 1 -> {
                    adicionarNovaTag();
                    continuar = false;
                }
                //case 2 -> TODO!!
                default -> System.err.println("[!] Opção inválida");
            }
        }
    }

    public void adicionarNovaTag(){
        System.out.println(
                """
                +--------------------------------------+
                | Digite o nome da nova tag            |
                +--------------------------------------+""");

        String nomeTag = "";
        while(nomeTag.length() < 3 || nomeTag.length() > 75){
            nomeTag = leitorString.nextLine();
            if(nomeTag.length() < 3 || nomeTag.length() > 75){
                System.err.println("[!] O nome da tag deve ter entre 3 e 75 caracteres.");
            }
        }

        System.out.println(
                """
                +--------------------------------------+
                | Selecione uma cor para sua tag       |
                +--------------------------------------+""");
        CorTag[] cores = CorTag.values();
        for(int i = 1; i < cores.length; i++){
            System.out.println(i+". "+cores[i]);
        }

        int indiceCor = 0;
        while(indiceCor < 1 || indiceCor >= cores.length){
            indiceCor = leitor.nextInt();
            if(indiceCor < 1 || indiceCor > cores.length){
                System.err.println("[!] Cor inválida");
            }
        }

        Tag tag = new Tag(nomeTag, cores[indiceCor-1].getCOR_RGB());
        TagDao tagDao = new TagDao(con, conLocal);
        tagDao.adicionarTag(tag);
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
