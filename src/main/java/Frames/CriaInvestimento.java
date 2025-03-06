package Frames;

import Usuarios.Gerente;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class CriaInvestimento extends JFrame {
    private JTextField campoNome;
    private JFormattedTextField campoResgateMax;
    private JFormattedTextField campoResgateMin;
    private JFormattedTextField campoRendimento;
    private JButton botaoConfirmar;
    private JButton botaoCancelar;
    private JPanel criacaoInvestimentoPanel;

    public CriaInvestimento(Gerente usuarioLogado) {
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(criacaoInvestimentoPanel);
        aplicaMascaraDatas();
        aplicaMascaraRendimento();
        Utility.adicionaBotaoCancelarOperacao(botaoCancelar, this);

        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validaCamposPreenchidos()) {
                    try {
                        double rendimentoString = Double.parseDouble(campoRendimento.getText().replaceAll("[%]", ""));
                        usuarioLogado.cadastraRendaFixa(campoNome.getText(), campoResgateMin.getText(), campoResgateMax.getText(), rendimentoString);
                        dispose();
                    } catch (RuntimeException ex) {
                        JOptionPane.showMessageDialog(null, "Não foi possível cadastrar o investimento solicitado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem estar preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void aplicaMascaraDatas() {
        try {
            MaskFormatter formataData = new MaskFormatter("##/##/####");
            formataData.setPlaceholderCharacter('_');

            campoResgateMin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(formataData));
            campoResgateMax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(formataData));
        } catch (ParseException e) {
            System.err.println("Erro ao aplicar máscara de datas. " + e.getMessage());
        }
    }

    private void aplicaMascaraRendimento() {
        try {
            MaskFormatter formataRendimento = new MaskFormatter("##.##%");
            formataRendimento.setPlaceholderCharacter('_');

            campoRendimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(formataRendimento));
        } catch (ParseException e) {
            System.err.println("Erro ao aplicar máscara de rendimento. " + e.getMessage());
        }
    }

    private boolean validaCamposPreenchidos() {
        return (!campoRendimento.getText().replaceAll("[.%]", "").isEmpty() &&
                !campoNome.getText().isEmpty() &&
                !campoResgateMin.getText().replaceAll("[/]", "").isEmpty() &&
                !campoResgateMax.getText().replaceAll("[/]", "").isEmpty());


    }
}
