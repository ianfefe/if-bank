//Nome: Ian Felix Fernandes Matrícula: 202376007

package Frames;

import TiposAtributos.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

import static Usuarios.Sistema.criarUsuario;

public class CadastroAdmFrame extends CadastroFrame {
    public CadastroAdmFrame() {

        setSize(900, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        tiposUsuarioPanel.setVisible(true);

    }

    @Override
    protected void voltar(ActionEvent e) {
        int sair = JOptionPane.showConfirmDialog(null, "Deseja cancelar a operação? Todo progresso será perdido.", "Cancelar operação.", JOptionPane.YES_NO_OPTION);
        if (sair == 0) {
            dispose();
        }
    }

    @Override
    protected void cadastrar(ActionEvent e) {
        String senha = new String(campoSenha.getPassword());
        String senhaConfirma = new String(campoSenhaConfirma.getPassword());

        if (senha.equals(senhaConfirma)) {
            if (validarCampos(e, (String) comboBoxTipoUsuario.getSelectedItem(), "Cadastro")) {
                criarUsuario(
                        campoNome.getText(),
                        new DataDeNascimento(campoDataNascimento.getText()),
                        new CPF(campoCPF.getText()),
                        new Endereco(campoRua.getText(), campoNumeroEndereco.getText(), (String) complementoBox.getSelectedItem()),
                        new Telefone(campoTelefone.getText()),
                        new Email(campoEmail.getText()),
                        new String(campoSenha.getPassword()),
                        (String) Objects.requireNonNull(comboBoxTipoUsuario.getSelectedItem()));

                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(campoSenhaConfirma, "Senha diferente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
