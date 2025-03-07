//Nome: Ian Felix Fernandes Matrícula: 202376007

package Exceptions;

public class DataNascimentoException extends RuntimeException {
    public DataNascimentoException() {
        super("Data de nascimento inválida.");
    }

    public DataNascimentoException(String message) {
        super(message);
    }
}
