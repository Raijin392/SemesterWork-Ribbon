package ru.kpfu.itis.ribbon.Exception;

public class EncryptorException extends Exception {
    public EncryptorException() {
        super();
    }

    public EncryptorException(String message) {
        super(message);
    }

    public EncryptorException(String message, Throwable cause) {
        super(message, cause);
    }

    public EncryptorException(Throwable cause) {
        super(cause);
    }

    protected EncryptorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
