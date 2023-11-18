package com.stocksafe.dao;

import com.stocksafe.objeto.Categoria;
import com.stocksafe.objeto.Maquina;
import com.stocksafe.objeto.Servidor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Timer;
import java.util.TimerTask;

public class MaquinaDao {

    Timer timer = new Timer();

    private JdbcTemplate con;

    public MaquinaDao(JdbcTemplate con) {
        this.con = con;
    }

    // TODO: 18/11/2023 Atualizar a inserção de dados ao novo modelo

    public void inserirDadosPacote (Servidor servidor, Maquina maquina) {
        con.update("""
        INSERT INTO tb_registro VALUES(
            null,
            %d,
            %d,
            now(),
            %d
        );
        """.formatted(servidor.getIdServidor(),
                1, // Categoria Pacotes Enviados
                maquina.getPacotesEnviados().intValue())
        );
    }

    public void inserirDadosCpu (Servidor servidor, Maquina maquina) {
        con.update("""
        INSERT INTO tb_registro VALUES(
            null,
            %d,
            %d,
            now(),
            %d
        );
        """.formatted(servidor.getIdServidor(),
                2, // Categoria Uso da CPU
                maquina.getPorcentagemUsoCpu().intValue())
        );
    }

    public void inserirDadosRam (Servidor servidor, Maquina maquina) {
        con.update("""
        INSERT INTO tb_registro VALUES(
            null,
            %d,
            %d,
            now(),
            %d
        );
        """.formatted(servidor.getIdServidor(),
                3, // Categoria Uso da RAM
                maquina.getPorcentagemUsoRam().intValue())
        );
    }

    public void inserirDadosTransferencia (Servidor servidor, Maquina maquina) {
        con.update("""
        INSERT INTO tb_registro VALUES(
            null,
            %d,
            %d,
            now(),
            %d
        );
        """.formatted(servidor.getIdServidor(),
                4, // Categoria Taxa de transferência
                maquina.getTaxaDeTransferencia().intValue())
        );
    }
}
