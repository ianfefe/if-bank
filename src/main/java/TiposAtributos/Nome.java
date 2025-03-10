package TiposAtributos;

import Exceptions.NomeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Nome {
    private String nome;

    public Nome(String nome){
        setNome(nome);
    }

    private boolean isNomeValido(String nome) {
        String nomeRegex = "^[\\p{L}\\s]+$";

        Pattern pattern = Pattern.compile(nomeRegex);
        Matcher matcher = pattern.matcher(nome);
        return matcher.matches();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (!isNomeValido(nome))
            throw new NomeException();

        this.nome = nome;
    }
}
