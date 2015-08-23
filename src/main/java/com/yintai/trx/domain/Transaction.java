package com.yintai.trx.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private Integer transactionId;
    private Date createDate;
    private Integer transactionStatus;      // 1代表等待付款 2代表交易失败 3代表交易成功
    private Integer transactionType;        // 1代表支付         2代表退款
    private BigDecimal transactionAmount;
    private Integer currencyType;           // 1代表美元        2代表人民币
    private Client client;
    
    public Transaction() {
        super();
    }

    public Transaction(Integer transactionId, Date createDate, Integer transactionStatus, Integer transactionType,
            BigDecimal transactionAmount, Integer currencyType, Client client) {
        super();
        this.transactionId = transactionId;
        this.createDate = createDate;
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.currencyType = currencyType;
        this.client = client;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(Integer transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Integer getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(Integer currencyType) {
        this.currencyType = currencyType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
