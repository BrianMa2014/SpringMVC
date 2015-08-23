package com.yintai.trx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yintai.trx.domain.Tenant;
import com.yintai.trx.domain.Transaction;
import com.yintai.trx.service.TransactionService;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService trxService;

    @RequestMapping(value="/trx/save", method=RequestMethod.GET)
    @ResponseBody
    public Transaction saveTransaction(Transaction trx) {
        Transaction transaction = trxService.addTransaction(trx);
        return transaction;
    }
    
    @RequestMapping(value="/tenant/save", method=RequestMethod.POST,
    		headers = {"content-type=application/json","content-type=application/xml"})
    @ResponseBody
    public boolean saveTenant(@RequestBody Tenant tenant) {
//        boolean flag = trxService.addTenant(tenant);
    	System.out.println(tenant);
        return true;
    }
    
    @RequestMapping(value="/tenant/getTenantById", method=RequestMethod.GET)
    @ResponseBody
    public Tenant getTenantById(int tenantId) {
//        boolean flag = trxService.addTenant(tenant);
    	System.out.println(tenantId);
    	Tenant tenant = new Tenant(1001,"商户Name","2015-07-01","联系人contact","地址address");
    	
        return tenant;
    }

    @RequestMapping(value="/trx/list", method=RequestMethod.GET)
    @ResponseBody
    public List<Transaction> showTransaction(int status) {
        List<Transaction> transactions = trxService
                .getTransactionsByStatus(status);

        return transactions;
    }
}
