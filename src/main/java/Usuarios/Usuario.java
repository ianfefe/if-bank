package Usuarios;

import TiposAtributos.CPF;
import TiposAtributos.Email;
import TiposAtributos.Endereco;
import TiposAtributos.Telefone;

import java.util.Objects;

public class Usuario {
    protected static int id = 0;

    protected int userID;
    protected String nome;
    protected String dataNascimento;
    protected CPF cpf;
    protected Endereco endereco;
    protected Telefone telefone;
    protected Email email;
    protected String senha;
    protected String tipoUsuario;

    public Usuario( String nome,
                    String dataNascimento,
                    CPF cpf,
                    Endereco endereco,
                    Telefone telefone,
                    Email email,
                    String senha){

        this.userID = id++;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;

    }

    public String getNome(){
        return this.nome;
    }

    public String getDataNascimento(){ return this.dataNascimento;}

    public CPF getCpf(){
        return this.cpf;
    }

    public String getCpfString(){
        return this.cpf.getCPF();
    }

    public Endereco getEndereco(){
        return this.endereco;
    }

    public Telefone getTelefone(){
        return this.telefone;
    }

    public Email getEmail(){
        return this.email;
    }

    public String getSenha(){
        return this.senha;
    }

    public String getTipo(){
        return this.tipoUsuario;
    }

    public void transferir(Cliente origem, Cliente destino, double valor){
        Objects.requireNonNull(origem, "Conta de origem não encontrada.");
        Objects.requireNonNull(destino, "Conta de destino não encontrada.");
        origem.confirmarSaldo(valor);
        origem.sacar(valor);
        destino.depositarSaldo(this,valor);
    }
}
