package hemma.springboot.stock.model;

import lombok.*;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PUBLIC)
public class Stock {
    private Integer id;
    private String companyName;
    private String symbol;
    private BigDecimal price;
}
