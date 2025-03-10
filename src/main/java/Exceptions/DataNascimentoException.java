//Nome: Ian Felix Fernandes Matrícula: 202376007

package Exceptions;

import javax.swing.*;

public class DataNascimentoException extends RuntimeException {
    public DataNascimentoException() {
        super("Data de nascimento inválida.");
        JOptionPane.showMessageDialog(null,"Data de nascimento inválida.");
    }

    public DataNascimentoException(String message) {
        super(message);
        JOptionPane.showMessageDialog(null,message);
    }
}
