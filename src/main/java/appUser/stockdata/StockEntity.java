package appUser.stockdata;

import lombok.*;
import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class StockEntity {

    @Id
    @SequenceGenerator(
            name = "stock_sequence",
            sequenceName = "stock_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stock_sequence"
    )
    private Long id;
    private Long userID;
    private String ticker;
    private Float amount;

    public StockEntity(Long userid, String ticker, Float amount) {
        this.userID = userid;
        this.ticker = ticker;
        this.amount = amount;
    }
}

