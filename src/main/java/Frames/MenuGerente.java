//Nome: Ian Felix Fernandes Matrícula: 202376007

package Frames;

import Investimentos.RendaFixa;
import Usuarios.Gerente;
import Usuarios.Sistema;
import Usuarios.Usuario;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

public class MenuGerente extends JFrame {
    private JTabbedPane abasPanel;
    private JButton botaoTransferir;
    private JLabel nomeUsuario;
    private JButton botaoPerfil;
    private JPanel gerentePanel;
    private JButton botaoSaque;
    private JButton botaoDeposito;
    private JButton botaoSair;
    private JPanel transferenciasAba;
    private JButton botaoCriarFixa;
    private JList<String> listaUsuarios;
    private JButton botaoEditar;
    private JButton botaoCriar;
    private JButton recarregarButton;
    private JButton botaoRemover;
    private JList listaInvestimentosGerente;
    private JButton botaoCriarInvestimento;
    private JButton botaoRecarregarInvestimentos;
    private JButton botaoRemoverInvestimento;

    public MenuGerente(Gerente usuarioLogado) {
        setSize(900, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(gerentePanel);
        nomeUsuario.setText("GERENTE - " + usuarioLogado.getNome());
        Utility.adicionaOpcaoDeslogarUsuario(botaoSair, this);
        desenhaListaUsuarios(usuarioLogado);
        desenhaListaInvestimentos();

        botaoTransferir.addActionListener(e -> SwingUtilities.invokeLater(() -> new TransferenciaCompletaFrame().setVisible(true)));

        botaoSaque.addActionListener(e -> SwingUtilities.invokeLater(() -> new SaqueFrame(usuarioLogado).setVisible(true)));

        botaoDeposito.addActionListener(e -> SwingUtilities.invokeLater(() -> new DepositoFrame(usuarioLogado).setVisible(true)));

        botaoCriar.addActionListener(e -> SwingUtilities.invokeLater(() -> new CadastroAdmFrame().setVisible(true)));

        recarregarButton.addActionListener(e -> desenhaListaUsuarios(usuarioLogado));

        botaoRemover.addActionListener(e -> {

            DefaultListModel<Usuario> modelo = listaModeloUsuarios();
            int indice = listaUsuarios.getSelectedIndex();
            for (Usuario usuario : Sistema.getUsuarios()) {
                if (!usuario.equals(usuarioLogado)) {
                    if (usuario.getCpf().equals(modelo.getElementAt(indice).getCpf())) {
                        try {
                            if (usuarioLogado.confirmaSenha()) {
                                Sistema.removerUsuario(modelo.getElementAt(indice));
                            } else {
                                JOptionPane.showMessageDialog(null, "Falha ao remover usuário, senha incorreta ou o usuário cancelou a operação.");
                                return;
                            }
                        } catch (RuntimeException ex) {
                            JOptionPane.showMessageDialog(null, "Falha ao remover usuário.");
                            return;
                        }

                        JOptionPane.showMessageDialog(null, "Usuário removido com sucesso.\nClique em Recarregar para ver as alterações.");

                        break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não é possível remover o usuário conectado.");
                }
            }
        });

        botaoEditar.addActionListener(e -> {
            DefaultListModel<Usuario> modelo = listaModeloUsuarios();
            int indice = listaUsuarios.getSelectedIndex();
            SwingUtilities.invokeLater(() -> new EdicaoDadosFrame(modelo.getElementAt(indice), usuarioLogado).setVisible(true));
        });

        botaoPerfil.addActionListener(e -> SwingUtilities.invokeLater(() -> new EdicaoDadosFrame(usuarioLogado, usuarioLogado).setVisible(true)));

        botaoCriarInvestimento.addActionListener(e -> SwingUtilities.invokeLater(() -> new CriaInvestimento(usuarioLogado).setVisible(true)));

        botaoRecarregarInvestimentos.addActionListener(e -> desenhaListaInvestimentos());

        botaoRemoverInvestimento.addActionListener(e -> {
            int indice = listaInvestimentosGerente.getSelectedIndex();
            try {
                usuarioLogado.removerRendaFixa(indice);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possivel realizar a operação.");
            }
            JOptionPane.showMessageDialog(null, "Investimento removido com sucesso.");
        });
    }

    private DefaultListModel<Usuario> listaModeloUsuarios() {
        DefaultListModel<Usuario> modelo = new DefaultListModel<>();
        for (Usuario usuario : Sistema.getUsuarios()) {
            if (usuario != null) {
                modelo.addElement(usuario);
            } else {
                break;
            }
        }
        return modelo;
    }

    private void desenhaListaUsuarios(Gerente usuarioLogado) {

        DefaultListModel<Usuario> modelo = listaModeloUsuarios();

        DefaultListModel<String> listaUsuariosString = new DefaultListModel<>();
        for (int i = 0; i < modelo.getSize(); i++) {
            listaUsuariosString.addElement(modelo.getElementAt(i).getNome() + " - " + modelo.getElementAt(i).getCpfString() + " - " + modelo.getElementAt(i).getTipoUsuario());
        }

        if (!modelo.isEmpty()) {
            listaUsuarios.setModel(listaUsuariosString);
        }
    }

    private void desenhaListaInvestimentos() {

        DefaultListModel<String> listaInvestimentoString = new DefaultListModel<>();
        for (RendaFixa investimento : Gerente.getListaRendaFixa()) {
            if (investimento != null) {
                listaInvestimentoString.addElement("Tipo: " + investimento.getTipo() + " - Nome: " + investimento.getNome());
            }
        }
        if (!listaInvestimentoString.isEmpty()) {
            listaInvestimentosGerente.setModel(listaInvestimentoString);
        }
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
        gerentePanel = new JPanel();
        gerentePanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        gerentePanel.setPreferredSize(new Dimension(700, 600));
        abasPanel = new JTabbedPane();
        gerentePanel.add(abasPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        transferenciasAba = new JPanel();
        transferenciasAba.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        abasPanel.addTab("Transferências", transferenciasAba);
        botaoTransferir = new JButton();
        Font botaoTransferirFont = this.$$$getFont$$$(null, -1, 16, botaoTransferir.getFont());
        if (botaoTransferirFont != null) botaoTransferir.setFont(botaoTransferirFont);
        botaoTransferir.setText("Transferir");
        transferenciasAba.add(botaoTransferir, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        botaoSaque = new JButton();
        Font botaoSaqueFont = this.$$$getFont$$$(null, -1, 16, botaoSaque.getFont());
        if (botaoSaqueFont != null) botaoSaque.setFont(botaoSaqueFont);
        botaoSaque.setText("Saque");
        transferenciasAba.add(botaoSaque, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        botaoDeposito = new JButton();
        Font botaoDepositoFont = this.$$$getFont$$$(null, -1, 16, botaoDeposito.getFont());
        if (botaoDepositoFont != null) botaoDeposito.setFont(botaoDepositoFont);
        botaoDeposito.setText("Depósito");
        transferenciasAba.add(botaoDeposito, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        abasPanel.addTab("Investimentos", panel1);
        listaInvestimentosGerente = new JList();
        Font listaInvestimentosGerenteFont = this.$$$getFont$$$(null, -1, 16, listaInvestimentosGerente.getFont());
        if (listaInvestimentosGerenteFont != null) listaInvestimentosGerente.setFont(listaInvestimentosGerenteFont);
        panel1.add(listaInvestimentosGerente, new GridConstraints(0, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        botaoCriarInvestimento = new JButton();
        botaoCriarInvestimento.setText("Criar");
        panel1.add(botaoCriarInvestimento, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        botaoRecarregarInvestimentos = new JButton();
        botaoRecarregarInvestimentos.setText("Recarregar");
        panel1.add(botaoRecarregarInvestimentos, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        botaoRemoverInvestimento = new JButton();
        botaoRemoverInvestimento.setText("Remover");
        panel1.add(botaoRemoverInvestimento, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        abasPanel.addTab("Consultas", panel2);
        listaUsuarios = new JList();
        Font listaUsuariosFont = this.$$$getFont$$$(null, -1, 16, listaUsuarios.getFont());
        if (listaUsuariosFont != null) listaUsuarios.setFont(listaUsuariosFont);
        panel2.add(listaUsuarios, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        botaoEditar = new JButton();
        botaoEditar.setText("Ver / Editar");
        panel3.add(botaoEditar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        botaoCriar = new JButton();
        botaoCriar.setText("Criar");
        panel3.add(botaoCriar, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        recarregarButton = new JButton();
        recarregarButton.setText("Recarregar");
        panel3.add(recarregarButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        botaoRemover = new JButton();
        botaoRemover.setText("Remover");
        panel3.add(botaoRemover, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JToolBar toolBar1 = new JToolBar();
        gerentePanel.add(toolBar1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        toolBar1.setBorder(BorderFactory.createTitledBorder(null, "IF BANK", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$("Unispace", -1, 14, toolBar1.getFont()), null));
        nomeUsuario = new JLabel();
        nomeUsuario.setForeground(new Color(-12697532));
        nomeUsuario.setText("Nome do usuário");
        toolBar1.add(nomeUsuario);
        final Spacer spacer1 = new Spacer();
        toolBar1.add(spacer1);
        botaoPerfil = new JButton();
        botaoPerfil.setText("Editar perfil");
        toolBar1.add(botaoPerfil);
        botaoSair = new JButton();
        botaoSair.setText("Sair");
        toolBar1.add(botaoSair);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return gerentePanel;
    }

}

