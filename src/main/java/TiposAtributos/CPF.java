package TiposAtributos;

import Exceptions.CPFException;
import Usuarios.Cliente;
import Persistencias.Sistema;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPF {
    private String cpf;

    public CPF(String cpf) throws CPFException {
        setCPF(cpf);
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

    public void setCPF(String cpf) throws CPFException {
        if (!isCPFValido(cpf))
            throw new CPFException();

        for (Cliente cliente : Sistema.getClientes()) {
            if (cliente.getCpfString().equals(cpf)) {
                throw new CPFException("CPF já cadastrado");
            }
        }

        this.cpf = cpf;
    }
}
