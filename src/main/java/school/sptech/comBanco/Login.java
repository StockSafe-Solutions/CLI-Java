package school.sptech.comBanco;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Scanner;

public class Login {

    private String nome;
    private String senha;


    private List<Login> LoginDoBanco;
    private Conexao conexaoLogin = new Conexao();
    private JdbcTemplate con = conexaoLogin.getConxaoDoBanco();


    public Login() {
    }



//Usuário digitando o email e senha
    public void telaLogin (){
        Scanner leituraEscrita = new Scanner(System.in);

        System.out.println("Login: \n"+"Digite seu email:");
        this.nome = leituraEscrita.nextLine();
        System.out.println("Digite seu senha:");
        this.senha = leituraEscrita.nextLine();


    }

//Procurando no banco
    public void infoLoginDoBanco (){

        LoginDoBanco = con.query("select email,senha from usuario " +
                        "where email = ? and senha = ?;",
                new BeanPropertyRowMapper<>(Login.class),
                getNome(), getSenha());


    }


    public void respostaVerificacao (){

        if (LoginDoBanco.isEmpty()){

            do {
                System.out.println("Email ou senha errado\n"+"-".repeat(50));
                this.telaLogin();
                this.infoLoginDoBanco();
            }while (LoginDoBanco.isEmpty());
            System.out.println("Login efetuado\n"+"-".repeat(50));

        }else {

            System.out.println("Login efetuado\n"+"-".repeat(50));

        }

    }



    //GETTERS e SETTERS
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }



    public List<Login> getLoginDoBanco() {
        return LoginDoBanco;
    }

    //Vendo a resposta do banco
    @Override
    public String toString() {
        return "InformacoesLogin{" +
                " nome='" + nome + '\'' +
                ", senha='" + senha + '\''+
                '}';
    }





}
