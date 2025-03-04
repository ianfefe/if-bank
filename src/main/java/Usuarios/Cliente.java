package Usuarios;

import Persistencias.Sistema;
import TiposAtributos.*;

import javax.swing.*;
import java.util.List;

public class Cliente extends Usuario {
    private double saldo;
    private Extrato extrato;

    public Cliente(String nome,
                   DataDeNascimento dataNascimento,
                   CPF cpf,
                   Endereco endereco,
                   Telefone telefone,
                   Email email,
                   String senha) {
        super(nome, dataNascimento, cpf, endereco, telefone, email, senha);
        tipoUsuario = "Cliente";
        this.saldo = 0;
        this.extrato = new Extrato();
    }

    public double getSaldo() {
        return this.saldo;
    }

    void confirmarSaldo(double valor) {
        if (this.getSaldo() < valor) {
            throw new RuntimeException("Saldo insuficiente.");
        } else if (this.getSaldo() == 0) {
            throw new RuntimeException("Não pode fazer transferências sem valor.");
        }
    }

    protected void sacar(double valor, String tipoTransferencia) {
        confirmarSaldo(valor);
        this.saldo -= valor;
        Sistema.salvaUsuarios();
        extrato.setSaida(valor, tipoTransferencia);
    }

    protected void depositar(double valor, String tipoTransferencia) {
        this.saldo += valor;
        Sistema.salvaUsuarios();
        extrato.setEntrada(valor, tipoTransferencia);
    }

    boolean confirmaSenha() {

        for (int i = 3; i > 0; i--) {
            String senhatemp = JOptionPane.showInputDialog("Digite a senha do usuário para confirmar a operação");
            if (this.verificaSenha(senhatemp)) {
                return true;
            } else {
                if (i > 1)
                    JOptionPane.showMessageDialog(null, "Senha incorreta, tente novamente. \n" + (i - 1) + " tentativas restantes");
            }
        }
        return false;
    }

//    float consultaSaldoExtrato(){
//        //confirma operacao com senha
//        return 1;
//    }

    void investeFixo() {
        //autoriza investimento
        confirmaSenha();
    }

    void investeVariavel() {
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
                ", tipoUsuario='" + tipoUsuario + '\'' +
                ", extrato='" + extrato + '\'' +
                '}';
    }

    public String getSaldoString() {
        return String.valueOf(this.saldo);
    }

    public List<String> getEntrada(){
        return this.extrato.getEntrada();
    }

    public List<String> getSaida(){
        return this.extrato.getSaida();
    }
}

