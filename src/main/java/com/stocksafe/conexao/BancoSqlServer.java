package com.stocksafe.conexao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class BancoSqlServer{

    private final JdbcTemplate conexaoDoBanco;

    public BancoSqlServer() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://44.221.134.15:1433;user=sa;databaseName=StockSafe;password=urubu100;encrypt=false");
        dataSource.setUsername("sa");
        dataSource.setPassword("urubu100");

        this.conexaoDoBanco = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoDoBanco() {
        return conexaoDoBanco;
    }
}
