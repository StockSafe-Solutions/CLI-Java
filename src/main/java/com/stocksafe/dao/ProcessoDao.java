package com.stocksafe.dao;

import com.stocksafe.objeto.Maquina;
import com.stocksafe.objeto.Servidor;
import com.stocksafe.utils.Conversor;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProcessoDao {

    private final JdbcTemplate con;
    private final JdbcTemplate conLocal;

    public ProcessoDao(JdbcTemplate con, JdbcTemplate conLocal) {
        this.con = con;
        this.conLocal = conLocal;
    }

    public void inserirDadosProcessos(Servidor servidor, Maquina maquina) {

        String sqlServ = """
        INSERT INTO tb_processo( 
        pid_proc,
        nome_proc,
        data_hora,
        uso_cpu,
        uso_ram,
        uso_bytes_mb,
        uso_memoria_virtual_mb,
        fk_servidor)
        VALUES(
        ?,
        ?,
        GETDATE(),
        ?,
        ?,
        ?,
        ?,
        ?
        )
        """;

        con.batchUpdate(sqlServ, maquina.getProcessos(), 100, (ps, processo) -> {
            ps.setInt(1, processo.getPid());
            ps.setString(2, processo.getNome());
            ps.setDouble(3, (processo.getUsoCpu()) / 10);
            ps.setDouble(4, processo.getUsoMemoria());
            ps.setDouble(5, Conversor.converteMb(processo.getBytesUtilizados()));
            ps.setDouble(6, Conversor.converteMb(processo.getMemoriaVirtualUtilizada()));
            ps.setInt(7, servidor.getIdServidor());
        });

        String mySql = """
        INSERT INTO tb_processo VALUES(
        null,
        ?,
        ?,
        now(),
        ?,
        ?,
        ?,
        ?,
        ?
        )
        """;

        conLocal.batchUpdate(mySql, maquina.getProcessos(), 100, (ps, processo) -> {
            ps.setInt(1, processo.getPid());
            ps.setString(2, processo.getNome());
            ps.setDouble(3, (processo.getUsoCpu() / 10));
            ps.setDouble(4, processo.getUsoMemoria());
            ps.setDouble(5, Conversor.converteMb(processo.getBytesUtilizados()));
            ps.setDouble(6, Conversor.converteMb(processo.getMemoriaVirtualUtilizada()));
            ps.setInt(7, servidor.getIdServidor());
        });
    }
}


