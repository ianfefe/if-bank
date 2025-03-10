//Nome: Ian Felix Fernandes Matrícula: 202376007

package Exceptions;

import javax.swing.*;

public class CPFException extends RuntimeException {
    public CPFException() {
        super("CPF inválido.");
        JOptionPane.showMessageDialog(null,"CPF inválido.");
    }

    public CPFException(String message) {
        super(message);
        JOptionPane.showMessageDialog(null,message);
    }
}