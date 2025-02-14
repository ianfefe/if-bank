package Usuarios;

import TiposAtributos.Endereco;

public class Caixa extends Usuario{

    public Caixa(String nome, Endereco endereco, String telefone, String email, int senha) {
        super(nome, endereco, telefone, email, senha);
    }
}
