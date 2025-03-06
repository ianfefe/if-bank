package Investimentos;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class RendaFixa implements InvestimentoBase {
    private final String nome;
    private final String tipo = "Renda Fixa";
    private final String resgateMin;
    private final String resgateMax;
    private final double rendimento;


    public RendaFixa(String nome, String resgateMinString, String resgateMaxString, double rendimentoAnual) {

        DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate resgateMin = LocalDate.parse(resgateMinString, formataData);
        LocalDate resgateMax = LocalDate.parse(resgateMaxString, formataData);

        if (!resgateMin.isBefore(resgateMax)) {
            JOptionPane.showMessageDialog(null, "A data de resgate mínima deve anteceder o tempo de resgate limite.");
            throw new RuntimeException("Data de resgate mínimo superior à limite.");
        }
        if (rendimentoAnual <= 0) {
            JOptionPane.showMessageDialog(null, "Rendimento do investimento deve ser superior a zero.");
            throw new RuntimeException("Investimento com rendimento abaixo de zero");
        }
        this.nome = nome;
        this.resgateMin = resgateMinString;
        this.resgateMax = resgateMaxString;
        this.rendimento = rendimentoAnual / 100;
    }

    @Override
    public boolean expirouAplicacao() {

        DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.now().isEqual(LocalDate.parse(this.resgateMax, formataData));
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getTipo() {
        return this.tipo;
    }

    @Override
    public double calculaRendimento(LocalDate inicioAplicacao) {

        DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate resgateMinData = LocalDate.parse(this.resgateMin, formataData);

        if (resgateMinData.isAfter(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "Resgate indisponível, confira o prazo de resgate mínimo.");
            throw new RuntimeException("Data de resgate anterior ao resgate mínimo.");
        } else {
            double tempoPassado = inicioAplicacao.until(LocalDate.now()).getDays();
            return Math.pow(1 + rendimento, tempoPassado / 365.0);
        }
    }
}
