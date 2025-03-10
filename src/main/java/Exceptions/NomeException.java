package Exceptions;

public class NomeException extends RuntimeException {
    public NomeException(String message) {
        super(message);
    }

    public NomeException(){
        throw new RuntimeException("Nome inválido. Contém caracter especial.");
    }

}
