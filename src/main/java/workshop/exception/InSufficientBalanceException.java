package workshop.exception;

/**
 * This exception is thrown in case there is insufficient balance in the account to perform the transaction.
 */
public class InSufficientBalanceException extends RuntimeException {
    /**
     * @param message The error message
     */
    public InSufficientBalanceException(String message) {
        super(message);
    }
}
