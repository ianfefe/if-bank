package Frames;

import Usuarios.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MenuCliente extends JFrame{
    private JLabel nomeUsuario;
    private JLabel saldoUsuario;
    private JButton botaoConfiguracoes;
    private JCheckBox SaldoCheckBox;
    private JScrollBar scrollBar1;
    private JPanel ClientePanel;
    private JTabbedPane menuPanel;
    private JTabbedPane abaInvestimento;
    private JPanel abaExtrato;
    private JTabbedPane abaRendaVariavel;
    private JPanel abaRendaFixa;
    private JButton botaoTransferir;
    private JButton botaoSair;

    MenuCliente(Cliente usuarioLogado){
        setSize(900,600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(ClientePanel);
        nomeUsuario.setText(usuarioLogado.getNome());
        atualizaSaldoView(usuarioLogado);
        Utility.adicionaOpcaoDeslogarUsuario(botaoSair,this);

        SaldoCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    SaldoCheckBox.setText("Mostrar saldo");
                    saldoUsuario.setText("***.**");
                }else{
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

        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizaSaldoView(usuarioLogado);
            }
        });
        timer.start();
    }

    private void atualizaSaldoView(Cliente usuarioLogado){
        saldoUsuario.setText("Saldo: R$" + usuarioLogado.getSaldoString());
    }

};