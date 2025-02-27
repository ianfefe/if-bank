package Usuarios;

import TiposAtributos.CPF;
import TiposAtributos.Email;
import TiposAtributos.Endereco;
import TiposAtributos.Telefone;

public class Cliente extends Usuario{
    private double saldo;

    public Cliente(String nome,
                   String dataNascimento,
                   CPF cpf,
                   Endereco endereco,
                   Telefone telefone,
                   Email email,
                   String senha) {
        super(nome,dataNascimento,cpf,endereco,telefone,email,senha);
        tipoUsuario = "Cliente";
        this.saldo = 0;
    }

    public double getSaldo(){
        return this.saldo;
    }

    void confirmarSaldo(double valor){
        if(this.getSaldo() < valor){
            throw new RuntimeException("Saldo insuficiente");
        }
    }

    void sacar(double valor){
        confirmarSaldo(valor);
//        confirmarSenha();
        this.saldo -= valor;
    }

    void depositarSaldo(Usuario usuario,double valor){
//        if(usuario instanceof Caixa || usuario instanceof Gerente){
            this.saldo += valor;
//        }else{
//            throw new RuntimeException("Apenas administradores podem acessar este recurso.");
//        }
    }

    float consultaSaldoExtrato(){
        //confirma operacao com senha
        return 1;
    }

    void investeFixo(){
        //autoriza investimento
//        confirmarSenha();
    }

    void investeVariavel(){
        //autoriza investimento
//        confirmarSenha();
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }

    public String getSaldoString() {
        return String.valueOf(this.saldo);
    }
}
