package com.stocksafe.dao;

import com.stocksafe.Enums.Categoria;
import com.stocksafe.objeto.Maquina;
import com.stocksafe.objeto.Servidor;
import org.springframework.jdbc.core.JdbcTemplate;

public class MaquinaDao {

    private final JdbcTemplate con;
    private final JdbcTemplate conLocal;

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
                Categoria.PACOTES.getID(),
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
                Categoria.PACOTES.getID(),
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
                Categoria.CPU.getID(),
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
                Categoria.CPU.getID(),
                maquina.getPorcentagemUsoCpu())
        );

        AlertaDao.inserirAlerta(Categoria.CPU.getID(), maquina.getPorcentagemUsoCpu(), servidor.getIdServidor(), conLocal);
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
                Categoria.RAM.getID(),
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
                Categoria.RAM.getID(),
                maquina.getPorcentagemUsoRam())
        );

        AlertaDao.inserirAlerta(Categoria.RAM.getID(), maquina.getPorcentagemUsoRam(), servidor.getIdServidor(), conLocal);
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
                Categoria.TAXA_TRANSFERENCIA.getID(),
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
                Categoria.TAXA_TRANSFERENCIA.getID(),
                maquina.getPorcentagemUsoRam())
        );

        AlertaDao.inserirAlerta(Categoria.TAXA_TRANSFERENCIA.getID(), maquina.getTaxaDeTransferencia(), servidor.getIdServidor(), conLocal);
    }

    public void inserirDadosRamTotal (Servidor servidor, Maquina maquina) {
        con.update("""
        INSERT INTO tb_registro(fk_servidor, fk_cat, data_hora, valor) VALUES(
            %d,
            5,
            GETDATE(),
            %.2f
        );
        """.formatted(servidor.getIdServidor(),
                maquina.getTotalRam())
        );

        conLocal.update("""
        INSERT INTO tb_registro VALUES(
            null,
            %d,
            5,
            now(),
            %.2f
        );
        """.formatted(servidor.getIdServidor(),
                maquina.getTotalRam())
        );

        AlertaDao.inserirAlerta(5, maquina.getTotalRam(), servidor.getIdServidor(), conLocal);
    }

    public void inserirDadosRamDisponivel (Servidor servidor, Maquina maquina) {
        con.update("""
        INSERT INTO tb_registro(fk_servidor, fk_cat, data_hora, valor) VALUES(
            %d,
            6,
            GETDATE(),
            %.2f
        );
        """.formatted(servidor.getIdServidor(),
                maquina.getPorcentagemDisponivelRam())
        );

        conLocal.update("""
        INSERT INTO tb_registro VALUES(
            null,
            %d,
            6,
            now(),
            %.2f
        );
        """.formatted(servidor.getIdServidor(),
                maquina.getPorcentagemDisponivelRam())
        );

        AlertaDao.inserirAlerta(6, maquina.getPorcentagemDisponivelRam(), servidor.getIdServidor(), conLocal);
    }
}
