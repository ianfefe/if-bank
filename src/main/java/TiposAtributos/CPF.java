package TiposAtributos;

import Exceptions.EmailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPF {
    private String cpf;

    public CPF(String cpf) throws EmailException {
        setCPF(cpf);
    }

    private boolean isCPFValido(String email) {
        String cpfRegex = "^[0-9]{11}$";
        Pattern pattern = Pattern.compile(cpfRegex);
        Matcher matcher = pattern.matcher(cpf);
        return matcher.matches();
    }

    public String getCPF() {
            return cpf;
    }

    public void setCPF(String cpf) throws EmailException {
        if(! isCPFValido(cpf))
            throw new EmailException();

        this.cpf = cpf;
    }
}
