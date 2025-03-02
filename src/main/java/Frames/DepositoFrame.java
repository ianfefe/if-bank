package Frames;

import Usuarios.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class DepositoFrame extends JFrame{
    private JFormattedTextField campoConta;
    private JTextField campoValor;
    private JButton botaoConfirmar;
    private JPanel depositoPanel;

    public DepositoFrame(Administrador usuarioLogado){
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(depositoPanel);
        Utility.aplicaMascaraCpf(campoConta);

        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double valor = Utility.confereValorDouble(campoValor);
                Cliente destino = Utility.encontraCliente(campoConta.getText());
                usuarioLogado.realizarDeposito(destino,valor);
                dispose();
            }
        });

        campoValor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Utility.formataValor(campoValor);
            }
        });
    }


}
