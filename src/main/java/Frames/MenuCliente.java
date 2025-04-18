//Nome: Ian Felix Fernandes Matrícula: 202376007

package Frames;

import Investimentos.Investimento;
import Investimentos.RendaFixa;
import Usuario.Cliente;
import Usuario.Gerente;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Locale;

public class MenuCliente extends JFrame {
    private JLabel nomeUsuario;
    private JLabel saldoUsuario;
    private JButton botaoPerfil;
    private JCheckBox SaldoCheckBox;
    private JPanel ClientePanel;
    private JTabbedPane menuPanel;
    private JPanel abaExtrato;
    private JButton botaoTransferir;
    private JButton botaoSair;
    private JList<String> listaEntrada;
    private JList<String> listaSaida;
    private JScrollPane panelEntrada;
    private JScrollPane panelSaida;
    private JList listaInvestimentos;
    private JList listaAplicacoesCliente;
    private JButton botaoInvestir;
    private JButton botaoResgatar;
    private JPanel abaInvestimentos;
    private JButton recarregarButton;

    MenuCliente(Cliente usuarioLogado) {
        setSize(900, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(ClientePanel);
        nomeUsuario.setText(usuarioLogado.getNome());
        Utility.adicionaOpcaoDeslogarUsuario(botaoSair, this);
        atualizaSaldoView(usuarioLogado);
        desenhaListaInvestimentos();
        desenhaAplicacoes(usuarioLogado);

        SaldoCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                SaldoCheckBox.setText("Mostrar saldo");
                saldoUsuario.setText("***.**");
            } else {
                SaldoCheckBox.setText("Esconder saldo");
                atualizaSaldoView(usuarioLogado);
            }
        });

        botaoTransferir.addActionListener(e -> SwingUtilities.invokeLater(() -> new TransferenciaDestinoFrame(usuarioLogado).setVisible(true)));

        botaoPerfil.addActionListener(e -> SwingUtilities.invokeLater(() -> new EdicaoDadosFrame(usuarioLogado, usuarioLogado).setVisible(true)));

        Timer timer = new Timer(2000, e -> {

            if (!saldoUsuario.getText().equals("***.**")) {
                atualizaSaldoView(usuarioLogado);
            }
            desenhaListaEntrada(usuarioLogado);
            desenhaListaSaida(usuarioLogado);
            desenhaAplicacoes(usuarioLogado);
        });
        timer.start();

        botaoInvestir.addActionListener(e -> {
            int indice = listaInvestimentos.getSelectedIndex();
            SwingUtilities.invokeLater(() -> {
                new InvestimentoRendaFixa(Gerente.getListaRendaFixa().get(indice), usuarioLogado).setVisible(true);
            });

        });

        botaoResgatar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indice = listaAplicacoesCliente.getSelectedIndex();
                usuarioLogado.resgataInvestimento(usuarioLogado.getInvestimentos().get(indice));
            }
        });
    }

    private void atualizaSaldoView(Cliente usuarioLogado) {
        saldoUsuario.setText("Saldo: R$" + String.format("%.2f", usuarioLogado.getSaldo()));
    }

    private void desenhaListaEntrada(Cliente usuarioLogado) {
        DefaultListModel<String> extratoEntrada = new DefaultListModel<>();
        for (String entrada : usuarioLogado.getEntrada()) {
            if (entrada != null) {
                extratoEntrada.addElement(entrada);
            } else {
                break;
            }
        }
        if (!extratoEntrada.isEmpty()) {
            listaEntrada.setModel(extratoEntrada);
        }
    }

    private void desenhaListaSaida(Cliente usuarioLogado) {
        DefaultListModel<String> extratoSaida = new DefaultListModel<>();
        for (String saida : usuarioLogado.getSaida()) {
            if (saida != null) {
                extratoSaida.addElement(saida);
            } else {
                break;
            }
        }
        if (!extratoSaida.isEmpty()) {
            listaSaida.setModel(extratoSaida);
        }
    }

    private void desenhaListaInvestimentos() {

        DefaultListModel<String> listaInvestimentoString = new DefaultListModel<>();
        for (RendaFixa investimento : Gerente.getListaRendaFixa()) {
            if (investimento != null) {
                listaInvestimentoString.addElement("Nome: " + investimento.getNome());
            }
        }
        if (!listaInvestimentoString.isEmpty()) {
            listaInvestimentos.setModel(listaInvestimentoString);
        }
    }

    private void desenhaAplicacoes(Cliente usuarioLogado) {
        DefaultListModel<String> listaAplicacoes = new DefaultListModel<>();
        for (Investimento investimento : usuarioLogado.getInvestimentos()) {
            if (investimento != null) {
                investimento.aplicacaoExpirada();
                listaAplicacoes.addElement("Nome: " + investimento.getNome() + " Valor aplicado: " + String.format("%.2f", investimento.getValorAplicado()));
            }
        }
        if (!listaAplicacoes.isEmpty()) {
            listaAplicacoesCliente.setModel(listaAplicacoes);
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
        ClientePanel = new JPanel();
        ClientePanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        ClientePanel.setPreferredSize(new Dimension(700, 600));
        menuPanel = new JTabbedPane();
        ClientePanel.add(menuPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        menuPanel.addTab("Transferências", panel1);
        botaoTransferir = new JButton();
        Font botaoTransferirFont = this.$$$getFont$$$(null, -1, 18, botaoTransferir.getFont());
        if (botaoTransferirFont != null) botaoTransferir.setFont(botaoTransferirFont);
        botaoTransferir.setText("Transferir");
        panel1.add(botaoTransferir, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        abaExtrato = new JPanel();
        abaExtrato.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        menuPanel.addTab("Extrato", abaExtrato);
        panelEntrada = new JScrollPane();
        abaExtrato.add(panelEntrada, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panelEntrada.setBorder(BorderFactory.createTitledBorder(null, "Entrada", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, this.$$$getFont$$$(null, Font.BOLD, -1, panelEntrada.getFont()), new Color(-16777216)));
        listaEntrada = new JList();
        Font listaEntradaFont = this.$$$getFont$$$(null, -1, 14, listaEntrada.getFont());
        if (listaEntradaFont != null) listaEntrada.setFont(listaEntradaFont);
        listaEntrada.setLayoutOrientation(0);
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        listaEntrada.setModel(defaultListModel1);
        listaEntrada.putClientProperty("List.isFileList", Boolean.FALSE);
        panelEntrada.setViewportView(listaEntrada);
        panelSaida = new JScrollPane();
        abaExtrato.add(panelSaida, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        panelSaida.setBorder(BorderFactory.createTitledBorder(null, "Saída", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$(null, Font.BOLD, -1, panelSaida.getFont()), new Color(-16777216)));
        listaSaida = new JList();
        Font listaSaidaFont = this.$$$getFont$$$(null, -1, 14, listaSaida.getFont());
        if (listaSaidaFont != null) listaSaida.setFont(listaSaidaFont);
        listaSaida.setLayoutOrientation(0);
        panelSaida.setViewportView(listaSaida);
        abaInvestimentos = new JPanel();
        abaInvestimentos.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        menuPanel.addTab("Investimentos", abaInvestimentos);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        abaInvestimentos.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel2.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setBorder(BorderFactory.createTitledBorder(null, "Meus investimentos", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        listaAplicacoesCliente = new JList();
        Font listaAplicacoesClienteFont = this.$$$getFont$$$(null, -1, 16, listaAplicacoesCliente.getFont());
        if (listaAplicacoesClienteFont != null) listaAplicacoesCliente.setFont(listaAplicacoesClienteFont);
        scrollPane1.setViewportView(listaAplicacoesCliente);
        botaoResgatar = new JButton();
        botaoResgatar.setText("Resgatar");
        panel2.add(botaoResgatar, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        abaInvestimentos.add(panel3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        panel3.add(scrollPane2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane2.setBorder(BorderFactory.createTitledBorder(null, "Opções para investir", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        listaInvestimentos = new JList();
        Font listaInvestimentosFont = this.$$$getFont$$$(null, -1, 16, listaInvestimentos.getFont());
        if (listaInvestimentosFont != null) listaInvestimentos.setFont(listaInvestimentosFont);
        scrollPane2.setViewportView(listaInvestimentos);
        botaoInvestir = new JButton();
        botaoInvestir.setText("Investir");
        panel3.add(botaoInvestir, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JToolBar toolBar1 = new JToolBar();
        ClientePanel.add(toolBar1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        toolBar1.setBorder(BorderFactory.createTitledBorder(null, "IF BANK", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, this.$$$getFont$$$("Unispace", -1, 14, toolBar1.getFont()), null));
        nomeUsuario = new JLabel();
        nomeUsuario.setForeground(new Color(-12697532));
        nomeUsuario.setText("Nome do usuário");
        toolBar1.add(nomeUsuario);
        final JToolBar.Separator toolBar$Separator1 = new JToolBar.Separator();
        toolBar1.add(toolBar$Separator1);
        saldoUsuario = new JLabel();
        saldoUsuario.setForeground(new Color(-12697532));
        saldoUsuario.setText("Saldo");
        toolBar1.add(saldoUsuario);
        final JToolBar.Separator toolBar$Separator2 = new JToolBar.Separator();
        toolBar1.add(toolBar$Separator2);
        SaldoCheckBox = new JCheckBox();
        SaldoCheckBox.setForeground(new Color(-7039334));
        SaldoCheckBox.setText("Esconder saldo");
        toolBar1.add(SaldoCheckBox);
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
        return ClientePanel;
    }

}