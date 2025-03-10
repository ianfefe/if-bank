//Nome: Ian Felix Fernandes Matrícula: 202376007

package Exceptions;

import javax.swing.*;

public class EnderecoException extends RuntimeException {
    public EnderecoException(String message) {
        super(message);
        JOptionPane.showMessageDialog(null,message);
    }

    public EnderecoException() {
        super("Endereço inválido.");
        JOptionPane.showMessageDialog(null,"Endereço inválido.");
    }
}
