package Frames;

import Usuarios.Administrador;
import Usuarios.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class SaqueFrame extends JFrame {
    private JFormattedTextField campoConta;
    private JTextField campoValor;
    private JButton botaoConfirmar;
    private JPanel saquePanel;
    private JButton botaoCancelar;

    public SaqueFrame(Administrador usuarioLogado) {
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(saquePanel);
        Utility.aplicaMascaraCpf(campoConta);
        Utility.adicionaBotaoCancelarOperacao(botaoCancelar,this);

        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                double valor = Utility.confereValorDouble(campoValor);
                Cliente origem = Utility.encontraCliente(campoConta.getText());
                usuarioLogado.realizarSaque(origem, valor);
                dispose();
            }
        });

        campoValor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Utility.formataValor(campoValor);
            }
        });
    }

}
