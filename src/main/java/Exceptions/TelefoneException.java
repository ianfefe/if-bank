package Exceptions;

public class TelefoneException extends RuntimeException {
    public TelefoneException() {
        super("Número de telefone inválido.");
    }
}
