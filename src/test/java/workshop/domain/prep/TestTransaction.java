package workshop.domain.prep;

import org.junit.Assert;
import org.junit.Test;
import workshop.domain.Transaction;
import workshop.enums.TransactionStatus;
import workshop.enums.TransactionType;

import java.math.BigDecimal;
import java.math.MathContext;


public class TestTransaction {

    @Test
    public void testTransactionToDepositMoney() {
        BigDecimal transactionAmount = new BigDecimal("123", MathContext.DECIMAL128);
        BigDecimal currentBalance = new BigDecimal("123", MathContext.DECIMAL128);
        BigDecimal newBalance = new BigDecimal("246", MathContext.DECIMAL128);
        Transaction transaction = new Transaction(TransactionType.CREDIT, transactionAmount, currentBalance);
        Assert.assertEquals(TransactionStatus.SUCCESS, transaction.getStatus());
        Assert.assertEquals(newBalance, transaction.getBalanceAfterTransaction());
    }

    @Test
    public void testTransactionToWithdrawMoneySuccess() {
        BigDecimal transactionAmount = new BigDecimal("13", MathContext.DECIMAL128);
        BigDecimal currentBalance = new BigDecimal("123", MathContext.DECIMAL128);
        BigDecimal newBalance = new BigDecimal("110", MathContext.DECIMAL128);
        Transaction transaction = new Transaction(TransactionType.DEBIT, transactionAmount, currentBalance);
        Assert.assertEquals(TransactionStatus.SUCCESS, transaction.getStatus());
        Assert.assertEquals(newBalance, transaction.getBalanceAfterTransaction());
    }

    @Test
    public void testTransactionToWithdrawMoneyFailure() {
        BigDecimal transactionAmount = new BigDecimal("125", MathContext.DECIMAL128);
        BigDecimal currentBalance = new BigDecimal("123", MathContext.DECIMAL128);
        Transaction transaction = new Transaction(TransactionType.DEBIT, transactionAmount, currentBalance);
        Assert.assertEquals(TransactionStatus.FAILURE, transaction.getStatus());
        Assert.assertEquals(currentBalance, transaction.getBalanceAfterTransaction());
    }
}
