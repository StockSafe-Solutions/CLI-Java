package stocksafe.solutions.comBanco;

import java.util.Scanner;

public class BancoMain {
    public static void main(String[] args) {

            Scanner leitor = new Scanner(System.in);
            Cadastro usuarioCadastro = new Cadastro();
            Login usuarioLogin = new Login();
            Maquina infoMaquina = new Maquina();

        do {

            System.out.println("""
                    1. Cadastro
                    2. Login
                    0. Sair
                    """);
            Integer escolha = leitor.nextInt();

            if (escolha.equals(1)) {
                usuarioCadastro.cadastroTela();
                usuarioCadastro.enviandoCadastroBanco();
            }

            else if (escolha.equals(2)) {
                usuarioLogin.telaLogin();
                usuarioLogin.infoLoginDoBanco();
                usuarioLogin.respostaVerificacao();

                do {

                    System.out.println("""
                            1. Informações das Máquinas
                            0. Sair
                            """);
                    escolha = leitor.nextInt();

                    if (escolha.equals(1)) {
                        infoMaquina.analiseDoSistema();

                    } else {
                        break;
                    }


                } while (true);

            } else if (escolha.equals(0)) {
                    break;
                }



        } while(true);












    }
}
