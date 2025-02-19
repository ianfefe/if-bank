package Usuarios;

import TiposAtributos.Email;
import TiposAtributos.Endereco;
import TiposAtributos.Telefone;

import java.util.Objects;

public class Usuario {
    String nome;
    String cpf;
    String dataNascimento;
    Endereco endereco;
    Telefone telefone;
    Email email;
    protected String senha;

    public Usuario( String nome,
                    Endereco endereco,
                    Telefone telefone,
                    Email email,
                    String senha){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    void transferir(Cliente origem, Cliente destino, double valor){
        Objects.requireNonNull(origem, "Conta de origem não encontrada.");
        Objects.requireNonNull(destino, "Conta de destino não encontrada.");
        origem.confirmarSaldo(valor);
        origem.sacar(valor);
        destino.depositarSaldo(this,valor);
    }
}
