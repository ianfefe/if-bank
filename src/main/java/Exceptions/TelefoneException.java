//Nome: Ian Felix Fernandes Matrícula: 202376007

package Exceptions;

import javax.swing.*;

public class TelefoneException extends RuntimeException {
    public TelefoneException() {
        super("Número de telefone inválido.");
        JOptionPane.showMessageDialog(null,"Número de telefone inválido.");
    }
}
