package hemma.springboot.stock.controller;

import hemma.springboot.stock.model.Stock;
import hemma.springboot.stock.response.APIResponse;
import hemma.springboot.stock.response.ResponseMessage;
import hemma.springboot.stock.response.StockResponse;
import hemma.springboot.stock.response.StocksResponse;
import hemma.springboot.stock.service.StockService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Collection;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/stocks")
public class StockController {
    private static final Logger LOGGER = getLogger(StockController.class);

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public APIResponse<StocksResponse> stocks() {
        LOGGER.info("Requesting stocks");
        final Collection<Stock> stocks = stockService.stocks();
        StocksResponse stocksResponse = new StocksResponse(stocks);
        LOGGER.info("Found stocks: " + stocksResponse.size());
        return APIResponse.<StocksResponse>apiResponse()
                .responseCode(HttpStatus.OK.value())
                .responseMessage(ResponseMessage.successful.getValue())
                .responseBody(stocksResponse).build();
    }

    @RequestMapping(value = {"/{stockId}"}, method = RequestMethod.GET)
    public APIResponse<StockResponse> stock(@PathVariable("stockId") @NotNull @Min(1) Integer stockId) {
        LOGGER.info("Requesting stock");
        final Stock stock = stockService.stock(stockId);
        StockResponse stockResponse = new StockResponse(stock);
        LOGGER.info("Found stock: " + stockResponse);
        return APIResponse.<StockResponse>apiResponse()
                .responseCode(HttpStatus.OK.value())
                .responseMessage(ResponseMessage.successful.getValue())
                .responseBody(stockResponse).build();
    }
}