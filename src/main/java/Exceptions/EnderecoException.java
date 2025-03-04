package Exceptions;

public class EnderecoException extends RuntimeException {
    public EnderecoException(String message) {
        super(message);
    }

    public EnderecoException() {
        super("Endereço inválido.");
    }
}
