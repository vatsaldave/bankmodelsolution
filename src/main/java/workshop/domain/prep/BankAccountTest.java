package workshop.domain.prep;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class BankAccountTest {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(123, "0");
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Future<String> future = executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return bankAccount.deposit("100");
                }
            });
            futureList.add(future);

            Future<String> future2 = executor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return bankAccount.withdraw("100");
                }
            });
            futureList.add(future2);
        }

        waitForFinish(futureList);
        executor.shutdown();
        System.out.println(bankAccount.getCurrentBalance());
    }

    private static void waitForFinish(List<Future<String>> list) {
        List<Future<String>> newList = list.stream().filter(e -> !e.isDone()).collect(Collectors.toList());
        if (newList != null && newList.size() > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            waitForFinish(newList);
        }
    }

}
