package TiposAtributos;

import java.util.ArrayList;
import java.util.List;

public class Extrato {

    private List<Double> entrada = new ArrayList<>();;
    private List<Double> saida = new ArrayList<>();;

    public void setEntrada(Double valor) {
        if (valor > 0){
            this.entrada.add(valor);
        }
    }

    public void setSaida(Double valor) {
        if (valor > 0){
            this.saida.add(valor);
        }
    }

    public List<Double> getEntrada(){
        return this.entrada;
    }

    public List<Double> getSaida(){
        return this.saida;
    }
}