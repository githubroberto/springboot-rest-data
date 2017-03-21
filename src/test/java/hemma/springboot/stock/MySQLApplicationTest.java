package hemma.springboot.stock;

import hemma.springboot.stock.controller.StockController;
import hemma.springboot.stock.response.ResponseMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MySQLTestConfiguration.class})
public class MySQLApplicationTest {

    @Autowired
    private StockController stockController;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }

    @Test
    @Sql(scripts = {"/dropAndCreateTables.sql", "/loadTestData.sql"})
    public void should_get_stocks_one_element() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/stocks")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseCode", is(HttpStatus.OK.value())))
                .andExpect(jsonPath("$.responseMessage", is(ResponseMessage.successful.getValue())))
                .andExpect(jsonPath("$.responseBody", is(notNullValue())))
                .andExpect(jsonPath("$.responseBody.stocks", is(notNullValue())))
                .andExpect(jsonPath("$.responseBody.stocks.length()", is(3)));
    }
}