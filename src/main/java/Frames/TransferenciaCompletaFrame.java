package Frames;

import Usuarios.Cliente;
import Usuarios.Sistema;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferenciaCompletaFrame extends JFrame {
    private JFormattedTextField campoOrigem;
    private JFormattedTextField campoDestino;
    private JTextField campoValor;
    private JButton botaoTransferir;
    private JPanel transferenciaCompletaPanel;

    public TransferenciaCompletaFrame() {

        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(transferenciaCompletaPanel);
        aplicaMascaraCpf();


        botaoTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cliente origem = encontraCliente(campoOrigem.getText());
                Cliente destino = encontraCliente(campoDestino.getText());
                origem.transferir(origem, destino, Double.parseDouble(String.valueOf(campoValor.getText())));
                dispose();
            }
        });

    }

    private Cliente encontraCliente(String cpf) {
        for (Cliente cliente : Sistema.getClientes()) {
            if (cliente.getCpfString().equals(cpf)) {
                return cliente;
            }
        }
        throw new RuntimeException("Conta destino nao encontrada");
    }

    private void aplicaMascaraCpf() {
        try {
            MaskFormatter formataCpf = new MaskFormatter("###.###.###-##");
            formataCpf.setPlaceholderCharacter('_');

            campoOrigem.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(formataCpf));
            campoDestino.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(formataCpf));
        } catch (Exception e) {
            System.err.println("Formato de CPF inválido");
        }
    }
}