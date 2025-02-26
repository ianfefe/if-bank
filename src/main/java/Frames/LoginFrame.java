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
    private JLabel usuario;
    private JLabel senha;
    private JPanel loginPanel;

    public LoginFrame() {

        setSize(400,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(loginPanel);
        aplicaMascaraUsuario();

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuarioLogado = Sistema.logarUsuario(campoUsuario.getText(),new String(campoSenha.getPassword()));

                if (usuarioLogado != null){
                    switch (usuarioLogado.getTipo()){
                        case "Cliente":
                            dispose();
                            new MenuCliente((Cliente) usuarioLogado).setVisible(true);
                            break;
                        case "Caixas":
                            dispose();
                            new MenuCaixa((Caixa) usuarioLogado).setVisible(true);
                            break;
                        case "Gerente":
                            dispose();
                            new MenuGerente((Gerente) usuarioLogado).setVisible(true);
                            break;
                        default:
                            throw new RuntimeException("Tipo de usuário desconhecido");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Usuário e/ou senha incorretos.");
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

        private void aplicaMascaraUsuario() {
            try {
                MaskFormatter formataUsuario = new MaskFormatter("###.###.###-##");
                formataUsuario.setPlaceholderCharacter('_');

                campoUsuario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(formataUsuario));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
