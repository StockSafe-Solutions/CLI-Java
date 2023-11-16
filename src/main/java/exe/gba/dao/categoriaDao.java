package exe.gba.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class categoriaDao {
    private JdbcTemplate con;
    private String tipoCategoria;
    private String unidade;

    public categoriaDao(JdbcTemplate con, String tipoCategoria, String unidade) {
        this.con = con;
        this.tipoCategoria = tipoCategoria;
        this.unidade = unidade;
    }

    public void inserirDadosCategoria(){
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
