package Usuarios;

import TiposAtributos.CPF;
import TiposAtributos.Email;
import TiposAtributos.Endereco;
import TiposAtributos.Telefone;

public class Gerente extends Usuario{
    public Gerente(String nome,
                   String dataNascimento,
                   CPF cpf,
                   Endereco endereco,
                   Telefone telefone,
                   Email email,
                   String senha) {
        super(nome,dataNascimento,cpf,endereco,telefone,email,senha);
        tipoUsuario = "Gerente";
    }

    void cadastraRendaFixa(){

    }

    void cadastraRendaVariavel(){

    }

    void ajudaTransferencia(){

    }

    @Override
    public String toString() {
        return "Gerente{" +
                "nome='" + nome + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

}
