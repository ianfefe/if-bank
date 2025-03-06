package Investimentos;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Investimento {
    private final double valorAplicado;
    private final String dataInicio;
    private final InvestimentoBase tipoInvestimento;

    public Investimento(double valorAplicado, InvestimentoBase tipoInvestimento) {
        if (tipoInvestimento == null || valorAplicado <= 0) {
            throw new RuntimeException("Investimento inválido.");
        }

        this.valorAplicado = valorAplicado;
        this.tipoInvestimento = tipoInvestimento;

        LocalDate data = LocalDate.now();
        DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        this.dataInicio = data.format(formataData);
    }

    private LocalDate getDataInicioDate() {

        DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return LocalDate.parse(this.dataInicio, formataData);
    }

    public double resgatarInvestimento() {
        double imposto;
        double rendimentoBruto = valorAplicado * tipoInvestimento.calculaRendimento(getDataInicioDate());

        LocalDate hoje = LocalDate.now();
        int tempoPassado = Period.between(getDataInicioDate(), hoje).getDays();

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
        if (tipoInvestimento.expirouAplicacao()) {
            resgatarInvestimento();
        }
    }
}
