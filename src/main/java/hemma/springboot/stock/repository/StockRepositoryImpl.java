package hemma.springboot.stock.repository;

import hemma.springboot.stock.model.Stock;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

@Repository
public class StockRepositoryImpl implements StockRepository {
    public Collection<Stock> stocks() {
        return Collections.singleton(Stock.builder().id(1000)
                .companyName("Company AB")
                .symbol("CO")
                .price(new BigDecimal("2345"))
                .build());
    }

    public Stock stock(Integer stockId) {
        return Stock.builder().id(1000)
                .companyName("Company AB")
                .symbol("CO")
                .price(new BigDecimal("2345"))
                .build();
    }
}
