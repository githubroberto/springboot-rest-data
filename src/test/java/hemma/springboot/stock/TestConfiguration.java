package hemma.springboot.stock;

import hemma.springboot.stock.controller.StockController;
import hemma.springboot.stock.repository.StockRepository;
import hemma.springboot.stock.repository.StockRepositoryImpl;
import hemma.springboot.stock.service.StockService;
import hemma.springboot.stock.service.StockServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// Disabled because it is the default configuration and it would be used instead of the static configuration in test class
//@Configuration
public class TestConfiguration {

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
        return new StockRepositoryImpl();
    }
}
