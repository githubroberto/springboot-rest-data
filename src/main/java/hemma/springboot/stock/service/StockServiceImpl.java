package hemma.springboot.stock.service;

import hemma.springboot.stock.model.Stock;
import hemma.springboot.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Collection<Stock> stocks() {
        return stockRepository.stocks();
    }

    public Stock stock(Integer stockId) {
        return stockRepository.stock(stockId);
    }
}
