package stocksafe.solutions.semBanco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Menu {

    // Usuário define no começo do código
    private String nomeUsuario;
    private String cargoUsuario;


    // Usuário digita quando o código estiver rodando
    private String email;
    private String senha;
    private String confirmacaoSenha;


    // variáveis da verificação de cadastro e login
    private String[] splitando;
    private boolean cadastroExiste = false;
    private boolean loginExiste = false;
    private Integer reiniciandoValoresinteiros;
    private Double reiniciandoValoresQuebrados;
    private Boolean fecharPrograma = false;
    private String respostaUsuario;
    private Integer primeiraPerguntaMenu;
    private Integer getPrimeiraPerguntaLogin;
    private Boolean posicaoParList = false;
    private Boolean posicaoImparList = false;
    //Variavel utilizada para validar se a senha e do email que tentou logar
    private Integer verificacaoSenha = 0;
    private boolean queroContinuarCadastro = false;


    // Analytics da CPU, Memória e Disco
    private Integer valorAleatorioCPU = 0;
    private Integer valorAleatorioTemperaturaCPU = 0;
    private Double valorAleatorioVelocidadeCPU = 0.0;
    private Integer valorAleatorioMemoriaRam = 0;
    private Integer valorAleatorioTemperaturaDisco = 0;

    //Cores
    private String padrao = "\u001B[0m"; // Deixar como padrão
    private String negrito = "\u001B[1m";
    private String preto = "\u001B[30m";
    private String branco = "\u001B[37m";
    private String vermelho = "\u001B[31m";
    private String magenta = "\u001B[35m";
    private String azul = "\u001B[34m";

    private String ciano = "\u001B[36m";
    private String verde = "\u001B[32m";
    private String amarelo = "\u001B[33m";



    //Mensagens
    private String mensagem;
    private String linha = ciano + "-".repeat(50) + padrao;
    private String relatorio;





    //Métodos

    public void cadastro(){
        Scanner leituraEscrita = new Scanner(System.in);
        Scanner numero = new Scanner(System.in);


        System.out.println( linha + magenta + "\nCadastro"+ padrao +"\n Digite:\n"+"1 - para realizar o cadastro\n"
                +"2 - Sair e ir para o login");
        primeiraPerguntaMenu = numero.nextInt();


        if (primeiraPerguntaMenu == 1){

            do {
                //Reiniciando valor
                cadastroExiste = false;

                if (queroContinuarCadastro == true){
                    System.out.println("Deseja continuar com o cadastro (S/N)");
                     mensagem = leituraEscrita.nextLine();
                    queroContinuarCadastro = false;
                    if (mensagem.equalsIgnoreCase("N")){
                        queroContinuarCadastro = false;
                        mensagem = String.format("Indo para o login\n"+linha);
                        break;
                    }
                }

                //Inserindo informações
                System.out.println("Digite seu email");
                email = leituraEscrita.nextLine();
                System.out.println("Digite seu senha");
                senha = leituraEscrita.nextLine();
                System.out.println("Digite seu Confirme sua senha");
                confirmacaoSenha = leituraEscrita.nextLine();

                //Pegando valores TXT
                try {
                    File myObj = new File("filename.txt");
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        splitando = data.split(";");

                    }
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }


                if (splitando != null){
                    // Verificando se existe um cadastro igual
                    for (int i = 0; i < splitando.length; i++) {
                        if (i%2==0){
                            if (email.equals(splitando[i])){
                                cadastroExiste = true;
                                queroContinuarCadastro = true;
                                System.out.println(vermelho+"Email já cadastrado"+padrao);
                                break;
                            }
                        }
                    }
                }


                //Verificando se as senhas estão diferentes
                //Verificando se não tem nenhum outro cadastro em o mesmo email
                if (!(senha.equals(confirmacaoSenha))){
                    System.out.println(vermelho+"Senha diferente de confirmação de senha"+padrao);
                    queroContinuarCadastro = true;
                }else if (cadastroExiste == false){
                    mensagem = String.format(verde+"Cadastro realizado\n"+padrao+"Indo para o login\n"+linha);
                    //Escrevendo no txt
                    String EscrevendoTXT = String.format("%s;%s;",email,senha);
                    try {
                        FileWriter myWriter = new FileWriter("filename.txt",true);
                        myWriter.write(EscrevendoTXT);
                        myWriter.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                }
            }while (!(senha.equals(confirmacaoSenha)) || cadastroExiste == true || queroContinuarCadastro == true);


        } else {
            mensagem = String.format("Indo para o login\n"+linha);
        }


    }

    public void login (){
        Scanner leituraEscrita = new Scanner(System.in);
        Scanner leituraNumeroLogin = new Scanner(System.in);

        try {
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                splitando = data.split(";");



            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        if (splitando != null){


            do {

                //Inserindo informações
                System.out.println(magenta+"Login:"+padrao
                +"\nFazer login digite - 1"+"\nSair do login digite - 2");
                getPrimeiraPerguntaLogin = leituraNumeroLogin.nextInt();
                if (getPrimeiraPerguntaLogin == 1){


                    System.out.println("Digite seu email");
                    email = leituraEscrita.nextLine();
                    System.out.println("Digite sua senha");
                    senha = leituraEscrita.nextLine();

                    for (int i = 0; i < splitando.length; i++) {
                        if (i%2 == 0){
                            if (email.equals(splitando[i])){
                                posicaoParList = true;
                                verificacaoSenha = 1;
                            }
                        } else if (i%2 == 1 && verificacaoSenha == 1) {
                            verificacaoSenha = 2;
                            if (senha.equals(splitando[i])){
                                posicaoImparList = true;
                            }
                        }

                        if (posicaoParList == true && posicaoImparList == true){
                            loginExiste = true;
                            mensagem = String.format("Entrando no aplicativo\n"+linha);
                            break;
                        }
                        if (i == splitando.length-1){
                            if (posicaoParList == false || posicaoImparList == false   ) {
                                System.out.println(vermelho+"email ou senha errado\n" + padrao +linha);
                            }
                        }


                    }


                }else {
                    mensagem = String.format("");
                    break;
                }




            }while (loginExiste == false);


        }else {
            mensagem = String.format("Não existe nenhum login neste aplicativo");
        }


    }



    public void menu(){

        if (loginExiste == true){

            Scanner leitura = new Scanner(System.in);
            Scanner numero = new Scanner(System.in);


            System.out.println("Bem-vindo!"+"\n Digite:\n"+"1 - Acessar relatório de gerenciamento\n"
            +"2 - Sair");
            primeiraPerguntaMenu = numero.nextInt();

            if (primeiraPerguntaMenu == 1){

                do {

                    setReiniciandoValoresinteiros(0);
                    setReiniciandoValoresQuebrados(0.0);

                    for (int i = 0; i < 3; i++) {
                        // Pegar outro relatório (gerar novos dados)
                        valorAleatorioCPU += ThreadLocalRandom.current().nextInt(5,30);
                        valorAleatorioTemperaturaCPU = valorAleatorioTemperaturaCPU + ThreadLocalRandom.current().nextInt(40,70);
                        valorAleatorioVelocidadeCPU = valorAleatorioVelocidadeCPU + ThreadLocalRandom.current().nextDouble(3.5,4.0);
                        valorAleatorioMemoriaRam = valorAleatorioMemoriaRam + ThreadLocalRandom.current().nextInt(30,90);
                        valorAleatorioTemperaturaDisco += valorAleatorioTemperaturaDisco + ThreadLocalRandom.current().nextInt(20,30);
                        if (i == 2){
                            valorAleatorioCPU = valorAleatorioCPU / 3;
                            valorAleatorioTemperaturaCPU = valorAleatorioTemperaturaCPU / 3;
                            valorAleatorioVelocidadeCPU = valorAleatorioVelocidadeCPU / 3;
                            valorAleatorioMemoriaRam = valorAleatorioMemoriaRam / 3;
                            valorAleatorioTemperaturaDisco = valorAleatorioTemperaturaDisco / 3;
                        }
                    }


                    mensagem = String.format("\nDeseja gerar um novo relatório (%sS%s/%sN%s)",verde,padrao,vermelho,padrao);
                    System.out.println(getRelatorio() + mensagem);
                    respostaUsuario = leitura.next();

                    if (respostaUsuario.equalsIgnoreCase("N")){
                        fecharPrograma = true;
                    }
                }while (fecharPrograma == false);

            }


        }else {
            mensagem = String.format("Como não foi feito o login"
            +" não será possível acessar o menu");
        }

    }



    //GETTERS e SETTERS
    public String getMensagem() {
        return mensagem;
    }


    public String getRelatorio() {
        relatorio = String.format(linha +
                         amarelo + "\n Média dos ultimos 3 dados capitados\n" + padrao + linha +
                         azul + "\nCPU:" + padrao +"\n Porcentagem média:%s %d%% %s"
                        +"\n Temperatura média:%s %d°C%s"+"\n Velocidade média:%s %fGHz%s\n"+
                        linha + azul + "\nMemória:"+ padrao +"\n Média de uso:%s %d%% %s\n"
                        +linha + azul + "\nDisco"+ padrao +"\n Temperatura média:%s %d %s\n"+linha
                ,negrito,valorAleatorioCPU,padrao, negrito,valorAleatorioTemperaturaCPU,padrao,
                negrito,valorAleatorioVelocidadeCPU,padrao,negrito,valorAleatorioMemoriaRam,
                padrao,negrito,valorAleatorioTemperaturaDisco,padrao);
        return relatorio;
    }

    public void setReiniciandoValoresinteiros(Integer valorReiniciado) {
        valorAleatorioCPU = 0;
        valorAleatorioTemperaturaCPU = 0;
        valorAleatorioMemoriaRam = 0;
        valorAleatorioTemperaturaDisco = 0;

    }

    public void setReiniciandoValoresQuebrados(Double valorReiniciado) {
        valorAleatorioVelocidadeCPU = 0.0;
    }
}
