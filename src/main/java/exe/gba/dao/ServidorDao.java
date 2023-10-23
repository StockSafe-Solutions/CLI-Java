package exe.gba.dao;

import exe.gba.objeto.Funcionario;
import exe.gba.objeto.Maquina;
import exe.gba.objeto.Opcoes;
import exe.gba.objeto.Servidor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ServidorDao {
    private JdbcTemplate con;

    public ServidorDao(JdbcTemplate con) {
        this.con = con;
    }

    public List<Servidor> selecionarServidor (Opcoes opcoes) {
        return con.query("SELECT id_servidor, codigo FROM tb_servidor WHERE codigo = ?;", new BeanPropertyRowMapper<>(Servidor.class), opcoes.getCodigo());
    }
}
