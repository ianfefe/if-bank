package Usuarios;

import TiposAtributos.*;

import javax.swing.*;
import java.util.Objects;

public class Usuario {
    protected static int id = 0;

    protected String userID;
    protected String nome;
    protected DataDeNascimento dataNascimento;
    protected CPF cpf;
    protected Endereco endereco;
    protected Telefone telefone;
    protected Email email;
    protected String senha;
    protected String tipoUsuario;

    public Usuario(String nome,
                   DataDeNascimento dataNascimento,
                   CPF cpf,
                   Endereco endereco,
                   Telefone telefone,
                   Email email,
                   String senha) {

        this.userID = String.valueOf(id++);
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;

    }

    public String getNome() {
        return this.nome;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getTipoUsuario() {
        return this.tipoUsuario;
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

    public boolean verificaSenha(String senha) {
        return this.senha.equals(senha);
    }

    public void transferir(Cliente origem, Cliente destino, double valor) {
        if (confirmaValor(valor)) {
            Objects.requireNonNull(origem, "Conta de origem não encontrada.");
            Objects.requireNonNull(destino, "Conta destino não encontrada.");
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
