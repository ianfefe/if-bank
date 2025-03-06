package Investimentos;

import java.time.LocalDate;

public interface InvestimentoBase {
    String getNome();

    String getTipo();

    double calculaRendimento(LocalDate dataInicioAplicacao);

    boolean expirouAplicacao();
}
