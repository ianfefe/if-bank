//Nome: Ian Felix Fernandes Matrícula: 202376007

package Frames;

import Investimentos.InvestimentoBase;
import Investimentos.RendaFixa;
import Investimentos.RendaVariavel;
import Usuarios.Gerente;
import Usuarios.Sistema;
import Usuarios.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public MenuGerente(Gerente usuarioLogado) {
        setSize(900, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(gerentePanel);
        nomeUsuario.setText("GERENTE - " + usuarioLogado.getNome());
        Utility.adicionaOpcaoDeslogarUsuario(botaoSair, this);
        desenhaListaUsuarios(usuarioLogado);
        desenhaListaInvestimentos();

        botaoTransferir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new TransferenciaCompletaFrame().setVisible(true);
                });
            }
        });

        botaoSaque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new SaqueFrame(usuarioLogado).setVisible(true);
                });
            }
        });

        botaoDeposito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new DepositoFrame(usuarioLogado).setVisible(true);
                });
            }
        });

        botaoCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new CadastroAdmFrame().setVisible(true);
                });
            }
        });

        recarregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desenhaListaUsuarios(usuarioLogado);
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
            }
        });

        botaoEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel<Usuario> modelo = listaModeloUsuarios();
                int indice = listaUsuarios.getSelectedIndex();
                SwingUtilities.invokeLater(() -> {
                    new EdicaoDadosFrame(modelo.getElementAt(indice), usuarioLogado).setVisible(true);
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

        botaoCriarInvestimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new CriaInvestimento(usuarioLogado).setVisible(true);
                });
            }
        });

        botaoRecarregarInvestimentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                desenhaListaInvestimentos();
            }
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
            listaInvestimentosGerente.setModel(listaInvestimentoString);
        }
    }
}

