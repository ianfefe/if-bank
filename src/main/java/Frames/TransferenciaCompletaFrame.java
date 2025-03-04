package Frames;

import Usuarios.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TransferenciaCompletaFrame extends JFrame {
    private JFormattedTextField campoOrigem;
    private JFormattedTextField campoDestino;
    private JTextField campoValor;
    private JButton botaoTransferir;
    private JPanel transferenciaCompletaPanel;
    private JButton botaoCancelar;

    public TransferenciaCompletaFrame() {

        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(transferenciaCompletaPanel);
        Utility.aplicaMascaraCpf(campoOrigem);
        Utility.aplicaMascaraCpf(campoDestino);
        Utility.adicionaBotaoCancelarOperacao(botaoCancelar,this);


        botaoTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente origem = Utility.encontraCliente(campoOrigem.getText());
                Cliente destino = Utility.encontraCliente(campoDestino.getText());
                double valor = Utility.confereValorDouble(campoValor);
                origem.transferir(origem, destino, valor);
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