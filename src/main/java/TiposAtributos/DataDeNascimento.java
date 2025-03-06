package TiposAtributos;

import Exceptions.DataNascimentoException;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataDeNascimento {

    private String dataDeNascimento;

    public DataDeNascimento(String dataDeNascimentoString) {
        if (validaMaioridade(dataDeNascimentoString)) {
            this.dataDeNascimento = dataDeNascimentoString;
        }
    }

    public String getDataDeNascimentoString() {
        return dataDeNascimento;
    }

    public boolean validaMaioridade(String dataDeNascimentoString) {
        try {
            DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataNascimento = LocalDate.parse(dataDeNascimentoString, formataData);

            LocalDate hoje = LocalDate.now();
            int idade = Period.between(dataNascimento, hoje).getYears();

            if (idade >= 18) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "É necessário ser maior de idade para se cadastrar.");
                throw new DataNascimentoException("É necessário ser maior de idade para se cadastrar.");
            }

        } catch (DateTimeParseException e) {
            throw new DataNascimentoException();
        }
    }
}
