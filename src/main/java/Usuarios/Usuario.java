package Usuarios;

import TiposAtributos.Endereco;

import java.util.Objects;

public class Usuario {
    String nome;
    Endereco endereco;
    String telefone;
    String email;
    protected int senha;

    public Usuario( String nome,
                    Endereco endereco,
                    String telefone,
                    String email,
                    int senha){
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
