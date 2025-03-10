//Nome: Ian Felix Fernandes Matrícula: 202376007

package Exceptions;

import javax.swing.*;

public class SaldoException extends RuntimeException {
    public SaldoException(String message) {
        super(message);
        JOptionPane.showMessageDialog(null,message);
    }
}
