package Exceptions;

public class EmailException extends RuntimeException {

    public EmailException() {
        super("Email inválido!");
    }
}
