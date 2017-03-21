package hemma.springboot.stock.repository;

import hemma.springboot.stock.model.Stock;

import java.util.Collection;

public interface StockRepository {
    Collection<Stock> stocks();

    Stock stock(Integer stockId);
}
