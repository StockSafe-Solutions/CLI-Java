package com.stocksafe.dao;

import com.github.britooo.looca.api.group.processos.Processo;
import com.stocksafe.objeto.Maquina;
import com.stocksafe.objeto.Servidor;
import com.stocksafe.utils.Conversor;
import org.springframework.jdbc.core.JdbcTemplate;

public class ProcessoDao {

    private JdbcTemplate con;

    public ProcessoDao(JdbcTemplate con) {
        this.con = con;
    }

    public void inserirDadosProcessos(Servidor servidor, Maquina maquina) {

        String sql = """
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

        con.batchUpdate(sql, maquina.getProcessos(), 100, (ps, processo) -> {
            ps.setInt(1, processo.getPid());
            ps.setString(2, processo.getNome());
            ps.setDouble(3, processo.getUsoCpu());
            ps.setDouble(4, processo.getUsoMemoria());
            ps.setDouble(5, Conversor.converteMb(processo.getBytesUtilizados()));
            ps.setDouble(6, Conversor.converteMb(processo.getMemoriaVirtualUtilizada()));
            ps.setInt(7, servidor.getIdServidor());
        });
    }
}


