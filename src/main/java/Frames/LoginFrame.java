package Frames;

import Usuarios.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{
    private JFormattedTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoLogin;
    private JButton botaoCadastro;
    private JPanel loginPanel;
    private JLabel usuario;
    private JLabel senha;

    public LoginFrame() {

        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(loginPanel);

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                validarCampos(e);

                if(getTipoUsuario().equals("Cliente")){
                    Cliente usuarioLogado = Sistema.logarCliente(formataUsuarioCliente(),new String(campoSenha.getPassword()));
                    if(usuarioLogado == null){
                        JOptionPane.showMessageDialog(null,"Usuário não encontrado", "Login mal sucedido",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    dispose();
                    new MenuCliente(usuarioLogado).setVisible(true);
                }else{
                    Object usuarioLogado = Sistema.logarAdm(campoUsuario.getText(),new String(campoSenha.getPassword()));
                    if(usuarioLogado == null){
                        JOptionPane.showMessageDialog(null,"Usuário não encontrado", "Login mal sucedido",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(usuarioLogado instanceof Caixa)
                        new MenuCaixa((Caixa) usuarioLogado).setVisible(true);
                    else
                        new MenuGerente((Gerente) usuarioLogado).setVisible(true);
                    dispose();
                }
            }
        });

        botaoCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CadastroFrame().setVisible(true);
            }
        });
    }

    private String formataUsuarioCliente(){
        String parte1 = campoUsuario.getText().substring(0, 3);
        String parte2 = campoUsuario.getText().substring(3, 6);
        String parte3 = campoUsuario.getText().substring(6, 9);
        String parte4 = campoUsuario.getText().substring(9, 11);

        return parte1 + "." + parte2 + "." + parte3 + "-" + parte4;
    }

    private String getTipoUsuario(){
        if(campoUsuario.getText().matches("^[0-9]{11}$")){
            return "Cliente";
        }else{
            return "Administrador";
        }
    }

    private void validarCampos(ActionEvent e) {

        boolean todosPreenchidos = true;

        if (campoUsuario.getText().isBlank())
            todosPreenchidos = false;

        if (new String(campoSenha.getPassword()).isBlank())
            todosPreenchidos = false;

        if(!todosPreenchidos)
            JOptionPane.showMessageDialog(null,"Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
