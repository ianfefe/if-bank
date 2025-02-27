package Frames;

import Usuarios.Cliente;
import Usuarios.Sistema;
import Usuarios.Usuario;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferenciaDestinoFrame extends JFrame {
    private JTextField valorCampo;
    private JFormattedTextField destinoCampo;
    private JButton confirmarTransferênciaButton;
    private JPanel transferenciaDestinoPanel;

    public TransferenciaDestinoFrame(Cliente usuario){

        setSize(400,250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(transferenciaDestinoPanel);
        aplicaMascaraCpf();

        confirmarTransferênciaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuario.transferir(usuario,encontraDestino(destinoCampo.getText()), Double.parseDouble(String.valueOf(valorCampo.getText())));
            }
        });
    }

    Cliente encontraDestino(String cpf){
        for (Usuario usuario : Sistema.getUsuarios()){
            if(usuario.getCpfString().equals(cpf) && usuario.getTipo().equals("Cliente")){
                return (Cliente) usuario;
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

