package Investimentos;

import java.util.Date;

public class RendaFixa {
    Date resgateMin;
    Date resgateMax;
    double rendimento;

    RendaFixa(Date resgateMin, Date resgateMax, double rendimento){

        //resgate min deve ser menor que resgate maximo
        //resgate max deve ser resgatado automaticamente na data

        this.resgateMin = resgateMin;
        this.resgateMax = resgateMax;
        this.rendimento = rendimento;
    }
}
