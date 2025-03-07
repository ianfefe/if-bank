package Investimentos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Data {

    public String dataToString(LocalDate dataDate) {
        DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = dataDate.format(formataData);

        return dataString;
    }

    public LocalDate dataToDate(String dataString) {
        DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataString, formataData);

        return data;
    }

}
