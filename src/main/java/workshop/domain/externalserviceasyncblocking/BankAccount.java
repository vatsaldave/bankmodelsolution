package workshop.domain.externalserviceasyncblocking;

import workshop.domain.Transaction;
import workshop.enums.TransactionStatus;
import workshop.enums.TransactionType;
import workshop.exception.InSufficientBalanceException;
import workshop.utils.Utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * This class represent a bank account. It stores the balance and transaction list in memory.
 */
public class BankAccount {
    private final AtomicReference<BankAccountData> bankAccountData = new AtomicReference<>();
    private ExternalService externalService;

    /**
     * This is the constructor for bank account.
     */
    public BankAccount(ExternalService service) {
        bankAccountData.set(new BankAccountData(BigDecimal.ZERO, new LinkedList<>()));
        this.externalService = service;
    }


    /**
     * @return current account balance
     */
    public String getCurrentBalance() {
        return Utils.getDecimalFormat("##,##,##,###", 0, 2).format(bankAccountData.get().getCurrentBalance());
    }

    /**
     * This method is synchronized and thread safe.
     *
     * @param amount The amount to be deposited to this account.
     * @return Current balance after the deposit
     */
    public String deposit(String amount) {
        BigDecimal transactionAmount = new BigDecimal(amount, MathContext.DECIMAL128);
        transact(TransactionType.CREDIT, transactionAmount);
        return getCurrentBalance();
    }

    /**
     * This method is synchronized and thread safe.
     *
     * @param amount The amount to be withdrawn from this account
     * @return Current balance after withdrawal
     * @throws InSufficientBalanceException - The exception is thrown in case there is insufficient balance to perform the withdrawal
     */
    public String withdraw(String amount) throws InSufficientBalanceException {
        BigDecimal transactionAmount = new BigDecimal(amount, MathContext.DECIMAL128);
        transact(TransactionType.DEBIT, transactionAmount);
        if (TransactionStatus.FAILURE == bankAccountData.get().getTransactionList().peekLast().getStatus()) {
            throw new InSufficientBalanceException("Insufficient funds to perform transaction");
        }
        return getCurrentBalance();
    }

    /**
     * This method returns the list of transaction since the bank account was created. The list is sorted.
     *
     * @return the list of transactions since the bank account object was created.
     */
    public List<Transaction> getTransactionList() {
        List<Transaction> retVal = new ArrayList<Transaction>(bankAccountData.get().getTransactionList());
//        Collections.sort(retVal);
        return retVal;
    }

    /**
     * This method is used to perfrom transaction on bank account.
     * Internal method used from deposit and withdraw methods.
     *
     * @param transactionType   if the tranasction is DEBIT or CREDIT
     * @param transactionAmount the amount for which transaction is done.
     */
    private void transact(TransactionType transactionType, BigDecimal transactionAmount) {
        externalService.logTransaction(transactionType, transactionAmount);
        bankAccountData.updateAndGet(e -> {
            Transaction newTransaction = new Transaction(transactionType, transactionAmount, e.getCurrentBalance());
            LinkedList<Transaction> transactionList = new LinkedList<>();
            transactionList.addAll(e.getTransactionList());
            transactionList.add(newTransaction);
            return new BankAccountData(newTransaction.getBalanceAfterTransaction(), transactionList);
        });
    }
}
