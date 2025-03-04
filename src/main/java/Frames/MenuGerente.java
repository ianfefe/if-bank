package Frames;

import Usuarios.Gerente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGerente extends JFrame {
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
    private JButton botaoSair;
    private JPanel transferenciasAba;
    private JTable table1;

    public MenuGerente(Gerente usuarioLogado) {
        setSize(900, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(gerentePanel);
        nomeUsuario.setText(usuarioLogado.getNome());
        Utility.adicionaOpcaoDeslogarUsuario(botaoSair, this);

        botaoTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new TransferenciaCompletaFrame().setVisible(true);
                });
            }
        });

        botaoSaque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new SaqueFrame(usuarioLogado).setVisible(true);
                });
            }
        });

        botaoDeposito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new DepositoFrame(usuarioLogado).setVisible(true);
                });
            }
        });
    }
}
