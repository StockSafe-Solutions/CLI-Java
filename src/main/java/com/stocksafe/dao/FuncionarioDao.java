package com.stocksafe.dao;

import com.stocksafe.objeto.Funcionario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class FuncionarioDao {
    private final JdbcTemplate con;
    private final JdbcTemplate conLocal;

    public FuncionarioDao(JdbcTemplate con , JdbcTemplate conLocal) {
        this.con = con;
        this.conLocal = conLocal;
    }

    public List<Funcionario> listar () {
        try {
            return con.query("SELECT id_funcionario, nome, email FROM tb_funcionario;",
                    new BeanPropertyRowMapper<>(Funcionario.class));
        } catch (Exception e){
            return conLocal.query("SELECT id_funcionario, nome, email FROM tb_funcionario;",
                    new BeanPropertyRowMapper<>(Funcionario.class));
        }
    }

    public List<Funcionario> getFuncionarioPorLogin (Funcionario funcionario) {
        System.out.println("Tentando conexão...");

        try {
            return con.query("SELECT * FROM tb_funcionario WHERE email = ? AND senha = ?",
                    new BeanPropertyRowMapper<>(Funcionario.class), funcionario.getEmail(), funcionario.getSenha());
        } catch (Exception e) {
            System.out.println("Falha na conexão com o servidor central. Gravando dados na localmente.");
            return conLocal.query("SELECT id_funcionario, nome, email FROM tb_funcionario;",
                    new BeanPropertyRowMapper<>(Funcionario.class));
        }
    }
}
