package hemma.springboot.stock;

import hemma.springboot.stock.controller.StockController;
import hemma.springboot.stock.repository.MySQLStockRepository;
import hemma.springboot.stock.repository.StockRepository;
import hemma.springboot.stock.service.StockService;
import hemma.springboot.stock.service.StockServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class MySQLTestConfiguration {

    @Bean
    public StockController stockController() {
        return new StockController(stockService());
    }

    @Bean
    public StockService stockService() {
        return new StockServiceImpl(stockRepository());
    }

    @Bean
    public StockRepository stockRepository() {
        return new MySQLStockRepository(jdbcTemplate());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/test_schema?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
}
