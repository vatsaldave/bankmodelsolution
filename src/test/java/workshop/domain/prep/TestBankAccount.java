package workshop.domain.prep;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import workshop.domain.Transaction;
import workshop.enums.TransactionType;
import workshop.exception.InSufficientBalanceException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class TestBankAccount {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testBankAccountToDepositMoney() {
        Integer bankAccountNumber = 123;
        String accountOpeningBalance = "5000";
        BankAccount bankAccount = new BankAccount(bankAccountNumber, accountOpeningBalance);
        bankAccount.deposit("500");
        Assert.assertEquals("5,500", bankAccount.getCurrentBalance());
    }

    @Test
    public void testBankAccountToWithdrawMoneySuccess() {
        Integer bankAccountNumber = 123;
        String accountOpeningBalance = "5000";
        BankAccount bankAccount = new BankAccount(bankAccountNumber, accountOpeningBalance);
        bankAccount.withdraw("500");
        Assert.assertEquals("4,500", bankAccount.getCurrentBalance());
    }

    @Test(expected = InSufficientBalanceException.class)
    public void testBankAccountToWithdrawMoneyFailure() {
        Integer bankAccountNumber = 123;
        String accountOpeningBalance = "5000";
        BankAccount bankAccount = new BankAccount(bankAccountNumber, accountOpeningBalance);
        bankAccount.withdraw("5500");
        Assert.assertEquals("5,000", bankAccount.getCurrentBalance());
    }

    @Test
    public void testBankAccountToGetTransactionList() {
        Integer bankAccountNumber = 123;
        String accountOpeningBalance = "5000";
        BankAccount bankAccount = new BankAccount(bankAccountNumber, accountOpeningBalance);
        bankAccount.deposit("500");
        LinkedList<Transaction> expectedList = new LinkedList<>();
        BigDecimal transactionAmount = new BigDecimal("5000", MathContext.DECIMAL128);
        BigDecimal currentBalance = new BigDecimal("0", MathContext.DECIMAL128);
        Transaction tran1 = new Transaction(TransactionType.CREDIT, transactionAmount, currentBalance);
        transactionAmount = new BigDecimal("500", MathContext.DECIMAL128);
        currentBalance = new BigDecimal("5000", MathContext.DECIMAL128);
        Transaction tran2 = new Transaction(TransactionType.CREDIT, transactionAmount, currentBalance);
        expectedList.add(tran1);
        expectedList.add(tran2);
        Collections.sort(expectedList);
        Assert.assertTrue(compareLinkedListByValueAndPosition(expectedList, bankAccount.getTransactionList()));
    }

    private boolean compareLinkedListByValueAndPosition(List<Transaction> expected, List<Transaction> actual) {
        ListIterator<Transaction> expectedItr = expected.listIterator();
        ListIterator<Transaction> actualItr = actual.listIterator();
        if (expected.size() != actual.size()) {
            return false;
        }
        while (expectedItr.hasNext()) {
            Transaction outer = expectedItr.next();
            if (actualItr.hasNext()) {
                Transaction inner = actualItr.next();
                if (!outer.equals(inner)) {
                    return false;
                }
                actualItr.remove();
            }
        }
        return true;
    }


}
