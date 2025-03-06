import Frames.LoginFrame;
import Usuarios.Gerente;
import Usuarios.Sistema;

import javax.swing.*;

public class main {
    public static void main(String[] args) {

        Sistema.carregarUsuarios();
        Gerente.carregarRendaFixa();

        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
