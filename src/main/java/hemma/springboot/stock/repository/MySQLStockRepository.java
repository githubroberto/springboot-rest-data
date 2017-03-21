package hemma.springboot.stock.repository;

import hemma.springboot.stock.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class MySQLStockRepository implements StockRepository {
    private static final String GET_STOCK_SQL = "SELECT stock_id, company_name, symbol, price from stock where stock_id = ?";
    private static final String GET_STOCKS_SQL = "SELECT stock_id, company_name, symbol, price from stock";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MySQLStockRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Collection<Stock> stocks() throws Exception {
        return jdbcTemplate.query(GET_STOCKS_SQL,
                new Object[]{},
                (resultSet, i) -> rowToStock(resultSet));
    }

    public Stock stock(Integer stockId) throws Exception {
        return jdbcTemplate.queryForObject(GET_STOCK_SQL,
                new Object[]{stockId},
                (resultSet, i) -> rowToStock(resultSet));
    }

    private Stock rowToStock(ResultSet resultSet) throws SQLException {
        return Stock.builder()
                .id(resultSet.getInt("stock_id"))
                .companyName(resultSet.getString("company_name"))
                .symbol(resultSet.getString("symbol"))
                .price(resultSet.getBigDecimal("price"))
                .build();
    }
}
