package hemma.springboot.stock.service;

import hemma.springboot.stock.model.Stock;
import hemma.springboot.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Collection<Stock> stocks() {
        try {
            return stockRepository.stocks();
        } catch (Exception exception) {
            return Collections.emptyList();
        }
    }

    public Stock stock(Integer stockId) {
        try {
            return stockRepository.stock(stockId);
        } catch (Exception exception) {
            return null;
        }
    }
}
