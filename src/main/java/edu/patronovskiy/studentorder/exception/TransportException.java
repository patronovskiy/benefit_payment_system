package edu.patronovskiy.studentorder.exception;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class TransportException extends Exception {
    public TransportException() {
    }

    public TransportException(String message) {
        super(message);
    }

    public TransportException(String message, Throwable cause) {
        super(message, cause);
    }
}
