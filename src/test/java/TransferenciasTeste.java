import TiposAtributos.*;
import Usuarios.Cliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransferenciasTeste {

    @Test
    public void testaTransferenciaSaldoInsuficiente(){
        Cliente clienteTeste = new Cliente("ian", new DataDeNascimento("02/08/2000"),new CPF("123.123.123-12"),new Endereco("Rua Marilia","123","Casa"),new Telefone("(32)3222-2227"),new Email("ianfe@gasd.com"),"123senha");
        Cliente clienteTeste2 = new Cliente("iandois", new DataDeNascimento("02/08/2000"),new CPF("321.321.321-32"),new Endereco("Rua Marilia","123","Casa"),new Telefone("(32)3222-2227"),new Email("ianfe@gasd.com"),"123senha");

        RuntimeException saldoInvalido = assertThrows(
                RuntimeException.class,
                () -> clienteTeste.transferir(clienteTeste,clienteTeste2,400.00)
        );

        assertEquals("Saldo insuficiente.", saldoInvalido.getMessage());

    }

    @Test
    public void testaTransferenciaContaOrigemInexistente(){
        Cliente clienteTeste = new Cliente("ian", new DataDeNascimento("02/08/2000"),new CPF("123.123.123-12"),new Endereco("Rua Marilia","123","Casa"),new Telefone("(32)3222-2227"),new Email("ianfe@gasd.com"),"123senha");
        Cliente clienteTeste2 = new Cliente("iandois", new DataDeNascimento("02/08/2000"),new CPF("321.321.321-32"),new Endereco("Rua Marilia","123","Casa"),new Telefone("(32)3222-2227"),new Email("ianfe@gasd.com"),"123senha");

        RuntimeException contaOrigemInvalida = assertThrows(
                RuntimeException.class,
                () -> clienteTeste.transferir(null,clienteTeste2,400.00)
        );

        assertEquals("Conta de origem não encontrada.", contaOrigemInvalida.getMessage());

    }

    @Test
    public void testaTransferenciaContasDestinoInexistentes(){
        Cliente clienteTeste = new Cliente("ian", new DataDeNascimento("02/08/2000"),new CPF("123.123.123-12"),new Endereco("Rua Marilia","123","Casa"),new Telefone("(32)3222-2227"),new Email("ianfe@gasd.com"),"123senha");

        RuntimeException contaDestinoInvalida = assertThrows(
                RuntimeException.class,
                () -> clienteTeste.transferir(clienteTeste,null,400.00)
        );

        assertEquals("Conta destino não encontrada.", contaDestinoInvalida.getMessage());

    }
}
