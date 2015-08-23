package com.yintai.trx.service.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.yintai.trx.service.TransactionService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:WEB-INF/dispatcher-servlet.xml")
public class TransactionControllerTest {

    @Autowired
    WebApplicationContext wac;
    private MockMvc mockMvc;

    @Autowired
    TransactionService trxService;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /*private Integer transactionId;
    private Date createDate;
    private Integer transactionStatus;      // 1代表等待付款 2代表交易失败 3代表交易成功
    private Integer transactionType;        // 1代表支付         2代表退款
    private BigDecimal transactionAmount;
    private Integer currencyType;           // 1代表美元        2代表人民币
    private Client client;*/
    
    @Test
    public void testAddTransaction() {
        try {
            
            // 验证交易状态为等待付款，并且交易的金额是86.5
            MvcResult result = mockMvc.perform(post("/trx/save")
                    .param("createDate", "2015/06/19")
                    .param("transactionStatus", "1")
                    .param("transactionType", "1")
                    .param("transactionAmount", "86.5")
                    .param("currencyType", "2")
                    .param("client.clientName", "张三")
            )
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(jsonPath("$.transactionStatus", is(1)))
            .andExpect(jsonPath("$.transactionAmount", is(86.5)))
            .andDo(print())
            .andReturn();
            
            Assert.assertNotNull(result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testGetAllTransaction() {
        try {
            // 三条交易成功
            mockMvc.perform(post("/trx/save")
                    .param("createDate", "2015/06/19")
                    .param("transactionStatus", "3")
                    .param("transactionType", "1")
                    .param("transactionAmount", "86.5")
                    .param("currencyType", "2")
                    .param("client.clientName", "张三1")
            );
            mockMvc.perform(post("/trx/save")
                    .param("createDate", "2015/06/19")
                    .param("transactionStatus", "3")
                    .param("transactionType", "1")
                    .param("transactionAmount", "10.20")
                    .param("currencyType", "2")
                    .param("client.clientName", "张三2")
            );
            mockMvc.perform(post("/trx/save")
                    .param("createDate", "2015/06/19")
                    .param("transactionStatus", "3")
                    .param("transactionType", "1")
                    .param("transactionAmount", "1000")
                    .param("currencyType", "1")
                    .param("client.clientName", "张三3")
            );
            // 两条交易失败
            mockMvc.perform(post("/trx/save")
                    .param("createDate", "2015/06/19")
                    .param("transactionStatus", "2")
                    .param("transactionType", "1")
                    .param("transactionAmount", "9.99")
                    .param("currencyType", "2")
                    .param("client.clientName", "张三4")
            );
            mockMvc.perform(post("/trx/save")
                    .param("createDate", "2015/06/19")
                    .param("transactionStatus", "2")
                    .param("transactionType", "1")
                    .param("transactionAmount", "10.00")
                    .param("currencyType", "1")
                    .param("client.clientName", "张三5")
            );

            // 验证全部状态共5条
            MvcResult result = mockMvc.perform(get("/trx/list")
                    .param("status", "-1")
                    ).andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andExpect(jsonPath("$", hasSize(5)))
                    .andDo(print())
                    .andReturn();
            
            Assert.assertNotNull(result);
            
            // 验证成功状态共3条
            result = mockMvc.perform(get("/trx/list")
                    .param("status", "3")
                    ).andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andExpect(jsonPath("$", hasSize(3)))
                    .andDo(print())
                    .andReturn();
            
            Assert.assertNotNull(result);
            
            // 验证失败状态共2条
            result = mockMvc.perform(get("/trx/list")
                    .param("status", "2")
                    ).andExpect(status().isOk())
                    .andExpect(content().contentType("application/json;charset=UTF-8"))
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andDo(print())
                    .andReturn();
            
            Assert.assertNotNull(result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
