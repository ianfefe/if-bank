//Nome: Ian Felix Fernandes Matrícula: 202376007

package Frames;

import Usuarios.Cliente;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TransferenciaDestinoFrame extends JFrame {
    private JTextField campoValor;
    private JFormattedTextField campoDestino;
    private JButton botaoTransferencia;
    private JPanel transferenciaDestinoPanel;
    private JButton botaoCancelar;

    public TransferenciaDestinoFrame(Cliente clienteOrigem) {

        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(transferenciaDestinoPanel);
        Utility.aplicaMascaraCpf(campoDestino);
        Utility.adicionaBotaoCancelarOperacao(botaoCancelar, this);

        botaoTransferencia.addActionListener(e -> {
            Cliente destino = Utility.encontraCliente(campoDestino.getText());
            double valor = Utility.confereValorDouble(campoValor);
            clienteOrigem.transferir(clienteOrigem, destino, valor);
            dispose();
        });

        campoValor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Utility.formataValor(campoValor);
            }
        });
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
        transferenciaDestinoPanel = new JPanel();
        transferenciaDestinoPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        transferenciaDestinoPanel.setBorder(BorderFactory.createTitledBorder(null, "TRANSFERENCIA", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Conta destino");
        transferenciaDestinoPanel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        campoDestino = new JFormattedTextField();
        transferenciaDestinoPanel.add(campoDestino, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        botaoTransferencia = new JButton();
        botaoTransferencia.setText("Confirmar transferência");
        transferenciaDestinoPanel.add(botaoTransferencia, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Valor");
        transferenciaDestinoPanel.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        campoValor = new JTextField();
        transferenciaDestinoPanel.add(campoValor, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        botaoCancelar = new JButton();
        botaoCancelar.setText("Cancelar");
        transferenciaDestinoPanel.add(botaoCancelar, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return transferenciaDestinoPanel;
    }

}