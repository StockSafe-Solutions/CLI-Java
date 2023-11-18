package exe.gba.dao;

import exe.gba.objeto.Categoria;
import exe.gba.objeto.Servidor;
import exe.gba.objeto.Maquina;
import org.springframework.jdbc.core.JdbcTemplate;

public class MaquinaDao {
    private JdbcTemplate con;

    public MaquinaDao(JdbcTemplate con) {
        this.con = con;
    }

    // TODO: 18/11/2023 Atualizar a inserção de dados ao novo modelo
    public void inserirDados (Servidor servidor, Categoria categoria) {
        con.update("""
        INSERT INTO tb_registro VALUES(
            null,
            %d,
            %d,
            now(),
            %.2f
        );
        """.formatted(servidor.getIdServidor(),
                // categoria.getCategoria()
                )
        );
    }
}
