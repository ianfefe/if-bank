package Usuarios;

import TiposAtributos.Email;
import TiposAtributos.Endereco;
import TiposAtributos.Telefone;

public class Caixa extends Usuario{

    public Caixa(String nome, Endereco endereco, Telefone telefone, Email email, String senha) {
        super(nome, endereco, telefone, email, senha);
    }
}
