package Usuarios;

import Investimentos.Investimento;

import Exceptions.SaldoException;
import TiposAtributos.*;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private final Extrato extrato;
    private double saldo;
    private final List<Investimento> investimentos;

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
        this.investimentos = new ArrayList<>();
    }

    public double getSaldo() {
        return this.saldo;
    }

    void confirmarSaldo(double valor) {
        if (this.getSaldo() < valor) {
            throw new SaldoException("Saldo insuficiente.");
        } else if (this.getSaldo() == 0) {
            throw new SaldoException("Não pode fazer transferências sem valor.");
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

    public String getSaldoString() {
        return String.valueOf(this.saldo);
    }

    public List<String> getEntrada() {
        return this.extrato.getEntrada();
    }

    public List<String> getSaida() {
        return this.extrato.getSaida();
    }
}

