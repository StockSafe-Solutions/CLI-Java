package com.stocksafe.dao;

import com.stocksafe.objeto.Categoria;
import com.stocksafe.objeto.Maquina;
import com.stocksafe.objeto.Servidor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Timer;

public class MaquinaDao {

    private JdbcTemplate con;
    private JdbcTemplate conLocal;

    public MaquinaDao(JdbcTemplate con, JdbcTemplate conLocal) {
        this.con = con;
        this.conLocal = conLocal;
    }

    public void inserirDadosPacote (Servidor servidor, Maquina maquina) {
        con.update("""
        INSERT INTO tb_registro(fk_servidor, fk_cat, data_hora, valor) VALUES(
            %d,
            %d,
            GETDATE(),
            %.0f
        );
        """.formatted(servidor.getIdServidor(),
                Categoria.PACOTES.getId(),
                maquina.getPacotesEnviados())
        );

        conLocal.update("""
        INSERT INTO tb_registro VALUES(
            null,
            %d,
            %d,
            now(),
            %.0f
        );
        """.formatted(servidor.getIdServidor(),
                Categoria.PACOTES.getId(),
                maquina.getPacotesEnviados())
        );
    }

    public void inserirDadosCpu (Servidor servidor, Maquina maquina) {
        con.update("""
        INSERT INTO tb_registro(fk_servidor, fk_cat, data_hora, valor) VALUES(
            %d,
            %d,
            GETDATE(),
            %.2f
        );
        """.formatted(servidor.getIdServidor(),
                Categoria.CPU.getId(),
                maquina.getPorcentagemUsoCpu())
        );

        conLocal.update("""
        INSERT INTO tb_registro VALUES(
            null,
            %d,
            %d,
            now(),
            %.2f
        );
        """.formatted(servidor.getIdServidor(),
                Categoria.CPU.getId(),
                maquina.getPorcentagemUsoCpu())
        );
    }

    public void inserirDadosRam (Servidor servidor, Maquina maquina) {
        con.update("""
        INSERT INTO tb_registro(fk_servidor, fk_cat, data_hora, valor) VALUES(
            %d,
            %d,
            GETDATE(),
            %.2f
        );
        """.formatted(servidor.getIdServidor(),
                Categoria.RAM.getId(),
                maquina.getPorcentagemUsoRam())
        );

        conLocal.update("""
        INSERT INTO tb_registro VALUES(
            null,
            %d,
            %d,
            now(),
            %.2f
        );
        """.formatted(servidor.getIdServidor(),
                Categoria.RAM.getId(),
                maquina.getPorcentagemUsoRam())
        );
    }

    public void inserirDadosTransferencia (Servidor servidor, Maquina maquina) {
        con.update("""
        INSERT INTO tb_registro(fk_servidor, fk_cat, data_hora, valor) VALUES(
            %d,
            %d,
            GETDATE(),
            %.2f
        );
        """.formatted(servidor.getIdServidor(),
                Categoria.TAXA_TRANSFERENCIA.getId(),
                maquina.getTaxaDeTransferencia())
        );

        conLocal.update("""
        INSERT INTO tb_registro VALUES(
            null,
            %d,
            %d,
            now(),
            %.2f
        );
        """.formatted(servidor.getIdServidor(),
                Categoria.RAM.getId(),
                maquina.getPorcentagemUsoRam())
        );
    }
}
