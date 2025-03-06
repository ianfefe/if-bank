package Investimentos;

import java.time.LocalDate;

public class RendaVariavel implements InvestimentoBase {

    double risco;
    double rentabilidade;
    private String nome;
    private final String tipo = "Renda Variavel";

    RendaVariavel(double risco, double rentabilidade) {
        this.risco = risco;
        this.rentabilidade = rentabilidade;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }

    @Override
    public double calculaRendimento(LocalDate dataInicioAplicacao) {
        return 0;
    }

    @Override
    public boolean expirouAplicacao() {
        return false;
    }
}
