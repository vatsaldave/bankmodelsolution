package workshop.domain.prep;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ConcurrentTestRunner.class)
public class TestBankAccountConcurrentWithdraw {
    private BankAccount bankAccount = null;

    @Before
    public void initialize() {
        Integer bankAccountNumber = 123;
        String accountOpeningBalance = "5000";
        bankAccount = new BankAccount(bankAccountNumber, accountOpeningBalance);
    }

    @Test
    @ThreadCount(5)
    public void testWithdraw() {
        bankAccount.withdraw("500");
    }

    @After
    public void testBalanceAtEnd() {
        Assert.assertEquals("2,500", bankAccount.getCurrentBalance());
    }
}
