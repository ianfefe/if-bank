package Frames;

import TiposAtributos.*;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import static Persistencias.Sistema.criarUsuario;

public class CadastroFrame extends JFrame {
    private JTextField campoNome;
    private JPasswordField campoSenha;
    private JPasswordField campoSenhaConfirma;
    private JLabel senha;
    private JLabel senhaConfirma;
    private JTextField campoEmail;
    private JFormattedTextField campoTelefone;
    private JLabel email;
    private JLabel telefone;
    private JTextField campoNumeroEndereco;
    private JTextField campoRua;
    private JPanel endereco;
    private JPanel formasContato;
    private JPanel confirmacaoSenha;
    private JPanel cadastroPanel;
    private JLabel complemento;
    private JButton confirmarCadastroButton;
    private JLabel nomeCompleto;
    private JLabel dataDeNascimentoLabel;
    private JFormattedTextField campoDataNascimento;
    private JLabel CPFLabel;
    private JFormattedTextField campoCPF;
    private JComboBox<String> complementoBox;
    private JButton botaoVoltar;

    public CadastroFrame() {

        setSize(900, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(cadastroPanel);
        aplicarMascarasTexto();

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> {
                    new LoginFrame().setVisible(true);
                });
            }
        });

        confirmarCadastroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String senha = new String(campoSenha.getPassword());
                String senhaConfirma = new String(campoSenhaConfirma.getPassword());

                if (senha.equals(senhaConfirma)) {
                    validarCampos(e);
                    criarUsuario(
                            campoNome.getText(),
                            new DataDeNascimento(campoDataNascimento.getText()),
                            new CPF(campoCPF.getText()),
                            new Endereco(campoRua.getText(), campoNumeroEndereco.getText(), (String) complementoBox.getSelectedItem()),
                            new Telefone(campoTelefone.getText()),
                            new Email(campoEmail.getText()),
                            new String(campoSenha.getPassword()),
                            "Cliente");

                    dispose();
                    SwingUtilities.invokeLater(() -> {
                        new LoginFrame().setVisible(true);
                    });

                } else {
                    JOptionPane.showMessageDialog(campoSenhaConfirma, "Senha diferente.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void validarCampos(ActionEvent e) {

        boolean todosPreenchidos = true;

        if (campoNome.getText().isBlank())
            todosPreenchidos = false;

        if (campoDataNascimento.getText().replaceAll("_", "").isBlank()) {
            todosPreenchidos = false;
        } else {
            new DataDeNascimento(campoDataNascimento.getText());
        }

        if (campoCPF.getText().replaceAll("_", "").isBlank()) {
            todosPreenchidos = false;
        } else {
            new CPF(campoCPF.getText());
        }

        if (campoEmail.getText().isBlank()) {
            todosPreenchidos = false;
        } else {
            new Email(campoEmail.getText());
        }

        if (campoTelefone.getText().replaceAll("[_()-]", "").trim().isEmpty()) {
            todosPreenchidos = false;
        } else {
            new Telefone(campoTelefone.getText());
        }

        if (campoNumeroEndereco.getText().isBlank()) {
            todosPreenchidos = false;
        } else {
            if (campoRua.getText().isBlank()) {
                todosPreenchidos = false;
            } else {
                new Endereco(campoRua.getText(), campoNumeroEndereco.getText(), (String) complementoBox.getSelectedItem());
            }
        }

        if (new String(campoSenha.getPassword()).isEmpty())
            todosPreenchidos = false;

        if (new String(campoSenhaConfirma.getPassword()).isEmpty())
            todosPreenchidos = false;

        if (todosPreenchidos) {
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void aplicaMascaraDataNascimento() {
        try {
            MaskFormatter formataNascimento = new MaskFormatter("##/##/####");
            formataNascimento.setPlaceholderCharacter('_');

            campoDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(formataNascimento));
        } catch (ParseException e) {
            System.err.println("Erro ao aplicar máscara de CPF. " + e.getMessage());
        }
    }

    private void aplicaMascaraTelefone() {
        try {
            MaskFormatter formataTelefone = new MaskFormatter("(##)####-####");
            formataTelefone.setPlaceholderCharacter('_');

            campoTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(formataTelefone));
        } catch (ParseException e) {
            System.err.println("Erro ao aplicar máscara de Telefone. " + e.getMessage());
        }
    }

    private void aplicarMascarasTexto() {
        Utility.aplicaMascaraCpf(campoCPF);
        aplicaMascaraDataNascimento();
        aplicaMascaraTelefone();
    }

}
