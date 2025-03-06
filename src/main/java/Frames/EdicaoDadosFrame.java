package Frames;

import TiposAtributos.Email;
import TiposAtributos.Endereco;
import TiposAtributos.Telefone;
import Usuarios.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static Usuarios.Sistema.editarUsuario;

public class EdicaoDadosFrame extends CadastroFrame {
    private final Usuario usuario;
    private final Usuario usuarioLogado;

    public EdicaoDadosFrame(Usuario usuario, Usuario usuarioLogado) {
        this.usuario = usuario;
        this.usuarioLogado = usuarioLogado;

        setSize(900, 600);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        campoCPF.setFocusable(false);
        campoCPF.setEditable(false);
        campoDataNascimento.setFocusable(false);
        campoDataNascimento.setEditable(false);
        preencheDadosDoUsuario();

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
        if (usuarioLogado.confirmaSenha()) {
            String senha = new String(campoSenha.getPassword());
            String senhaConfirma = new String(campoSenhaConfirma.getPassword());

            if (senha.equals(senhaConfirma)) {
                if (validarCampos(e, (String) comboBoxTipoUsuario.getSelectedItem(), "Edição")) {
                    editarUsuario(
                            this.usuario,
                            campoNome.getText(),
                            new Endereco(campoRua.getText(), campoNumeroEndereco.getText(), (String) complementoBox.getSelectedItem()),
                            new Telefone(campoTelefone.getText()),
                            new Email(campoEmail.getText()),
                            new String(campoSenha.getPassword()));
                    JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(campoSenhaConfirma, "Senha diferente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso.\n Clique em Recarregar para ver as alterações.");
        }
    }

    private void preencheDadosDoUsuario() {
        campoNome.setText(this.usuario.getNome());
        campoCPF.setText(this.usuario.getCpfString());
        campoDataNascimento.setText(this.usuario.getDataNascimento().getDataDeNascimentoString());
        campoTelefone.setText(this.usuario.getTelefone().getTelefone());
        campoEmail.setText(this.usuario.getEmail().getEmail());
        campoNumeroEndereco.setText(this.usuario.getEndereco().getNumero());
        campoRua.setText(this.usuario.getEndereco().getRua());
        complementoBox.setSelectedItem(this.usuario.getEndereco().getComplemento());
    }
}
