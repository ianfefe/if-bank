package Usuarios;

import TiposAtributos.Email;
import TiposAtributos.Endereco;
import TiposAtributos.Telefone;

public class Gerente extends Usuario{
    public Gerente(String nome, Endereco endereco, Telefone telefone, Email email, String senha) {
        super(nome, endereco, telefone, email, senha);
    }

    void cadastraRendaFixa(){

    }

    void cadastraRendaVariavel(){

    }

    void ajudaTransferencia(){

    }
}
