//Nome: Ian Felix Fernandes Matrícula: 202376007

package Frames;

import Investimentos.RendaFixa;
import Usuarios.Cliente;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class InvestimentoRendaFixa extends JFrame {
    private JLabel resgateMinTexto;
    private JLabel resgateMaxTexto;
    private JLabel rendimentoTexto;
    private JLabel tipoInvestimentoTexto;
    private JLabel nomeInvestimentoTexto;
    private JTextField campoValor;
    private JButton botaoConfirmar;
    private JButton botaoCancelar;
    private JPanel rendaFixaPanel;

    public InvestimentoRendaFixa(RendaFixa investimento, Cliente usuarioLogado) {
        setSize(480, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setContentPane(rendaFixaPanel);
        Utility.adicionaBotaoCancelarOperacao(botaoCancelar, this);
        mostraInformacoes(investimento);

        botaoConfirmar.addActionListener(e -> {
            try {
                double valor = Utility.confereValorDouble(campoValor);
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(null, "Não é possivel investir valores inferiores ou igual a 0.");
                    return;
                }
                usuarioLogado.investir(investimento.getNome(), investimento, valor);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possivel completar a operação.", "ERRO", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(null, "Aplicação feita com sucesso.");
            dispose();
        });

        campoValor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                Utility.formataValor(campoValor);
            }
        });
    }

    public void mostraInformacoes(RendaFixa investimento) {
        nomeInvestimentoTexto.setText(investimento.getNome());
        tipoInvestimentoTexto.setText(investimento.getTipo());
        resgateMinTexto.setText(investimento.getResgateMinDias() + " dias");
        resgateMaxTexto.setText(investimento.getResgateMaxDias() + " dias");
        rendimentoTexto.setText(String.format("%.2f", investimento.getRendimento()) + "%");
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
        rendaFixaPanel = new JPanel();
        rendaFixaPanel.setLayout(new GridBagLayout());
        rendaFixaPanel.setOpaque(false);
        rendaFixaPanel.setPreferredSize(new Dimension(480, 300));
        rendaFixaPanel.setBorder(BorderFactory.createTitledBorder(null, "INVESTIR", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        botaoConfirmar = new JButton();
        botaoConfirmar.setText("Confirmar investimento");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rendaFixaPanel.add(botaoConfirmar, gbc);
        botaoCancelar = new JButton();
        botaoCancelar.setText("Cancelar");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rendaFixaPanel.add(botaoCancelar, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        rendaFixaPanel.add(panel1, gbc);
        campoValor = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(campoValor, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Valor a aplicar:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Nome:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        rendaFixaPanel.add(label2, gbc);
        nomeInvestimentoTexto = new JLabel();
        nomeInvestimentoTexto.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        rendaFixaPanel.add(nomeInvestimentoTexto, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Tipo: ");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        rendaFixaPanel.add(label3, gbc);
        tipoInvestimentoTexto = new JLabel();
        tipoInvestimentoTexto.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        rendaFixaPanel.add(tipoInvestimentoTexto, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Rendimento:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        rendaFixaPanel.add(label4, gbc);
        rendimentoTexto = new JLabel();
        rendimentoTexto.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        rendaFixaPanel.add(rendimentoTexto, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("Tempo mínimo de resgate:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        rendaFixaPanel.add(label5, gbc);
        resgateMinTexto = new JLabel();
        resgateMinTexto.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        rendaFixaPanel.add(resgateMinTexto, gbc);
        resgateMaxTexto = new JLabel();
        resgateMaxTexto.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        rendaFixaPanel.add(resgateMaxTexto, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("Tempo máximo de resgate:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        rendaFixaPanel.add(label6, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rendaFixaPanel;
    }

}