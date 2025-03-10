import Investimentos.RendaFixa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RendaFixaTeste {

    @Test
    public void testaResgateMinimoData(){

        RuntimeException saldoInvalido = assertThrows(
                RuntimeException.class,
                () -> new RendaFixa("Renda resgate invalido",30,29,10)
        );

        assertEquals("Data de resgate mínimo superior à limite.", saldoInvalido.getMessage());

    }

    @Test
    public void testaRendimentoBaixo(){

        RuntimeException saldoInvalido = assertThrows(
                RuntimeException.class,
                () -> new RendaFixa("Renda rendimento 0",30,60,0)
        );

        assertEquals("Investimento com rendimento abaixo de zero", saldoInvalido.getMessage());

    }
}
