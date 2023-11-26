package exe.gba.conexao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoDocker {
    private JdbcTemplate conexaoDoBanco;

    public ConexaoDocker() {
        String dockerIPAddress = "34.205.218.189"; // Replace with actual Docker IP address

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + dockerIPAddress + ":3306/StockSafe");
        dataSource.setUsername("root");
        dataSource.setPassword("urubu100");

        this.conexaoDoBanco = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoDoBanco() {
        return conexaoDoBanco;
    }
}
