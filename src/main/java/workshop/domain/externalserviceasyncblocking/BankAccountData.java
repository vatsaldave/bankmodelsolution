package workshop.domain.externalserviceasyncblocking;

import workshop.domain.Transaction;

import java.math.BigDecimal;
import java.util.LinkedList;

/**
 * This class holds volatile data for bankaccount
 * Will be used as a type reference to hold data in thread safe atomic reference
 */
public final class BankAccountData {
    private final LinkedList<Transaction> transactionList;
    private final BigDecimal currentBalance;

    public BankAccountData(BigDecimal currentBalance, LinkedList<Transaction> transactionList) {
        this.currentBalance = currentBalance;
        this.transactionList = transactionList;
    }

    public LinkedList<Transaction> getTransactionList() {
        return transactionList;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

}
