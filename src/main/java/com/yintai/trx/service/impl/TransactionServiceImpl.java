package com.yintai.trx.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yintai.trx.datasource.TransactionStore;
import com.yintai.trx.domain.Client;
import com.yintai.trx.domain.Tenant;
import com.yintai.trx.domain.Transaction;
import com.yintai.trx.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public Transaction addTransaction(Transaction trx) {
        trx.setTransactionId(TransactionStore.getNextId());
        Client client = trx.getClient();
        // 生成自增长的ID
        if (client == null) {
            client = new Client(TransactionStore.getNextId(), "");
        } else {
            client.setClientId(TransactionStore.getNextId());
        }
        TransactionStore.save(trx);
        
        return trx;
    }

    // 根据交易状态返回交易列表
    @Override
    public List<Transaction> getTransactionsByStatus(int status) {
        // 如果status为-1 返回所有交易状态的交易列表
        if (status == -1) {
            return TransactionStore.loadAllTransactionHistory();
        }
        return TransactionStore.loadTransactionByStatus(status);
    }

	@Override
	public boolean addTenant(Tenant tenant) {
		// TODO Auto-generated method stub
		return false;
	}

}
