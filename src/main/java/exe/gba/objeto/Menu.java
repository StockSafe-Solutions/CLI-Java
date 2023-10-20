package exe.gba.objeto;

import exe.gba.dao.FuncionarioDao;
import exe.gba.dao.OpcoesDao;

import java.util.Scanner;

public class Menu {
    private Scanner leitor;
    private Scanner leitorString;
    private FuncionarioDao funcionarioDao;
    private OpcoesDao opcoesDao;
    private MaquinaAntiga maquinaAntiga;

    public Menu(Scanner leitor, Scanner leitorString, FuncionarioDao funcionarioDao, OpcoesDao opcoesDao, MaquinaAntiga maquinaAntiga) {
        this.leitor = leitor;
        this.leitorString = leitorString;
        this.funcionarioDao = funcionarioDao;
        this.opcoesDao = opcoesDao;
        this.maquinaAntiga = maquinaAntiga;
    }

    public Boolean fazerLogin() {
        System.out.println("Digite o seu email: ");
        String email = leitorString.nextLine();

        System.out.println("Digite a sua senha: ");
        String senha = leitorString.nextLine();

        Funcionario funcionario = new Funcionario(email, senha);

        if (funcionarioDao.getFuncionarioPorLogin(funcionario).isEmpty()) {
            System.out.println("Usuário inválido");
            return false;
        }
        System.out.println("Login efetuado, indo para o menu... ");
        return true;
    }

    public void exibirMenuInicial() {
        System.out.println(
            """
            +--------------------------------------+
            | StockSafe Solutions                  |
            +--------------------------------------+
            | 1) Verificar Dados                   |
            | 2) Mudar configurações de exibição   |
            |                                      |
            | 0) Sair                              |
            +--------------------------------------+
                """);
    }

    public void verificarDados () {
        System.out.println(maquinaAntiga.getMediaBytesEnviados());
    }

    public void mudarOpcoes () {
        Opcoes opcoes = opcoesDao.carregarOpcoes();
        System.out.println(opcoes);

        System.out.println("Insira um valor (1 = Mostrar / 0 = Ocultar): ");

        for (int i = 0; i < 6; i++) {
            System.out.printf("%s) ", i+1);

            String opcaoEscolhida = solicitarOpcaoString();

            if (opcaoEscolhida.equals("0") || opcaoEscolhida.equals("1")) {
                switch (i) {
                    case 0 -> opcoes.setMostrarUsoCpu(opcaoEscolhida);
                    case 1 -> opcoes.setMostrarUsoRam(opcaoEscolhida);
                    case 2 -> opcoes.setMostrarPacotesEnviados(opcaoEscolhida);
                    case 3 -> opcoes.setMostrarTaxaTransferencia(opcaoEscolhida);
                    case 4 -> opcoes.setMostrarArmazenamentoTotal(opcaoEscolhida);
                    case 5 -> opcoes.setmostrarArmazenamentoUsado(opcaoEscolhida);
                }
            } else {
                System.out.println("Opção inválida! ");
                return;
            }
        }

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

    public void exibirMensagemSair () { System.out.println("Saindo... "); }

    public void opcaoInvalida () { System.out.println("Opção inválida"); }
}
