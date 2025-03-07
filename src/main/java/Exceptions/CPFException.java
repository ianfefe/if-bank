//Nome: Ian Felix Fernandes Matrícula: 202376007

package Exceptions;

public class CPFException extends RuntimeException {
    public CPFException() {
        super("CPF inválido.");
    }

    public CPFException(String message) {
        super(message);
    }
}