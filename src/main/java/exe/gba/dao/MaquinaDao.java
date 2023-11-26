package exe.gba.dao;

import exe.gba.objeto.Funcionario;
import exe.gba.objeto.Maquina;
import exe.gba.objeto.Opcoes;
import exe.gba.objeto.Servidor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MaquinaDao {
    private JdbcTemplate con;
    private JdbcTemplate conD;


    public MaquinaDao(JdbcTemplate con, JdbcTemplate conD) {
        this.con = con;
        this.conD = conD;
    }

    public void inserirDados (Servidor servidor, Maquina maquina) {
        con.update("""
        INSERT INTO tb_registro VALUES(
            null,
            %d,
            now(),
            %.0f,
            %.2f,
            %.2f,
            %.2f
        );
        """.formatted(servidor.getIdServidor(),
                maquina.getPacotesEnviados(),
                maquina.getPorcentagemUsoCpu(),
                maquina.getPorcentagemUsoRam(),
                maquina.getTaxaDeTransferencia())
        );

        conD.update("""
        INSERT INTO tb_registro VALUES(
            null,
            %d,
            now(),
            %.0f,
            %.2f,
            %.2f,
            %.2f
        );
        """.formatted(servidor.getIdServidor(),
                maquina.getPacotesEnviados(),
                maquina.getPorcentagemUsoCpu(),
                maquina.getPorcentagemUsoRam(),
                maquina.getTaxaDeTransferencia())
        );
    }
}
