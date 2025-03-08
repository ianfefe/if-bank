//Nome: Ian Felix Fernandes Matrícula: 202376007

import Frames.LoginFrame;
import TiposAtributos.*;
import Usuario.Gerente;
import Usuario.Sistema;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Sistema.carregarUsuarios();

        if(Sistema.getUsuarios().isEmpty()){
            Sistema.criarUsuario("Adm", new DataDeNascimento("01/01/2000"), new CPF("111.111.111-11"), new Endereco("abc", "123", "Casa"), new Telefone("(32)3222-2223"), new Email("aaaaa@gmail.com"), "123", "Gerente");
        }

        Gerente.carregarRendaFixa();

        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
