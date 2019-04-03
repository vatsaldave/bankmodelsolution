package workshop.domain.prep;

import workshop.domain.Transaction;
import workshop.enums.TransactionStatus;
import workshop.enums.TransactionType;
import workshop.exception.InSufficientBalanceException;
import workshop.utils.Utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

/**
 * This class represent a bank account. It stores the balance and transaction list in memory.
 */
public class BankAccount {
    private final Integer bankAccountNumber;
    private final BigDecimal openingBalance;
    private final LinkedList<Transaction> transactionList;
    private BigDecimal currentBalance;

    /**
     * This is the constructor for bank account.
     *
     * @param bankAccountNumber     the account number
     * @param accountOpeningBalance balance with which the account is opened
     */
    public BankAccount(Integer bankAccountNumber, String accountOpeningBalance) {
        this.bankAccountNumber = bankAccountNumber;
        this.openingBalance = new BigDecimal(accountOpeningBalance, MathContext.DECIMAL128);
        this.currentBalance = new BigDecimal("0", MathContext.DECIMAL128);
        transactionList = new LinkedList<Transaction>();
        BigDecimal transactionAmount = new BigDecimal(accountOpeningBalance, MathContext.DECIMAL128);
        Transaction transaction = new Transaction(TransactionType.CREDIT, transactionAmount, this.currentBalance);
        this.transactionList.add(transaction);
        this.currentBalance = transaction.getBalanceAfterTransaction();
    }

    /**
     * @param o BankAccount object to compare to
     * @return true if the arugment bank account object is having same bacn account number as this object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankAccount)) return false;
        BankAccount that = (BankAccount) o;
        return bankAccountNumber.equals(that.bankAccountNumber);
    }

    /**
     * @return integer hashcode
     */
    @Override
    public int hashCode() {
        return bankAccountNumber.hashCode();
    }

    /**
     * @return bank account number
     */
    public Integer getBankAccountNumber() {
        return bankAccountNumber;
    }

    /**
     * @return opening balance with which the account was opened
     */
    public String getOpeningBalance() {
        return Utils.getDecimalFormat("##,##,##,###", 0, 2).format(openingBalance);
    }

    /**
     * @return current account balance
     */
    public synchronized String getCurrentBalance() {
        return Utils.getDecimalFormat("##,##,##,###", 0, 2).format(currentBalance);
    }

    /**
     * This method is synchronized and thread safe.
     *
     * @param amount The amount to be deposited to this account.
     * @return Current balance after the deposit
     */
    public synchronized String deposit(String amount) {
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
    public synchronized String withdraw(String amount) throws InSufficientBalanceException {
        BigDecimal transactionAmount = new BigDecimal(amount, MathContext.DECIMAL128);
        transact(TransactionType.DEBIT, transactionAmount);
        if (TransactionStatus.FAILURE == this.transactionList.peekLast().getStatus()) {
            throw new InSufficientBalanceException("Insufficient funds to perform transaction");
        }
        return getCurrentBalance();
    }

    /**
     * This method returns the list of transaction since the bank account was created. The list is sorted.
     *
     * @return the list of transactions since the bank account object was created.
     */
    public synchronized List<Transaction> getTransactionList() {
        List<Transaction> retVal = new ArrayList<Transaction>(this.transactionList);
        Collections.sort(retVal);
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
        Transaction newTransaction = new Transaction(transactionType, transactionAmount, this.currentBalance);
        this.transactionList.add(newTransaction);
        if (TransactionStatus.SUCCESS == newTransaction.getStatus()) {
            this.currentBalance = newTransaction.getBalanceAfterTransaction();
        }
        reconcile();
    }

    /**
     * This method will reconcile the balance since the bank account is opened.
     * The method will take account opening balance into consideration and apply
     * each successful transaction on it to derive current balance.
     * If the derived value does not match the current balance which is
     * not common scenario a RuntimeException is thrown
     */
    private void reconcile() {
        BigDecimal amountToCompare = new BigDecimal("0", MathContext.DECIMAL128);
        ListIterator<Transaction> iterator = this.transactionList.listIterator();
        while (iterator.hasNext()) {
            Transaction transaction = iterator.next();
            if (TransactionStatus.SUCCESS == transaction.getStatus()) {
                if (TransactionType.CREDIT == transaction.getTransactionType()) {
                    amountToCompare = amountToCompare.add(transaction.getTransactionAmount());
                } else if (TransactionType.DEBIT == transaction.getTransactionType()) {
                    amountToCompare = amountToCompare.subtract(transaction.getTransactionAmount());
                }
            }
        }
        if (!amountToCompare.equals(currentBalance)) {
            throw new RuntimeException("ERROR couldn't reconcile Account transactions");
        }
    }
}
