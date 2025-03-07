//Nome: Ian Felix Fernandes Matrícula: 202376007

package TiposAtributos;

import Exceptions.CPFException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPF {
    private String cpf;

    public CPF(String cpf, String tipoUsuario) {
        setCPF(cpf, tipoUsuario);
    }

    private boolean isCPFValido(String cpf) {
        String cpfRegex = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}$";
        Pattern pattern = Pattern.compile(cpfRegex);
        Matcher matcher = pattern.matcher(cpf);
        return matcher.matches();
    }

    public String getCPF() {
        return cpf;
    }

    public void setCPF(String cpf, String tipoUsuario) {
        if (!isCPFValido(cpf))
            throw new CPFException();

        this.cpf = cpf;
    }
}
