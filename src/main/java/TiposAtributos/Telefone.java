package TiposAtributos;

import Exceptions.TelefoneException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Telefone {

    private String telefone;

    public Telefone(String telefone) throws TelefoneException {
        setTelefone(telefone);
    }

    private boolean isTelefoneValido(String email) {
        String telefonRegex =
                "^[0-9]{8,9}$";

        Pattern pattern = Pattern.compile(telefonRegex);
        Matcher matcher = pattern.matcher(telefone);
        return matcher.matches();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws TelefoneException {
        if(! isTelefoneValido(telefone))
            throw new TelefoneException();

        this.telefone = telefone;
    }
}
