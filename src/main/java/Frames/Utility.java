package Frames;

import Usuarios.Cliente;
import Persistencias.Sistema;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Utility {
    public static void aplicaMascaraCpf(JFormattedTextField campo) {
        try {
            MaskFormatter formataCpf = new MaskFormatter("###.###.###-##");
            formataCpf.setPlaceholderCharacter('_');

            campo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(formataCpf));
        } catch (Exception e) {
            System.err.println("Formato de CPF inválido");
        }
    }

    public static void formataValor(JTextField campo) {
        try {
            String valorString = campo.getText().replaceAll("[^0-9]", "");
            if (!valorString.isEmpty()) {
                double value = Double.parseDouble(valorString) / 100.0;
                DecimalFormat df = new DecimalFormat("R$###,##0.00");
                campo.setText(df.format(value));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            campo.setText("");
        }
    }

    public static double confereValorDouble(JTextField campoValor) {
        String valorString = campoValor.getText().trim().replace("R$", "");
        valorString = valorString.replace(",", "");
        double valorDouble = Double.parseDouble(valorString);

        if (valorDouble > 0) {
            return valorDouble;
        }

        return 0;
    }

    public static Cliente encontraCliente(String cpf) {
        for (Cliente cliente : Sistema.getClientes()) {
            if (cliente.getCpfString().equals(cpf)) {
                return cliente;
            }
        }
        JOptionPane.showMessageDialog(null,"Conta não encontrada.","Conta inválida.",JOptionPane.ERROR_MESSAGE);
        throw new RuntimeException("Conta não encontrada.");
    }

    public static void adicionaOpcaoDeslogarUsuario(JButton botao, Window tela) {
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sair = JOptionPane.showConfirmDialog(null, "Deseja sair?", "Desconectar", 2);
                if (sair == 0) {
                    tela.dispose();
                    SwingUtilities.invokeLater(() -> {
                        new LoginFrame().setVisible(true);
                    });
                }
            }
        });
    }

    public static void adicionaBotaoCancelarOperacao(JButton botao, Window tela) {
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sair = JOptionPane.showConfirmDialog(null, "Deseja cancelar a operação? Todo progresso será perdido.", "Cancelar operação.", 2);
                if (sair == 0) {
                    tela.dispose();
                }
            }
        });
    }
}
