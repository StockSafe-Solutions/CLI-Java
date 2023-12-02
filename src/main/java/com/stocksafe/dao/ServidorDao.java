package com.stocksafe.dao;

import com.stocksafe.objeto.Servidor;
import com.stocksafe.objeto.Funcionario;
import com.stocksafe.objeto.Opcoes;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ServidorDao {
    private final JdbcTemplate con;
    private final JdbcTemplate conLocal;

    public ServidorDao(JdbcTemplate con, JdbcTemplate conLocal) {
        this.con = con;
        this.conLocal = conLocal;
    }

    public List<Servidor> selecionarServidor (Opcoes opcoes) {
        return conLocal.query("SELECT id_servidor, codigo FROM tb_servidor WHERE codigo = ?;", new BeanPropertyRowMapper<>(Servidor.class), opcoes.getCodigo());
    }

    public void autenticarServidor (Servidor servidor, Funcionario funcionario) {
//        con.update("UPDATE tb_servidor SET id_autenticador = ? WHERE codigo = ?"
//                , funcionario.getIdFuncionario(), servidor.getCodigo());

        conLocal.update("UPDATE tb_servidor SET id_autenticador = ? WHERE codigo = ?"
                , funcionario.getIdFuncionario(), servidor.getCodigo());
    }

    public void atualizarArmazenamento (Servidor servidor, Double armazenamentoTotal, Double armazenamentoUsado) {
//        con.update("UPDATE tb_servidor SET armazenamento_total = ?, armazenamento_usado = ? WHERE codigo = ?"
//                , armazenamentoTotal, armazenamentoUsado, servidor.getCodigo());

        conLocal.update("UPDATE tb_servidor SET armazenamento_total = ?, armazenamento_usado = ? WHERE codigo = ?"
                , armazenamentoTotal, armazenamentoUsado, servidor.getCodigo());
    }
}
