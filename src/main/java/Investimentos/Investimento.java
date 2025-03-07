//Nome: Ian Felix Fernandes Matrícula: 202376007

package Investimentos;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Investimento {
    private final String dataInicio;
    private final String nome;
    private double valorAplicado;
    private RendaFixa investimento;
    private boolean estado = true;

    public Investimento(String nome, double valorAplicado, RendaFixa investimento) {
        Objects.requireNonNull(investimento);
        if (valorAplicado <= 0) {
            throw new RuntimeException("Investimento inválido.");
        }

        this.investimento = investimento;
        this.nome = nome;
        this.valorAplicado = valorAplicado;
        this.dataInicio = new Data().dataToString(LocalDate.now());
    }

    public String getNome() {
        return this.nome;
    }

    public boolean getEstado() {
        return this.estado;
    }

    public double getValorAplicado() {
        return this.valorAplicado;
    }

    public double resgatarInvestimento() {

        double imposto;
        try {
            this.investimento.calculaRendimento(new Data().dataToDate(dataInicio));
        } catch (RuntimeException ex) {
            return 0;
        }
        double rendimentoBruto = valorAplicado * this.investimento.calculaRendimento(new Data().dataToDate(dataInicio));

        LocalDate hoje = LocalDate.now();
        int tempoPassado = Period.between(new Data().dataToDate(dataInicio), hoje).getDays();

        if (tempoPassado < 180) {
            imposto = (rendimentoBruto - valorAplicado) * 0.225;
        } else if (tempoPassado < 360) {
            imposto = (rendimentoBruto - valorAplicado) * 0.2;
        } else if (tempoPassado < 720) {
            imposto = (rendimentoBruto - valorAplicado) * 0.175;
        } else {
            imposto = (rendimentoBruto - valorAplicado) * 0.15;
        }

        return rendimentoBruto - imposto;
    }

    public void aplicacaoExpirada() {
        if (this.investimento.expirouAplicacao(new Data().dataToDate(dataInicio))) {
            resgatarInvestimento();
        }
    }
}
