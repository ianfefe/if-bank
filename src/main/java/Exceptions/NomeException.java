package Exceptions;

import javax.swing.*;

public class NomeException extends RuntimeException {
    public NomeException(String message) {
        super(message);
    }

    public NomeException(){
        super("Nome inválido. Contém caracter especial.");
        JOptionPane.showMessageDialog(null,"Nome inválido. Contém caracter especial.");
    }

}
