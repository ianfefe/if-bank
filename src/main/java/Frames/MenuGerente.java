package Frames;

import Usuarios.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGerente extends JFrame{
    private JTabbedPane abasPanel;
    private JButton botaoTransferir;
    private JTabbedPane investimentosAba;
    private JTabbedPane tabbedPane4;
    private JLabel nomeUsuario;
    private JButton botaoConfiguracoes;
    private JPanel gerentePanel;
    private JButton botaoSaque;
    private JButton botaoDeposito;
    private JTabbedPane tabbedPane3;
    private JTabbedPane tabbedPane5;
    private JTabbedPane tabbedPane6;
    private JTable table1;
    private JButton botaoSair;
    private JPanel consultasAba;
    private JPanel transferenciasAba;

    public MenuGerente(Usuario usuarioLogado) {
        setSize(900,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(gerentePanel);
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
