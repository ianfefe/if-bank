import java.util.Scanner;

public class Cliente extends Usuario{
    double saldo;
    String agencia;

    public double getSaldo(){
        return this.saldo;
    }

//    void transferir(Cliente destino,double valor){
//        destino.depositar(valor);
//        confirmarSenha();
//        //valida dados e executa transferencia se possivel
//    }

    void depositar(double valor){
        confirmarSenha();
        this.saldo += valor;
    }

    void sacar(double valor){
        confirmarSenha();
        this.saldo -= valor;
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

    void solicitarCredito(){
        //solicita emprestimo ou financiamento
        //gerente analisa
        //cliente confirma condicoes
        confirmarSenha();
    }

    boolean confirmarSenha(){
        Scanner scan = new Scanner(System.in);

        //senha deve ter 6 digitos obrigatoriamente, apenas numeros***
        int senhaTemp = scan.nextInt();

        return senhaTemp == this.senha;
    }

}
