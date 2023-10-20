package exe.gba.main;

import com.github.britooo.looca.api.core.Looca;
import exe.gba.conexao.Conexao;
import exe.gba.dao.FuncionarioDao;
import exe.gba.dao.OpcoesDao;
import exe.gba.objeto.Maquina;
import exe.gba.objeto.MaquinaAntiga;
import exe.gba.objeto.Menu;
import exe.gba.objeto.Opcoes;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Looca looca = new Looca();

        Maquina maquina = new Maquina(looca);

        System.out.println(maquina.getRam());
    }
//        Conexao conexao = new Conexao();
//        JdbcTemplate con = conexao.getConexaoDoBanco();
//
//        Scanner leitor = new Scanner(System.in);
//        Scanner leitorString = new Scanner(System.in);
//
//        FuncionarioDao funcionarioDao = new FuncionarioDao(con);
//        OpcoesDao opcoesDao = new OpcoesDao();
//        MaquinaAntiga maquinaAntiga = new MaquinaAntiga();
//
//        Menu menu = new Menu(leitor, leitorString, funcionarioDao, opcoesDao, maquinaAntiga);
//
//        Opcoes opcoes = new Opcoes();
//
//        if (opcoesDao.carregarOpcoes().getMostrarUsoCpu() == null){
//            opcoesDao.criarOpcoes(opcoes);
//        }
//
//        do {
//            System.out.println("Faça seu login");
//        } while (!menu.fazerLogin());
//
//        Integer opcaoEscolhida;
//
//        do {
//            menu.exibirMenuInicial();
//            opcaoEscolhida = menu.solicitarOpcaoInt();
//
//            switch (opcaoEscolhida) {
//                case 1:
//                    menu.verificarDados();
//                    break;
//                case 2:
//                    menu.mudarOpcoes();
//                    break;
//                case 0:
//                    menu.exibirMensagemSair();
//                    break;
//                default:
//                    menu.opcaoInvalida();
//            }
//        } while (opcaoEscolhida != 0);
//    }
}