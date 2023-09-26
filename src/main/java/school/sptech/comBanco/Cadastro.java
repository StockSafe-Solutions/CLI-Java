package school.sptech.comBanco;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;

import java.util.List;
import java.util.Scanner;

public class Cadastro {


    private String emailCadastro;
    private String senhaCadastro;
    private List<Cadastro> emailSenha;


    private Conexao conexaoLogin = new Conexao();
    private JdbcTemplate con = conexaoLogin.getConxaoDoBanco();


    public Cadastro() {

    }

    public void cadastroTela (){

        Scanner leituraEscrita = new Scanner(System.in);

        System.out.println("Cadastro: \n"+"Digite seu email:");
        this.emailCadastro = leituraEscrita.nextLine();
        System.out.println("Digite seu senha:");
        this.senhaCadastro = leituraEscrita.nextLine();

        this.enviandoCadastroBanco();
        System.out.println("Cadastro realizado\n"+"-".repeat(50));

    }


    public void enviandoCadastroBanco (){

        String sql = "insert into usuario (nome, senha) values (?, ?)";
        con.update(sql,getEmailCadastro(),getSenhaCadastro());


    }




    public String getEmailCadastro() {
        return emailCadastro;
    }

    public String getSenhaCadastro() {
        return senhaCadastro;
    }

    public List<Cadastro> getEmailSenha() {
        return emailSenha;
    }
}
