//Nome: Ian Felix Fernandes Matrícula: 202376007

package Frames;

import Usuario.Cliente;
import Usuario.Sistema;
import Usuario.Usuario;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
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
        for (Usuario cliente : Sistema.getUsuarios()) {
            if (cliente.getCpfString().equals(cpf) && cliente instanceof Cliente) {
                return (Cliente) cliente;
            }
        }
        JOptionPane.showMessageDialog(null, "Conta não encontrada.", "Conta inválida.", JOptionPane.ERROR_MESSAGE);
        throw new RuntimeException("Não foi possível encontrar cliente.");
    }

    public static void adicionaOpcaoDeslogarUsuario(JButton botao, Window tela) {
        botao.addActionListener(e -> {
            int sair = JOptionPane.showConfirmDialog(null, "Deseja sair?", "Desconectar", JOptionPane.YES_NO_OPTION);
            if (sair == 0) {
                Sistema.salvaUsuarios();
                tela.dispose();
                SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
            }
        });
    }

    public static void adicionaBotaoCancelarOperacao(JButton botao, Window tela) {
        botao.addActionListener(e -> {
            int sair = JOptionPane.showConfirmDialog(null, "Deseja cancelar a operação? Todo progresso será perdido.", "Cancelar operação.", JOptionPane.YES_NO_OPTION);
            if (sair == 0) {
                tela.dispose();
            }
        });
    }
}
