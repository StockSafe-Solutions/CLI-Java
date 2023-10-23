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
                            Qual máquina deseja ver?
                            1. Máquina 1
                            2. Máquina 2
                            3. Máquina 3
                            """);
                    escolha = leitor.nextInt();

                    switch (escolha) {
                        case 1:
                            System.out.println("Máquina 1 ");
                            break;
                        case 2:
                            System.out.println("Máquina 2 ");
                            break;
                        case 3:
                            System.out.println("Máquina 3 ");
                            break;
                    }

                    System.out.println("""
                            1. Informações da Máquina
                            0. Sair
                            """);
                    escolha = leitor.nextInt();

                    if (escolha.equals(1)) {
                        infoMaquina.analiseDoSistema();
                        System.out.println("-".repeat(50));

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
