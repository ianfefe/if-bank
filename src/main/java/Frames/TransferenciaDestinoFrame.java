//Nome: Ian Felix Fernandes Matrícula: 202376007

package Frames;

import Usuarios.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TransferenciaDestinoFrame extends JFrame {
    private JTextField campoValor;
    private JFormattedTextField campoDestino;
    private JButton botaoTransferencia;
    private JPanel transferenciaDestinoPanel;
    private JButton botaoCancelar;

    public TransferenciaDestinoFrame(Cliente clienteOrigem) {

        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(transferenciaDestinoPanel);
        Utility.aplicaMascaraCpf(campoDestino);
        Utility.adicionaBotaoCancelarOperacao(botaoCancelar, this);

        botaoTransferencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente destino = Utility.encontraCliente(campoDestino.getText());
                double valor = Utility.confereValorDouble(campoValor);
                clienteOrigem.transferir(clienteOrigem, destino, valor);
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