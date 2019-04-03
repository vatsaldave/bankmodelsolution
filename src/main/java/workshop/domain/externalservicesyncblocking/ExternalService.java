package workshop.domain.externalservicesyncblocking;

import workshop.enums.TransactionType;

import java.math.BigDecimal;

/**
 * This class simulates external call that takes time to execute
 */
public class ExternalService {

    public void logTransaction(TransactionType transactionType, BigDecimal transactionAmount) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
