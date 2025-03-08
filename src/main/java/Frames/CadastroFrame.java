//Nome: Ian Felix Fernandes Matrícula: 202376007

package Frames;

import Exceptions.CPFException;
import TiposAtributos.*;
import Usuarios.Sistema;
import Usuarios.Usuario;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
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

        botaoVoltar.addActionListener(this::voltar);

        confirmarCadastroButton.addActionListener(this::cadastrar);
    }

    protected void voltar(ActionEvent e) {
        int sair = JOptionPane.showConfirmDialog(null, "Deseja cancelar a operação? Todo progresso será perdido.", "Cancelar operação.", JOptionPane.YES_NO_OPTION);
        if (sair == 0) {
            dispose();
            SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
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
                        new CPF(campoCPF.getText()),
                        new Endereco(campoRua.getText(), campoNumeroEndereco.getText(), (String) complementoBox.getSelectedItem()),
                        new Telefone(campoTelefone.getText()),
                        new Email(campoEmail.getText()),
                        new String(campoSenha.getPassword()),
                        "Cliente");
                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
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

            campoDataNascimento.setFormatterFactory(new DefaultFormatterFactory(formataNascimento));
        } catch (ParseException e) {
            System.err.println("Erro ao aplicar máscara de CPF. " + e.getMessage());
        }
    }

    protected void aplicaMascaraTelefone() {
        try {
            MaskFormatter formataTelefone = new MaskFormatter("(##)####-####");
            formataTelefone.setPlaceholderCharacter('_');

            campoTelefone.setFormatterFactory(new DefaultFormatterFactory(formataTelefone));
        } catch (ParseException e) {
            System.err.println("Erro ao aplicar máscara de Telefone. " + e.getMessage());
        }
    }

    protected void aplicarMascarasTexto() {
        Utility.aplicaMascaraCpf(campoCPF);
        aplicaMascaraDataNascimento();
        aplicaMascaraTelefone();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        cadastroPanel = new JPanel();
        cadastroPanel.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        confirmacaoSenha = new JPanel();
        confirmacaoSenha.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        cadastroPanel.add(confirmacaoSenha, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        confirmacaoSenha.setBorder(BorderFactory.createTitledBorder(null, "Confirmação de senha", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        senha = new JLabel();
        senha.setText("Senha");
        confirmacaoSenha.add(senha, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        senhaConfirma = new JLabel();
        senhaConfirma.setText("Confirmar senha");
        confirmacaoSenha.add(senhaConfirma, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        campoSenhaConfirma = new JPasswordField();
        confirmacaoSenha.add(campoSenhaConfirma, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        campoSenha = new JPasswordField();
        campoSenha.setText("");
        confirmacaoSenha.add(campoSenha, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        confirmarCadastroButton = new JButton();
        confirmarCadastroButton.setText("Confirmar cadastro");
        cadastroPanel.add(confirmarCadastroButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        cadastroPanel.add(panel1, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        endereco = new JPanel();
        endereco.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(endereco, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        endereco.setBorder(BorderFactory.createTitledBorder(null, "Formas de Contato", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        telefone = new JLabel();
        telefone.setText("Telefone");
        endereco.add(telefone, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        campoEmail = new JTextField();
        endereco.add(campoEmail, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        campoTelefone = new JFormattedTextField();
        endereco.add(campoTelefone, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        email = new JLabel();
        email.setText("Email");
        endereco.add(email, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        formasContato = new JPanel();
        formasContato.setLayout(new GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(formasContato, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        formasContato.setBorder(BorderFactory.createTitledBorder(null, "Endereço", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Número");
        formasContato.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Rua");
        formasContato.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        campoNumeroEndereco = new JTextField();
        formasContato.add(campoNumeroEndereco, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        campoRua = new JTextField();
        formasContato.add(campoRua, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        complemento = new JLabel();
        complemento.setText("Complemento");
        formasContato.add(complemento, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        complementoBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Casa");
        defaultComboBoxModel1.addElement("Apartamento");
        defaultComboBoxModel1.addElement("Estabelecimento");
        complementoBox.setModel(defaultComboBoxModel1);
        formasContato.add(complementoBox, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tiposUsuarioPanel = new JPanel();
        tiposUsuarioPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tiposUsuarioPanel.setVisible(false);
        formasContato.add(tiposUsuarioPanel, new GridConstraints(1, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tiposUsuarioPanel.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        comboBoxTipoUsuario = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Cliente");
        defaultComboBoxModel2.addElement("Caixa");
        defaultComboBoxModel2.addElement("Gerente");
        comboBoxTipoUsuario.setModel(defaultComboBoxModel2);
        tiposUsuarioPanel.add(comboBoxTipoUsuario, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Tipo de Usuário");
        tiposUsuarioPanel.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        cadastroPanel.add(panel2, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        panel2.setBorder(BorderFactory.createTitledBorder(null, "Dados do usuário", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        nomeCompleto = new JLabel();
        nomeCompleto.setText("Nome Completo");
        panel2.add(nomeCompleto, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dataDeNascimentoLabel = new JLabel();
        dataDeNascimentoLabel.setText("Data de nascimento");
        panel2.add(dataDeNascimentoLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CPFLabel = new JLabel();
        CPFLabel.setText("CPF");
        panel2.add(CPFLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        campoNome = new JTextField();
        campoNome.setText("");
        panel2.add(campoNome, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        campoDataNascimento = new JFormattedTextField();
        campoDataNascimento.setText("");
        panel2.add(campoDataNascimento, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        campoCPF = new JFormattedTextField();
        campoCPF.setText("");
        panel2.add(campoCPF, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        botaoVoltar = new JButton();
        botaoVoltar.setText("Voltar");
        cadastroPanel.add(botaoVoltar, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return cadastroPanel;
    }

}
