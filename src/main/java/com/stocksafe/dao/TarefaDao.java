package com.stocksafe.dao;

import com.github.britooo.looca.api.group.processos.Processo;
import com.stocksafe.objeto.Maquina;
import com.stocksafe.objeto.Servidor;
import org.springframework.jdbc.core.JdbcTemplate;

public class TarefaDao {

    private JdbcTemplate con;

    public TarefaDao(JdbcTemplate con) {
        this.con = con;
    }

    public void inserirDadosProcessos(Servidor servidor, Maquina maquina) {

        for (Processo processo : maquina.getProcessos()) {
            con.update("""
            INSERT INTO tb_tarefa VALUES(
            null,
            %d,
            '%s',
            now(),
            %.2f,
            %.2f,
            %d,
            %d,
            %d
            );
            """.formatted(
                    processo.getPid(),
                    processo.getNome(),
                    processo.getUsoCpu(),
                    processo.getUsoMemoria(),
                    processo.getBytesUtilizados(),
                    processo.getMemoriaVirtualUtilizada(),
                    servidor.getIdServidor()
                    )
            );
        }
    }
}

