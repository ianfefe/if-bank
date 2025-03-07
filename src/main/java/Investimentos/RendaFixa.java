//Nome: Ian Felix Fernandes Matrícula: 202376007

package Investimentos;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RendaFixa {
    private final String nome;
    private final String tipo = "Renda Fixa";
    private final int resgateMinDias;
    private final int resgateMaxDias;
    private final double rendimento;

    public RendaFixa(String nome, int resgateMinDias, int resgateMaxDias, double rendimentoAnual) {
        if (resgateMinDias > resgateMaxDias) {
            JOptionPane.showMessageDialog(null, "A data de resgate mínima deve anteceder o tempo de resgate limite.");
            throw new RuntimeException("Data de resgate mínimo superior à limite.");
        }
        if (rendimentoAnual <= 0) {
            JOptionPane.showMessageDialog(null, "Rendimento do investimento deve ser superior a zero.");
            throw new RuntimeException("Investimento com rendimento abaixo de zero");
        }
        this.nome = nome;
        this.resgateMinDias = resgateMinDias;
        this.resgateMaxDias = resgateMaxDias;
        this.rendimento = rendimentoAnual / 100;
        JOptionPane.showMessageDialog(null, "Investimento criado com sucesso.");
    }

    public double getRendimento() {
        return rendimento;
    }

    public int getResgateMaxDias() {
        return resgateMaxDias;
    }

    public int getResgateMinDias() {
        return resgateMinDias;
    }

    public boolean expirouAplicacao(LocalDate dataInicio) {

        DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate resgateMax = dataInicio.plusDays(this.resgateMaxDias);

        return LocalDate.now().isEqual(resgateMax);
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return this.tipo;
    }

    public double calculaRendimento(LocalDate inicioAplicacao) {

        DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate resgateMinData = inicioAplicacao.plusDays(this.resgateMinDias);

        if (resgateMinData.isAfter(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "Resgate indisponível, confira o prazo de resgate mínimo.");
            throw new RuntimeException("Data de resgate anterior ao resgate mínimo.");
        } else {
            double tempoPassado = inicioAplicacao.until(LocalDate.now()).getDays();
            return Math.pow(1 + rendimento, tempoPassado / 365.0);
        }
    }
}
