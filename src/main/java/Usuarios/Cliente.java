package Usuarios;

import TiposAtributos.CPF;
import TiposAtributos.Email;
import TiposAtributos.Endereco;
import TiposAtributos.Telefone;

import javax.swing.*;

public class Cliente extends Usuario{
    private double saldo;

    public Cliente(String nome,
                   String dataNascimento,
                   CPF cpf,
                   Endereco endereco,
                   Telefone telefone,
                   Email email,
                   String senha,
                   double saldo) {
        super(nome,dataNascimento,cpf,endereco,telefone,email,senha);
        tipoUsuario = "Cliente";
        this.saldo = 0;
    }

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
        }else if(this.getSaldo() == 0){
            throw new RuntimeException("Não pode fazer transferências sem valor");
        }
    }

    void sacar(double valor){
        confirmarSaldo(valor);
        //confirmarSenha();
        this.saldo -= valor;
        Sistema.salvaUsuarios();
    }

    void depositarSaldo(Usuario usuario,double valor){
//        if(usuario instanceof Caixa || usuario instanceof Gerente){
            this.saldo += valor;
//        }else{
//            throw new RuntimeException("Apenas administradores podem acessar este recurso.");
//        }

        Sistema.salvaUsuarios();
    }

    boolean confirmaSenha() {

        for (int i = 3; i > 0; i--) {
            String senhatemp = JOptionPane.showInputDialog("Digite a senha do usuário para confirmar a operação");
            if (this.verificaSenha(senhatemp)) {
                return true;
            } else {
                if(i > 1)
                    JOptionPane.showMessageDialog(null, "Senha incorreta, tente novamente. \n" + (i-1) + " tentativas restantes");
            }
        }
        return false;
    }

//    float consultaSaldoExtrato(){
//        //confirma operacao com senha
//        return 1;
//    }

    void investeFixo(){
        //autoriza investimento
        confirmaSenha();
    }

    void investeVariavel(){
        //autoriza investimento
        //confirmarSenha();
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

