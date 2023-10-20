package exe.gba.dao;

import exe.gba.objeto.MaquinaAntiga;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MaquinaDaoAntiga {
    private JdbcTemplate con;

    public MaquinaDaoAntiga(JdbcTemplate con) { this.con = con; }

    public List<MaquinaAntiga> listar () {
        return con.query("SELECT id_funcionario, nome, email FROM tb_funcionario;", new BeanPropertyRowMapper<>(MaquinaAntiga.class));
    }

    public void inserirDados (MaquinaAntiga maquinaAntiga) {
        con.update("INSERT INTO ");
    }
}
