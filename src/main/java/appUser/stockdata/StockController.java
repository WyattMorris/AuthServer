package appUser.stockdata;

import appUser.registration.AuthorizationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "stockdata")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    //Single request for the stock entries on the user's portfolio form.
    @CrossOrigin
    @GetMapping(path = "{stockTicker}/{amount}")
    public StockData getSingleStock(@PathVariable("stockTicker") String ticker, @PathVariable("amount") Float amount){
        return stockService.getSingleStock(ticker, amount);
    }

    //Request for the homepage stock table
    @CrossOrigin
    @PostMapping
    public List<StockData> getMultiple(@RequestBody String[] tickers) throws IOException {
        return stockService.getMultipleStocks(tickers);
    }

    //Saving the stocks for a user's profile table
    @CrossOrigin
    @PostMapping(path = "/portfolio/save")
    public void saveStocks(@RequestBody StockEntity[] stocks, @RequestHeader AuthorizationRequest Authorization) {
        stockService.saveStocks(stocks, Authorization);
    }

    //Getting stocks for a user's profile table
    @CrossOrigin
    @GetMapping(path = "/portfolio/stocks")
    public List<StockData> getMyStocks(@RequestHeader AuthorizationRequest Authorization) {
        return stockService.getStocks(Authorization);
    }

}
