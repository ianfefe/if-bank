//Nome: Ian Felix Fernandes Matrícula: 202376007

package Usuario;

import TiposAtributos.*;

import javax.swing.*;
import java.util.Objects;

public class Usuario {
    protected String tipo = "Usuario";
    protected final DataDeNascimento dataNascimento;
    protected final CPF cpf;
    protected int id = Sistema.getUsuarios().size();
    protected String userID = String.valueOf(id);
    protected String nome;
    protected Endereco endereco;
    protected Telefone telefone;
    protected Email email;
    protected String senha;

    public Usuario(String nome,
                   DataDeNascimento dataNascimento,
                   CPF cpf,
                   Endereco endereco,
                   Telefone telefone,
                   Email email,
                   String senha) {

        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;

    }

    protected void editaUsuario(String nome,
                                Endereco endereco,
                                Telefone telefone,
                                Email email,
                                String senha) {

        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        Sistema.salvaUsuarios();
    }

    public boolean confirmaSenha() {

        for (int i = 3; i > 0; i--) {
            String senhatemp = JOptionPane.showInputDialog("Digite a senha do usuário para confirmar a operação");

            if (senhatemp == null) {
                return false;
            } else if (this.comparaSenha(senhatemp)) {
                return true;
            } else {
                if (i > 1)
                    JOptionPane.showMessageDialog(null, "Senha incorreta, tente novamente. \n" + (i - 1) + " tentativas restantes");
            }
        }
        return false;
    }

    public boolean comparaSenha(String senha) {
        return this.senha.equals(senha);
    }

    public String getTipo(){
        return this.tipo;
    }

    public String getNome() {
        return this.nome;
    }

    public String getUserID() {
        return this.userID;
    }

    public DataDeNascimento getDataNascimento() {
        return this.dataNascimento;
    }

    public CPF getCpf() {
        return this.cpf;
    }

    public String getCpfString() {
        return this.cpf.getCPF();
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public Telefone getTelefone() {
        return this.telefone;
    }

    public Email getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void transferir(Cliente origem, Cliente destino, double valor) {
        if (confirmaValor(valor)) {
            Objects.requireNonNull(origem, "Conta de origem não encontrada para transferência.");
            Objects.requireNonNull(destino, "Conta destino não encontrada para transferência.");
            if (destino.getCpfString().equals(origem.getCpfString())) {
                JOptionPane.showMessageDialog(null, "Não é possível transferir para a própria conta.", "", JOptionPane.ERROR_MESSAGE);
                return;
            }
            origem.confirmarSaldo(valor);
            if (origem.confirmaSenha()) {
                origem.sacar(valor, "Transferência: ");
                destino.depositar(valor, "Transferência: ");
                JOptionPane.showMessageDialog(null, "Transferência concluída com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Senha incorreta, cancelando operação.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    protected boolean confirmaValor(double valor) {
        if (valor > 0) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Valor não aceito", "Valor inválido", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
