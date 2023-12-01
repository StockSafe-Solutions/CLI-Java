package com.stocksafe.dao;

import com.stocksafe.objeto.Funcionario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FuncionarioDao {
    private JdbcTemplate con;
    private JdbcTemplate conLocal;

    public FuncionarioDao(JdbcTemplate con , JdbcTemplate conLocal) {
        this.con = con;
        this.conLocal = conLocal;
    }

    public List<Funcionario> listar () {
        return conLocal.query("SELECT id_funcionario, nome, email FROM tb_funcionario;",
                new BeanPropertyRowMapper<>(Funcionario.class));
    }

    public List<Funcionario> getFuncionarioPorLogin (Funcionario funcionario) {
        return conLocal.query("SELECT * FROM tb_funcionario WHERE email = ? AND senha = ?",
                new BeanPropertyRowMapper<>(Funcionario.class), funcionario.getEmail(), funcionario.getSenha());
    }
}
