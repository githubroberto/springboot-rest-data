package hemma.springboot.stock.response;

import hemma.springboot.stock.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockResponse {
    private final Stock stock;
}
