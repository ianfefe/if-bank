package Usuarios;

import TiposAtributos.Endereco;

import java.util.Scanner;

public class Cliente extends Usuario{
    private double saldo;

    public Cliente(String nome, String endereco, String telefone, String email, int senha) {
        super(nome, endereco, telefone, email, senha);
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
        confirmarSenha();
        this.saldo -= valor;
    }

    void depositarSaldo(Usuario usuario,double valor){
        if(usuario instanceof Caixa || usuario instanceof Gerente){
            this.saldo += valor;
        }else{
            throw new RuntimeException("Apenas administradores podem acessar este recurso.");
        }
    }

    void confirmarSenha(){
        Scanner scanner = new Scanner(System.in);
        for(int i = 3; true; i--){
            int senhatemp = scanner.nextInt();
            if(senhatemp != this.senha){
                if(i == 1){
                    throw new RuntimeException("Tentativas esgotadas, cancelando operação.");
                }
                System.out.println("Senha incorreta, tente novamente" +
                        i + " tentativas restantes");
            }else{
                break;
            }
        }
    }

    float consultaSaldoExtrato(){
        //confirma operacao com senha
        return 1;
    }

    void investeFixo(){
        //autoriza investimento
        confirmarSenha();
    }

    void investeVariavel(){
        //autoriza investimento
        confirmarSenha();
    }
}
