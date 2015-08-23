package com.yintai.trx.datasource;

import java.util.ArrayList;
import java.util.List;

import com.yintai.trx.domain.Transaction;

public class TransactionStore {

    // 将交易信息保存于内存中
    private static List<Transaction> transactions = new ArrayList<Transaction>();

    // 自增长ID
    private static int increscentId = 0;

    public static void save(Transaction trx) {
        transactions.add(trx);
    }

    public static List<Transaction> loadTransactionByStatus(int status) {
        List<Transaction> transactionList = new ArrayList<Transaction>();

        for (Transaction trx : transactions) {
            if (trx.getTransactionStatus() == status) {
                transactionList.add(trx);
            }
        }

        return transactionList;
    }

    public static List<Transaction> loadAllTransactionHistory() {
        return transactions;
    }

    public static int getNextId() {
        return ++increscentId;
    }
}
