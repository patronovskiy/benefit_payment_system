package edu.patronovskiy.studentorder.exception;

/**
 * @author patronovskiy
 * @link https://github.com/patronovskiy
 */

public class CityRegisterException extends Exception {
    public CityRegisterException() {
    }

    public CityRegisterException(final String message) {
        super(message);
    }

    public CityRegisterException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
