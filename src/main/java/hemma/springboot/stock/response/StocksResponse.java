package hemma.springboot.stock.response;

import hemma.springboot.stock.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

@Getter
@AllArgsConstructor
public class StocksResponse {
    private final Collection<Stock> stocks;

    public int size() {
        return stocks.size();
    }
}
