//Nome: Ian Felix Fernandes Matrícula: 202376007

package Frames;

import Usuario.Caixa;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Locale;

public class MenuCaixa extends JFrame {
    private JTabbedPane transferenciasAba;
    private JButton botaoTransferir;
    private JButton botaoSaque;
    private JButton botaoDeposito;
    private JLabel nomeUsuario;
    private JButton botaoPerfil;
    private JButton botaoSair;
    private JPanel caixaPanel;
    private JPanel transferenciasBotoesPanel;

    public MenuCaixa(Caixa usuarioLogado) {
        setSize(900, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(caixaPanel);
        nomeUsuario.setText("CAIXA - " + usuarioLogado.getNome());
        Utility.adicionaOpcaoDeslogarUsuario(botaoSair, this);

        botaoTransferir.addActionListener(e -> SwingUtilities.invokeLater(() -> new TransferenciaCompletaFrame().setVisible(true)));

        botaoPerfil.addActionListener(e -> SwingUtilities.invokeLater(() -> new EdicaoDadosFrame(usuarioLogado, usuarioLogado).setVisible(true)));

        botaoSaque.addActionListener(e -> SwingUtilities.invokeLater(() -> new SaqueFrame(usuarioLogado).setVisible(true)));

        botaoDeposito.addActionListener(e -> SwingUtilities.invokeLater(() -> new DepositoFrame(usuarioLogado).setVisible(true)));
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
        caixaPanel = new JPanel();
        caixaPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        transferenciasAba = new JTabbedPane();
        caixaPanel.add(transferenciasAba, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        transferenciasBotoesPanel = new JPanel();
        transferenciasBotoesPanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        transferenciasAba.addTab("Transferências", transferenciasBotoesPanel);
        botaoTransferir = new JButton();
        Font botaoTransferirFont = this.$$$getFont$$$(null, -1, 16, botaoTransferir.getFont());
        if (botaoTransferirFont != null) botaoTransferir.setFont(botaoTransferirFont);
        botaoTransferir.setText("Transferir");
        transferenciasBotoesPanel.add(botaoTransferir, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        botaoSaque = new JButton();
        Font botaoSaqueFont = this.$$$getFont$$$(null, -1, 16, botaoSaque.getFont());
        if (botaoSaqueFont != null) botaoSaque.setFont(botaoSaqueFont);
        botaoSaque.setText("Saque");
        transferenciasBotoesPanel.add(botaoSaque, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        botaoDeposito = new JButton();
        Font botaoDepositoFont = this.$$$getFont$$$(null, -1, 16, botaoDeposito.getFont());
        if (botaoDepositoFont != null) botaoDeposito.setFont(botaoDepositoFont);
        botaoDeposito.setText("Depósito");
        transferenciasBotoesPanel.add(botaoDeposito, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JToolBar toolBar1 = new JToolBar();
        caixaPanel.add(toolBar1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
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
        return caixaPanel;
    }

}
