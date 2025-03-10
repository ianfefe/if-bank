//Nome: Ian Felix Fernandes Matrícula: 202376007

package Usuario;

import Exceptions.SaldoException;
import Investimentos.Investimento;
import Investimentos.RendaFixa;
import TiposAtributos.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private final Extrato extrato;
    private final List<Investimento> investimentos = new ArrayList<>();
    private double saldo;

    public Cliente(Nome nome,
                   DataDeNascimento dataNascimento,
                   CPF cpf,
                   Endereco endereco,
                   Telefone telefone,
                   Email email,
                   String senha) {
        super(nome, dataNascimento, cpf, endereco, telefone, email, senha);
        this.saldo = 0;
        this.extrato = new Extrato();
        this.tipo = "Cliente";
    }

    public void investir(String investimentoNome, RendaFixa investimentoBase, Double valor) {
        confirmarSaldo(valor);
        if (confirmaSenha()) {
            this.sacar(valor, "Investimento: ");
            this.investimentos.add(new Investimento(investimentoNome, valor, investimentoBase));
            Sistema.salvaUsuarios();
        }
    }

    public List<Investimento> getInvestimentos() {
        return new ArrayList<>(this.investimentos);
    }

    public double getSaldo() {
        return this.saldo;
    }

    protected void confirmarSaldo(double valor) {
        if (this.getSaldo() < valor) {
            throw new SaldoException("Saldo insuficiente.");
        } else if (this.getSaldo() == 0) {
            throw new SaldoException("Não pode fazer transferências sem valor.");
        }
    }

    protected void sacar(double valor, String tipoTransferencia) {
        confirmarSaldo(valor);
        this.saldo -= valor;
        extrato.setSaida(valor, tipoTransferencia);
        Sistema.salvaUsuarios();
    }

    protected void depositar(double valor, String tipoTransferencia) {
        this.saldo += valor;
        extrato.setEntrada(valor, tipoTransferencia);
        Sistema.salvaUsuarios();
    }

    public void resgataInvestimento(Investimento aplicacao) {
        for (Investimento investimento : investimentos) {
            if (investimento.equals(aplicacao)) {
                if (confirmaSenha()) {
                    if (investimento.resgatarInvestimento() == 0) {
                        return;
                    }
                    this.depositar(investimento.resgatarInvestimento(), "Investimento: ");
                    investimentos.remove(investimento);
                    JOptionPane.showMessageDialog(null, "Investimento resgatado com sucesso!");
                    break;
                }
            }
        }
    }

    public List<String> getEntrada() {
        return this.extrato.getEntrada();
    }

    public List<String> getSaida() {
        return this.extrato.getSaida();
    }
}

