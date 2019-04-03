# Banking Account Solution
This project is about modelling a simple bank account which maintains its state in memory. The bank account support deposit, withdrawal, balance-check and listing all transactions in thread safe manner.

There is an effort made to deal with concurrency using different solution approaches. Each approach is listed here

1.	**package workshop.domain.prep** : The code under this package takes traditional route where synchronized keyword is used to maintain sanity of state under multi threaded environment.
2.	**package workshop.domain.atomicref** : The code under this package makes use of Java inbuilt AtomicReference class to achieve thread safety of the bank account state.
3.	**package workshop.domain.externalservicesyncblocking** : The code under this package is modified to simulate an external interaction which takes time to execute. The interaction with external system is simulated as synchronous.
4.	**package workshop.domain.externalserviceasyncblocking** : The code under this package is modified to simulate an external interaction which takes time to execute. The interaction with external system is simulated as asynchronous where a separate thread is spawned in external system which takes time to do some work.
5.	**package workshop.domain.externalserviceasyncnonblocking** : The code under this package is modified to simulate an external interaction which takes time to execute. The interaction with external system is simulated as asynchronous where a separate thread is spawned in external system which scheduled to execute after one second delay.
6.	**package workshop.domain.externalserviceasyncnonblocking** : The code under this package is modified to simulate an external interaction which takes time to execute. The interaction with external system is simulated as asynchronous where a separate thread is spawned in external system which is scheduled to execute after one second delay.
7.	**package workshop.domain.externalservicecallback** : The code under this package is modified to simulate an external interaction which takes time to execute. The external service takes an instance of Runnable which is used as callback by external service.
8.	**package workshop.domain.externalservicecallbackwithqueue** : The code under this package is modified to simulate an external interaction which takes time to execute. The external service takes an instance of Runnable which is used as callback by external service. And in this approach bank account uses a queue to process the callback.

### Setup

1.	Install JDK 8
2.	Install maven
3.	Install git
4.	clone this repo
5.	cd into the cloned repo
6.	run "mvn clean package" to build the project
7.	Choose respective BankAccountTest class and run in your favourite IDE
