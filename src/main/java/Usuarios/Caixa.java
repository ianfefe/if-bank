package Usuarios;

import TiposAtributos.CPF;
import TiposAtributos.Email;
import TiposAtributos.Endereco;
import TiposAtributos.Telefone;

public class Caixa extends Usuario{

    public Caixa(String nome,
                 String dataNascimento,
                 CPF cpf,
                 Endereco endereco,
                 Telefone telefone,
                 Email email,
                 String senha) {
        super(nome,dataNascimento,cpf,endereco,telefone,email,senha);
        userID = (id++) + "C";
        tipoUsuario = "Caixa";
    }
}