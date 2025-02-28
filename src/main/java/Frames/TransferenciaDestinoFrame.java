package Frames;

import Usuarios.Cliente;
import Usuarios.Sistema;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferenciaDestinoFrame extends JFrame {
    private JTextField valorCampo;
    private JFormattedTextField destinoCampo;
    private JButton confirmarTransferênciaButton;
    private JPanel transferenciaDestinoPanel;

    public TransferenciaDestinoFrame(Cliente cliente){

        setSize(400,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(transferenciaDestinoPanel);
        aplicaMascaraCpf();

        confirmarTransferênciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cliente.transferir(cliente,encontraDestino(destinoCampo.getText()), Double.parseDouble(String.valueOf(valorCampo.getText())));
                dispose();
            }
        });
    }

    Cliente encontraDestino(String cpf){
        for (Cliente cliente : Sistema.getClientes()){
            if(cliente.getCpfString().equals(cpf)){
                return cliente;
            }
        }
        throw new RuntimeException("Conta destino nao encontrada");
    }

    private void aplicaMascaraCpf() {
        try {
            MaskFormatter formataCpf = new MaskFormatter("###.###.###-##");
            formataCpf.setPlaceholderCharacter('_');

            destinoCampo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(formataCpf));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}