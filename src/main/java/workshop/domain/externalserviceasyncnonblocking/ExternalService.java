package workshop.domain.externalserviceasyncnonblocking;

import workshop.enums.TransactionType;

import java.math.BigDecimal;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This class simulates external call that takes time to execute
 * The external call will be executed in a new thread and the thread will sleep for 1 sec
 */
public class ExternalService {

    private ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

    public void logTransaction(TransactionType transactionType, BigDecimal transactionAmount) {
        service.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("ExternalService.logTransaction completed. Async non blocking");
            }
        }, 1, TimeUnit.SECONDS);
    }
}
