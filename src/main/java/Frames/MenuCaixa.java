package Frames;

import Usuarios.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCaixa extends JFrame {
    private JTabbedPane transferenciasAba;
    private JButton botaoTransferir;
    private JButton botaoSaque;
    private JButton botaoDeposito;
    private JLabel nomeUsuario;
    private JButton botaoConfiguracoes;
    private JButton botaoSair;
    private JPanel caixaPanel;
    private JPanel transferenciasBotoesPanel;

    public MenuCaixa(Usuario usuarioLogado) {
        setSize(900,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(caixaPanel);
        nomeUsuario.setText(usuarioLogado.getNome());

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sair = JOptionPane.showConfirmDialog(null,"Deseja sair?","Desconectar",2);
                if(sair == 0){
                    dispose();
                    SwingUtilities.invokeLater(() -> {
                        new LoginFrame().setVisible(true);
                    });
                }
            }
        });
        botaoTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new TransferenciaCompletaFrame().setVisible(true);
                });
            }
        });
    }
}
