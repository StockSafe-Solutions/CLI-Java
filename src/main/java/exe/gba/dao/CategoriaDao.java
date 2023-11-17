package exe.gba.dao;

import exe.gba.objeto.Categoria;
import exe.gba.objeto.Opcoes;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoriaDao {
    private JdbcTemplate con;
    private String tipoCategoria;
    private String unidade;

    public CategoriaDao(JdbcTemplate con) {
        this.con = con;
        this.tipoCategoria = tipoCategoria;
        this.unidade = unidade;
    }

    public List<Categoria> selecionarCategoria () {
        return con.query("SELECT id_cat FROM tb_categoria WHERE tipo_cat = ?;", new BeanPropertyRowMapper<>( Categoria.class), this.tipoCategoria);
    }
    public void inserirDadosCategoria( String tipoCategoria, String unidade){
        con.update("""
        INSERT INTO tb_categoria VALUES(
            null,
            %s,
            %s,
        );
        """.formatted(this.tipoCategoria, this.unidade)
        );
    }
}
