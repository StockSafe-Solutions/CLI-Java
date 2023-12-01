package com.stocksafe.dao;

import com.stocksafe.objeto.Funcionario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FuncionarioDao {
    private static JdbcTemplate con;

    public FuncionarioDao(JdbcTemplate con) {
        this.con = con;
    }

    public static List<Funcionario> listar () {
        return con.query("SELECT id_funcionario, nome, email FROM tb_funcionario;",
                new BeanPropertyRowMapper<>(Funcionario.class));
    }

    public static List<Funcionario> getFuncionarioPorLogin (Funcionario funcionario) {
        return con.query("SELECT * FROM tb_funcionario WHERE email = ? AND senha = ?",
                new BeanPropertyRowMapper<>(Funcionario.class), funcionario.getEmail(), funcionario.getSenha());
    }
}
