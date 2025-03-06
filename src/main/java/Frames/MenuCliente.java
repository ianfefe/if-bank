package Frames;

import Investimentos.InvestimentoBase;
import Investimentos.RendaFixa;
import Investimentos.RendaVariavel;
import Usuarios.Cliente;
import Usuarios.Gerente;
import Usuarios.Usuario;

import javax.swing.*;
import java.awt.event.*;

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

    MenuCliente(Cliente usuarioLogado) {
        setSize(900, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(ClientePanel);
        nomeUsuario.setText(usuarioLogado.getNome());
        Utility.adicionaOpcaoDeslogarUsuario(botaoSair, this);
        atualizaSaldoView(usuarioLogado);
        desenhaListaInvestimentos();

        SaldoCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    SaldoCheckBox.setText("Mostrar saldo");
                    saldoUsuario.setText("***.**");
                } else {
                    SaldoCheckBox.setText("Esconder saldo");
                    atualizaSaldoView(usuarioLogado);
                }
            }
        });

        botaoTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new TransferenciaDestinoFrame(usuarioLogado).setVisible(true);
                });
            }
        });

        botaoPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new EdicaoDadosFrame(usuarioLogado, usuarioLogado).setVisible(true);
                });
            }
        });

        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (!saldoUsuario.getText().equals("***.**")) {
                    atualizaSaldoView(usuarioLogado);
                }
                desenhaListaEntrada(usuarioLogado);
                desenhaListaSaida(usuarioLogado);
            }
        });
        timer.start();

        abaInvestimentos.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                JOptionPane.showMessageDialog(null, "Aba em manutenção, disponível somente para visualização.");
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

        DefaultListModel<InvestimentoBase> investimentosBaseDefaultListModel = new DefaultListModel<>();
        for (RendaFixa investimento : Gerente.getListaRendaFixa()) {
            if (investimento != null) {
                investimentosBaseDefaultListModel.addElement(investimento);
            } else {
                break;
            }
        }
        for (RendaVariavel investimento : Gerente.getListaRendaVariavel()) {
            if (investimento != null) {
                investimentosBaseDefaultListModel.addElement(investimento);
            } else {
                break;
            }
        }

        DefaultListModel<String> listaInvestimentoString = new DefaultListModel<>();
        for (int i = 0; i < investimentosBaseDefaultListModel.getSize(); i++) {
            listaInvestimentoString.addElement(investimentosBaseDefaultListModel.getElementAt(i).getTipo() + " - " + investimentosBaseDefaultListModel.getElementAt(i).getNome());
        }

        if (!investimentosBaseDefaultListModel.isEmpty()) {
            listaInvestimentos.setModel(listaInvestimentoString);
        }
    }

}