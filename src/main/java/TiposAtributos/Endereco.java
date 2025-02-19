package TiposAtributos;

import Exceptions.EnderecoException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Endereco {

    private String rua;
    private String numero;
    private String complemento;

    public Endereco(String rua, String numero, String complemento) throws EnderecoException {
        setEndereco(rua, numero, complemento);
    }

    private boolean isRuaValida(String rua) {
        String ruaRegex =
                "^[a-zA-Z- ]$";

        Pattern pattern = Pattern.compile(ruaRegex);
        Matcher matcher = pattern.matcher(rua);
        return matcher.matches();
    }

    private boolean isNumeroValido(String numero) {
        String numeroRegex =
                "^[0-9]+$";

        Pattern pattern = Pattern.compile(numeroRegex);
        Matcher matcher = pattern.matcher(numero);
        return matcher.matches();
    }

    private boolean isComplementoValido(String complemento) {

        switch (complemento){
            case "Casa", "Estabelecimento", "Apartamento":
                return true;
            default:
                throw new EnderecoException("Complemento inexistente");
        }
    }

    public String getRua() {
        return rua;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setEndereco(String rua, String numero, String complemento) throws EnderecoException {
        if(! isRuaValida(rua))
            if(! isNumeroValido(numero))
                if(! isComplementoValido(complemento))
                    throw new EnderecoException();

        this.complemento = complemento;
        this.numero = numero;
        this.rua = rua;
    }
}
