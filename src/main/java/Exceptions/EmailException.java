//Nome: Ian Felix Fernandes Matrícula: 202376007

package Exceptions;

import javax.swing.*;

public class EmailException extends RuntimeException {

    public EmailException() {
        super("Email inválido.");
        JOptionPane.showMessageDialog(null,"Email inválido.");
    }
}
