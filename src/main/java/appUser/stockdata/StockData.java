package appUser.stockdata;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class StockData {

    private Long userID;
    private Float amount;
    private String ticker, fullName, exchange;
    private BigDecimal price, dividend, change;

    public StockData(String ticker, Float amount) {
        this.ticker = ticker;
        this.amount = amount;
        Stock myStock = getStock();
        this.fullName = myStock.getName();
        this.exchange = myStock.getStockExchange();
        this.price = myStock.getQuote().getPrice();
        this.dividend = myStock.getDividend().getAnnualYieldPercent();
        this.change = myStock.getQuote().getChangeInPercent();
    }

    @JsonIgnore
    public Stock getStock(){
        try{
            return YahooFinance.get(this.getTicker());
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
