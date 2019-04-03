package workshop.domain.externalserviceasyncblocking;

import workshop.enums.TransactionType;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class simulates external call that takes time to execute
 * The external call will be executed in a new thread and the thread will sleep for 1 sec
 */
public class ExternalService {

    private ExecutorService service = Executors.newFixedThreadPool(5);

    public void logTransaction(TransactionType transactionType, BigDecimal transactionAmount) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println("ExternalService.logTransaction completed. Async blocking");
            }
        });
    }
}
