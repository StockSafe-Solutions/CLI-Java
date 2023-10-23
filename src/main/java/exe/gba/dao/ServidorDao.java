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

    public void cadastrarServidor (Opcoes opcoes, Maquina maquina, Funcionario funcionario) {
        con.update("""
        INSERT INTO tb_servidor values (
        null,
        '%s',
        %.1f,
        %.1f,
        %d)
        """.formatted(opcoes.getCodigo(), maquina.getArmazenamentoTotal(), maquina.getArmazenamentoUsado(), funcionario.getIdFuncionario()));
    }

    public List<Servidor> selecionarServidor (Opcoes opcoes) {
        return con.query("SELECT id_servidor, codigo FROM tb_servidor WHERE codigo = ?;", new BeanPropertyRowMapper<>(Servidor.class), opcoes.getCodigo());
    }
}
