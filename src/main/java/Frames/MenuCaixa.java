package Frames;

import Usuarios.Caixa;
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

    public MenuCaixa(Caixa usuarioLogado) {
        setSize(900,600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(caixaPanel);
        nomeUsuario.setText(usuarioLogado.getNome());
        Utility.adicionaOpcaoDeslogarUsuario(botaoSair,this);

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
