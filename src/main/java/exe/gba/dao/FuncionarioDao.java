package exe.gba.dao;

import exe.gba.objeto.Funcionario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FuncionarioDao {
    private JdbcTemplate con;
    private JdbcTemplate conD;

    public FuncionarioDao(JdbcTemplate con, JdbcTemplate conD) {
        this.con = con;
        this.conD = conD;
    }

    public List<Funcionario> listar () {
        conD.query(  "SELECT id_funcionario, nome, email FROM tb_funcionario;", new BeanPropertyRowMapper<>(Funcionario.class));
        return con.query("SELECT id_funcionario, nome, email FROM tb_funcionario;", new BeanPropertyRowMapper<>(Funcionario.class));
    }

    public List<Funcionario> getFuncionarioPorLogin (Funcionario funcionario) {
        //conD.query(  "SELECT * FROM tb_funcionario WHERE email = ? AND senha = ?", new BeanPropertyRowMapper<>(Funcionario.class), funcionario.getEmail(), funcionario.getSenha());
        return con.query("SELECT * FROM tb_funcionario WHERE email = ? AND senha = ?", new BeanPropertyRowMapper<>(Funcionario.class), funcionario.getEmail(), funcionario.getSenha());
    }
}
