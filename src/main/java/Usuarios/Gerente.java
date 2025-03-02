package Usuarios;

import TiposAtributos.CPF;
import TiposAtributos.Email;
import TiposAtributos.Endereco;
import TiposAtributos.Telefone;

import javax.swing.*;
import java.util.Objects;

public class Gerente extends Usuario implements Administrador{
    public Gerente(String nome,
                   String dataNascimento,
                   CPF cpf,
                   Endereco endereco,
                   Telefone telefone,
                   Email email,
                   String senha) {
        super(nome,dataNascimento,cpf,endereco,telefone,email,senha);
        userID = (id++) + "G";
        tipoUsuario = "Gerente";
    }

    void cadastraRendaFixa(){

    }

    void cadastraRendaVariavel(){

    }

    void ajudaTransferencia(){

    }

    @Override
    public void realizarSaque(Cliente origem, double valor) {origem.confirmarSaldo(valor);
        if(confirmaValor(valor)){
            if(origem.confirmaSenha()){
                origem.sacar(valor);
                JOptionPane.showMessageDialog(null,"Saque concluído com sucesso.");
            }else{
                JOptionPane.showMessageDialog(null,"Senha incorreta, cancelando operação.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void realizarDeposito(Cliente destino ,double valor) {
        if(confirmaValor(valor)){
            destino.depositar(valor);
            JOptionPane.showMessageDialog(null, "Depósito concluído com sucesso.");
        }
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
