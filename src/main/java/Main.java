//Nome: Ian Felix Fernandes Matrícula: 202376007

import Frames.LoginFrame;
import Usuarios.Gerente;
import Usuarios.Sistema;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        Sistema.carregarUsuarios();
        Gerente.carregarRendaFixa();

        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
