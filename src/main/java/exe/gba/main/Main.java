package exe.gba.main;

import com.github.britooo.looca.api.core.Looca;
import exe.gba.conexao.Conexao;
import exe.gba.dao.*;
import exe.gba.objeto.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {

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

        Menu menu = new Menu(leitor, leitorString, funcionarioDao, opcoesDao, maquina);


        MemoriaDao md = new MemoriaDao( con );


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
        String resposta = "";
        String res = "";
        while (true){
            System.out.println("Quer cadastrar uma nova categoria? [S/n]");
             resposta = leitorString. nextLine();
             if(resposta.equalsIgnoreCase( "n" )){
                 break;
             }else{
                 while (true){
                     System.out.println("Digite o id: ");
                     int id = leitor.nextInt();

                     System.out.println("Digite o tipo: ");
                     String  tipo = leitorString.nextLine();

                     System.out.println("Digite a unidade");
                     String unidade = leitorString.nextLine();

                     CategoriaDao cd = new CategoriaDao( con );
                     cd.inserirDadosCategoria(id ,tipo, unidade);

                     System.out.println("Deseja adicionar mais uma?  [S/n]");
                     res = leitorString.nextLine();

                     if (res.equalsIgnoreCase( "n" )){
                         break;
                     }
                 }

             }
        }
        while (servidorDao.selecionarServidor(opcoes).isEmpty()) {
            opcoes.setCodigo(leitorString);

            opcoesDao.alterarOpcoes(opcoes);
        }

        servidor = servidorDao.selecionarServidor(opcoes).get(0);

        servidorDao.autenticarServidor(servidor, funcionario);

        do {
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
                case 4 :
                    for (int i = 0; i < 60; i++) {
                        System.out.println( "+--------------------------------------------------------------------------+" );
                        System.out.println( "| Dados Atuais" );
                        System.out.println( "+--------------------------------------------------------------------------+" );
                        System.out.printf( "| Uso de RAM: %.2f %%%n", maquina.getPorcentagemUsoRam() );
                        md.inserirDadosRamEmUso( servidor, maquina, 3 );
                        System.out.printf( "| Espaço total de RAM: %.2f %%%n", maquina.getTotalRam() );
                        md.inserirDadosRamTotal( servidor, maquina, 5 );
                        System.out.printf( "| Espaço livre  de RAM: %.2f %%%n", maquina.getPercentagemDisponivelRam() );
                        md.inserirDadosRamDisponivel( servidor, maquina, 6 );
                        Thread.sleep( 5000 );
                    }
                    break;
                case 0:
                    menu.exibirMensagemSair();
                    break;
                default:
                    menu.opcaoInvalida();
            }
            md.data();




            //maquinaDao.inserirDados(servidor, maquina);
            //servidorDao.atualizarArmazenamento(servidor, maquina.getArmazenamentoTotal(), maquina.getArmazenamentoUsado());
        } while (true);
    }
}