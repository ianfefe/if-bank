package Frames;

import Usuarios.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MenuCliente extends JFrame {
    private JLabel nomeUsuario;
    private JLabel saldoUsuario;
    private JButton botaoConfiguracoes;
    private JCheckBox SaldoCheckBox;
    private JPanel ClientePanel;
    private JTabbedPane menuPanel;
    private JTabbedPane abaInvestimento;
    private JPanel abaExtrato;
    private JTabbedPane abaRendaVariavel;
    private JPanel abaRendaFixa;
    private JButton botaoTransferir;
    private JButton botaoSair;
    private JList<String> listaEntrada;
    private JList<String> listaSaida;
    private JScrollPane panelEntrada;
    private JScrollPane panelSaida;

    MenuCliente(Cliente usuarioLogado) {
        setSize(900, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(ClientePanel);
        nomeUsuario.setText(usuarioLogado.getNome());
        Utility.adicionaOpcaoDeslogarUsuario(botaoSair, this);

        SaldoCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    SaldoCheckBox.setText("Mostrar saldo");
                    saldoUsuario.setText("***.**");
                } else {
                    SaldoCheckBox.setText("Esconder saldo");
                    atualizaSaldoView(usuarioLogado);
                }
            }
        });

        botaoTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new TransferenciaDestinoFrame(usuarioLogado).setVisible(true);
                });
            }
        });

        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizaSaldoView(usuarioLogado);
                desenhaListaEntrada(usuarioLogado);
                desenhaListaSaida(usuarioLogado);
            }
        });
        timer.start();

    }

    private void atualizaSaldoView(Cliente usuarioLogado) {
        saldoUsuario.setText("Saldo: R$" + String.format("%.2f", usuarioLogado.getSaldo()));
    }

    private void desenhaListaEntrada(Cliente usuarioLogado){
        DefaultListModel<String> extratoEntrada = new DefaultListModel<>();
        for (Double entrada : usuarioLogado.getEntrada()) {
            if (entrada != null) {
                extratoEntrada.addElement("R$ " + String.format("%.2f", entrada));
            } else {
                break;
            }
        }
        if (!extratoEntrada.isEmpty()) {
            listaEntrada.setModel(extratoEntrada);
        }
    }

    private void desenhaListaSaida(Cliente usuarioLogado){
        DefaultListModel<String> extratoSaida = new DefaultListModel<>();
        for (Double entrada : usuarioLogado.getSaida()) {
            if (entrada != null) {
                extratoSaida.addElement("R$ " + String.format("%.2f", entrada));
            } else {
                break;
            }
        }
        if (!extratoSaida.isEmpty()) {
            listaSaida.setModel(extratoSaida);
        }
    }

};