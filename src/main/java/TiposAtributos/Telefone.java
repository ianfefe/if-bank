package TiposAtributos;

import Exceptions.TelefoneException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Telefone {

    private String telefone;

    public Telefone(String telefone) throws TelefoneException {
        setTelefone(telefone);
    }

    private boolean isTelefoneValido(String telefone) {
        String telefoneRegex = "^\\([0-9]{2}\\)[0-9]{4}-[0-9]{4}$";

        Pattern pattern = Pattern.compile(telefoneRegex);
        Matcher matcher = pattern.matcher(telefone);
        return matcher.matches();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) throws TelefoneException {
        if (!isTelefoneValido(telefone))
            throw new TelefoneException();

        this.telefone = telefone;
    }
}
