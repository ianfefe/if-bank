import TiposAtributos.*;
import Usuarios.Cliente;
import org.junit.jupiter.api.Test;

public class TransferenciasTeste {

    @Test
    public void testaTransferenciaSaldoInvalido(){
        Cliente clienteTeste = new Cliente("ian", new DataDeNascimento("02/08/2000"),new CPF("123.123.123-12"),new Endereco("Rua Marilia","123","Casa"),new Telefone("(32)3222-2227"),new Email("ianfe@gasd.com"),"123senha");
        Cliente clienteTeste2 = new Cliente("iandois", new DataDeNascimento("02/08/2000"),new CPF("321.321.321-32"),new Endereco("Rua Marilia","123","Casa"),new Telefone("(32)3222-2227"),new Email("ianfe@gasd.com"),"123senha");
        clienteTeste.transferir(clienteTeste,clienteTeste2,400.00);


    }
}
