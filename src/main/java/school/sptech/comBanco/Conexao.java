package school.sptech.comBanco;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Conexao {

    private JdbcTemplate conxaoDoBanco;

    public Conexao(){

        // Informações banco de dados
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/StockSafe");
        dataSource.setUsername("stockSafe");
        dataSource.setPassword("urubu100");

        conxaoDoBanco = new JdbcTemplate(dataSource);

    }

    public JdbcTemplate getConxaoDoBanco() {
        return conxaoDoBanco;
    }
}
