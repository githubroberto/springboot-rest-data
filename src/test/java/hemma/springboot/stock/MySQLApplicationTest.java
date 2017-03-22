package hemma.springboot.stock;

import hemma.springboot.stock.controller.StockController;
import hemma.springboot.stock.repository.MySQLStockRepository;
import hemma.springboot.stock.repository.StockRepository;
import hemma.springboot.stock.response.ResponseMessage;
import hemma.springboot.stock.service.StockService;
import hemma.springboot.stock.service.StockServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.sql.DataSource;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@ContextConfiguration(classes = {MySQLTestConfiguration.class})
public class MySQLApplicationTest {

    @Autowired
    private StockController stockController;

    private MockMvc mvc;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Before
    public void setup() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }

    @After
    public void tearDown() throws Exception {
        applicationContext.close();
    }

    @Test
    @Sql(scripts = {"/dropAndCreateTables.sql", "/loadTestData.sql"})
    public void should_get_stocks_one_element() throws Exception {
        TestContextManager testContextManager = new TestContextManager(ConfigurationTest.class);
        testContextManager.prepareTestInstance(this);
        mvc.perform(MockMvcRequestBuilders.get("/stocks")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseCode", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.responseMessage", is(ResponseMessage.successful.getValue())))
                .andExpect(jsonPath("$.responseBody", is(notNullValue())))
                .andExpect(jsonPath("$.responseBody.stocks", is(notNullValue())))
                .andExpect(jsonPath("$.responseBody.stocks.length()", is(3)));
    }

    @ContextConfiguration(classes = {ConfigurationTest.ConfigA.class})
    static class ConfigurationTest {
        static class ConfigA {
            @Bean
            public StockController stockController() {
                return new StockController(stockService());
            }

            @Bean
            public StockService stockService() {
                return new StockServiceImpl(stockRepository());
            }

            @Bean
            public StockRepository stockRepository() {
                return new MySQLStockRepository(jdbcTemplate());
            }

            @Bean
            public JdbcTemplate jdbcTemplate() {
                return new JdbcTemplate(dataSource());
            }

            @Bean
            public DataSource dataSource() {
                DriverManagerDataSource dataSource = new DriverManagerDataSource();
                dataSource.setDriverClassName("com.mysql.jdbc.Driver");
                dataSource.setUrl("jdbc:mysql://localhost/test_schema?useSSL=false");
                dataSource.setUsername("root");
                dataSource.setPassword("");
                return dataSource;
            }
        }
    }
}