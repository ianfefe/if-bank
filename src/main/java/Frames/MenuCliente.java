package Frames;

import Usuarios.Cliente;
import Usuarios.Persistencia;
import Usuarios.Sistema;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MenuCliente extends JFrame{
    private JLabel nomeUsuario;
    private JLabel saldoUsuario;
    private JButton botaoConfiguracoes;
    private JCheckBox SaldoCheckBox;
    private JScrollBar scrollBar1;
    private JScrollBar scrollBar3;
    private JPanel ClientePanel;
    private JTabbedPane menuPanel;
    private JTabbedPane abaInvestimento;
    private JPanel abaExtrato;
    private JPanel abaNotificacoes;
    private JTabbedPane abaRendaVariavel;
    private JPanel abaRendaFixa;
    private JButton botaoTransferir;
    private JButton botaoSair;

    MenuCliente(Cliente usuarioLogado){
        setSize(900,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(ClientePanel);
        nomeUsuario.setText(usuarioLogado.getNome());
        atualizaSaldoView(usuarioLogado);
        Persistencia.salvarUsuarios(Sistema.getUsuarios());
        System.out.println(usuarioLogado.getSaldoString());
        System.out.println(usuarioLogado.getSaldo());

        SaldoCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    SaldoCheckBox.setText("Mostrar saldo");
                    saldoUsuario.setText("***.**");
                }else{
                    SaldoCheckBox.setText("Esconder saldo");
                    atualizaSaldoView(usuarioLogado);
                }
            }
        });
    }

    private void atualizaSaldoView(Cliente usuarioLogado){
        saldoUsuario.setText(usuarioLogado.getSaldoString());
        }

};