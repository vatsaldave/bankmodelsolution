package workshop.domain;

import workshop.enums.TransactionStatus;
import workshop.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Thie class represent a transaction that can be performed on BankAccount.
 * The object of this class is immutable.
 */
public final class Transaction implements Comparable<Transaction> {

    private final String tranactionId;
    private final TransactionType transactionType;
    private final BigDecimal transactionAmount;
    private final BigDecimal balanceBeforeTransaction;
    private final BigDecimal balanceAfterTransaction;
    private final TransactionStatus status;
    private final LocalDateTime transactionDate;

    public Transaction(TransactionType transactionType, BigDecimal transactionAmount, BigDecimal currentBalance) {
        BigDecimal temp = null;
        TransactionStatus tempStatus = TransactionStatus.SUCCESS;
        tranactionId = UUID.randomUUID().toString();
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDate = LocalDateTime.now();
        this.balanceBeforeTransaction = currentBalance;
        if (TransactionType.CREDIT == transactionType) {
            temp = this.balanceBeforeTransaction.add(transactionAmount);
        } else if (TransactionType.DEBIT == transactionType) {
            temp = this.balanceBeforeTransaction.subtract(transactionAmount);
        }
//        if (temp.signum() < 0) {
//            tempStatus = TransactionStatus.FAILURE;
//            temp = balanceBeforeTransaction;
//        }
        this.balanceAfterTransaction = temp;
        this.status = tempStatus;
    }

    public String getTranactionId() {
        return tranactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public BigDecimal getBalanceBeforeTransaction() {
        return balanceBeforeTransaction;
    }

    public BigDecimal getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public int compareTo(Transaction o) {
        return this.transactionDate.compareTo(o.transactionDate);
    }

    /**
     * The equals method takes into consideration
     * the transactionType, transactionAmount, balanceBeforeTransaction,
     * balanceAfterTransaction and status
     *
     * @param o another Transaction object to compare to
     * @return true if the objects is equal to this object false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return transactionType == that.transactionType &&
                transactionAmount.equals(that.transactionAmount) &&
                balanceBeforeTransaction.equals(that.balanceBeforeTransaction) &&
                balanceAfterTransaction.equals(that.balanceAfterTransaction) &&
                status == that.status;
//                && transactionDate.equals(that.transactionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionType, transactionAmount, balanceBeforeTransaction, balanceAfterTransaction, status);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "tranactionId='" + tranactionId + '\'' +
                ", transactionType=" + transactionType +
                ", transactionAmount=" + transactionAmount +
                ", balanceBeforeTransaction=" + balanceBeforeTransaction +
                ", balanceAfterTransaction=" + balanceAfterTransaction +
                ", status=" + status +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
