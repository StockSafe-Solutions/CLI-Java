package school.sptech.comBanco;

import com.github.britooo.looca.api.core.Looca;

public class BancoMain {
    public static void main(String[] args) {
        /*

        Cadastro usuarioCadastro = new Cadastro();

        usuarioCadastro.cadastroTela();

         */



        Login usuarioLogin = new Login();

        usuarioLogin.telaLogin();
        usuarioLogin.infoLoginDoBanco();
        usuarioLogin.respostaVerificacao();
        System.out.println(usuarioLogin.getLoginDoBanco());






        Maquina infoMaquina = new Maquina();
        infoMaquina.analiseDoSistema();














    }
}
