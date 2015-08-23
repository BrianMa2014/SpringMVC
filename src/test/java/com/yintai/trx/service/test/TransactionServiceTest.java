package com.yintai.trx.service.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.yintai.trx.domain.Client;
import com.yintai.trx.domain.Transaction;
import com.yintai.trx.service.TransactionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:WEB-INF/dispatcher-servlet.xml")
public class TransactionServiceTest {

    @Autowired
    TransactionService trxService;

    @Test
    public void testAddTransaction() {
        Client client = new Client(null, "yintai");
        Transaction trx = new Transaction(null, new Date(), 1, 1, new BigDecimal(86.5), 2, client);

        trxService.addTransaction(trx);
    }

    @Test
    public void testGetTransactionList() {
        Client client = new Client(null, "yintai");
        Transaction trx = new Transaction(null, new Date(), 1, 1, new BigDecimal(86.5), 2, client);

        trxService.addTransaction(trx);

        Client client2 = new Client(null, "yintai");
        Transaction trx2 = new Transaction(null, new Date(), 2, 1, new BigDecimal(86.5), 2, client2);

        trxService.addTransaction(trx2);

        List<Transaction> transactions = trxService.getTransactionsByStatus(1);
        Assert.notEmpty(transactions);
        Assert.isTrue(transactions.size() == 1);
    }

    @Test
    public void testGetAllTransactionList() {
        Client client = new Client(null, "yintai");
        Transaction trx = new Transaction(null, new Date(), 1, 1, new BigDecimal(86.5), 2, client);

        trxService.addTransaction(trx);

        Client client2 = new Client(null, "yintai");
        Transaction trx2 = new Transaction(null, new Date(), 2, 1, new BigDecimal(86.5), 2, client2);

        trxService.addTransaction(trx2);

        List<Transaction> transactions = trxService.getTransactionsByStatus(-1);
        Assert.notEmpty(transactions);
        Assert.isTrue(transactions.size() == 2);
    }
}
