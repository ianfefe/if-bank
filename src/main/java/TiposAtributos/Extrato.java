package TiposAtributos;

import java.util.ArrayList;
import java.util.List;

public class Extrato {

    private List<String> entrada = new ArrayList<>();;
    private List<String> saida = new ArrayList<>();;

    public void setEntrada(Double valor, String tipoTransacao) {
        String entradaExtrato = (tipoTransacao + "R$ " + String.format("%.2f", valor));
        if (valor > 0)
            this.entrada.add(entradaExtrato);
    }

    public void setSaida(Double valor, String tipoTransacao) {
        String entradaExtrato = (tipoTransacao + "R$ " + String.format("%.2f", valor));
        if (valor > 0)
            this.entrada.add(entradaExtrato);
    }

    public List<String> getEntrada(){
        return this.entrada;
    }

    public List<String> getSaida(){
        return this.saida;
    }
}