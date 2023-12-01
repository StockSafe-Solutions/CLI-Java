package com.stocksafe.conexao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class BancoSqlServer{

    private final JdbcTemplate conexaoDoBanco;

    public BancoSqlServer() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://54.164.215.197:1433;databaseName=StockSafe;encrypt=false;trustServerCertificate=false");
        dataSource.setUsername("sa");
        dataSource.setPassword("urubu100");

        this.conexaoDoBanco = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoDoBanco() {
        return conexaoDoBanco;
    }
}
