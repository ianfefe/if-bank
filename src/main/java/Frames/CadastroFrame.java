//Nome: Ian Felix Fernandes Matrícula: 202376007

package Frames;

import Exceptions.CPFException;
import TiposAtributos.*;
import Usuarios.Sistema;
import Usuarios.Usuario;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import static Usuarios.Sistema.criarUsuario;

public class CadastroFrame extends JFrame {
    protected JTextField campoNome;
    protected JPasswordField campoSenha;
    protected JPasswordField campoSenhaConfirma;
    protected JLabel senha;
    protected JLabel senhaConfirma;
    protected JTextField campoEmail;
    protected JFormattedTextField campoTelefone;
    protected JLabel email;
    protected JLabel telefone;
    protected JTextField campoNumeroEndereco;
    protected JTextField campoRua;
    protected JPanel endereco;
    protected JPanel formasContato;
    protected JPanel confirmacaoSenha;
    protected JPanel cadastroPanel;
    protected JLabel complemento;
    protected JButton confirmarCadastroButton;
    protected JLabel nomeCompleto;
    protected JLabel dataDeNascimentoLabel;
    protected JFormattedTextField campoDataNascimento;
    protected JLabel CPFLabel;
    protected JFormattedTextField campoCPF;
    protected JComboBox<String> complementoBox;
    protected JButton botaoVoltar;
    protected JPanel tiposUsuarioPanel;
    protected JComboBox<String> comboBoxTipoUsuario;

    public CadastroFrame() {

        setSize(900, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(cadastroPanel);
        aplicarMascarasTexto();

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltar(e);
            }
        });

        confirmarCadastroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrar(e);
            }
        });
    }

    protected void voltar(ActionEvent e) {
        int sair = JOptionPane.showConfirmDialog(null, "Deseja cancelar a operação? Todo progresso será perdido.", "Cancelar operação.", 2);
        if (sair == 0) {
            dispose();
            SwingUtilities.invokeLater(() -> {
                new LoginFrame().setVisible(true);
            });
        }
    }

    protected void cadastrar(ActionEvent e) {
        String senha = new String(campoSenha.getPassword());
        String senhaConfirma = new String(campoSenhaConfirma.getPassword());

        if (senha.equals(senhaConfirma)) {
            if (validarCampos(e, "Cliente", "Cadastro")) {
                criarUsuario(
                        campoNome.getText(),
                        new DataDeNascimento(campoDataNascimento.getText()),
                        new CPF(campoCPF.getText(), "Cliente"),
                        new Endereco(campoRua.getText(), campoNumeroEndereco.getText(), (String) complementoBox.getSelectedItem()),
                        new Telefone(campoTelefone.getText()),
                        new Email(campoEmail.getText()),
                        new String(campoSenha.getPassword()),
                        "Cliente");
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                SwingUtilities.invokeLater(() -> {
                    new LoginFrame().setVisible(true);
                });
            } else {
                JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(campoSenhaConfirma, "Senha diferente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected boolean validarCampos(ActionEvent e, String tipoUsuario, String tipoModificacao) {

        boolean todosPreenchidos = true;

        try {

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
                new CPF(campoCPF.getText(), tipoUsuario);
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
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.\n " +
                    "Verifique se as informações são válidas.\n" +
                    "-A data de nascimento deve ser válida e maior de 18 anos.\n" +
                    "-O email deve ser válido.\n" +
                    "-Rua não pode conter números.");
            return false;
        }


        if (todosPreenchidos) {
            if (!tipoModificacao.equals("Edição")) {
                for (Usuario usuario : Sistema.getUsuarios()) {
                    if (usuario.getCpfString().equals(campoCPF.getText()) && usuario.getTipoUsuario().equals(tipoUsuario)) {
                        JOptionPane.showMessageDialog(null, "CPF já cadastrado.");
                        throw new CPFException("CPF já cadastrado.");
                    }
                }
            }
        }
        return todosPreenchidos;
    }

    protected void aplicaMascaraDataNascimento() {
        try {
            MaskFormatter formataNascimento = new MaskFormatter("##/##/####");
            formataNascimento.setPlaceholderCharacter('_');

            campoDataNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(formataNascimento));
        } catch (ParseException e) {
            System.err.println("Erro ao aplicar máscara de CPF. " + e.getMessage());
        }
    }

    protected void aplicaMascaraTelefone() {
        try {
            MaskFormatter formataTelefone = new MaskFormatter("(##)####-####");
            formataTelefone.setPlaceholderCharacter('_');

            campoTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(formataTelefone));
        } catch (ParseException e) {
            System.err.println("Erro ao aplicar máscara de Telefone. " + e.getMessage());
        }
    }

    protected void aplicarMascarasTexto() {
        Utility.aplicaMascaraCpf(campoCPF);
        aplicaMascaraDataNascimento();
        aplicaMascaraTelefone();
    }

}
