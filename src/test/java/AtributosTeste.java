import Exceptions.*;
import TiposAtributos.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AtributosTeste {

    @Test
    public void CPFtesteInvalido(){

        CPFException cpfInvalido = assertThrows(
                CPFException.class,
                () -> new CPF("12345678910", "Cliente")
        );

        assertEquals("CPF inválido.", cpfInvalido.getMessage());
    }

    @Test
    public void EmailTesteInvalido(){

        EmailException emailInvalido = assertThrows(
                EmailException.class,
                () -> new Email("asdas")
        );

        assertEquals("Email inválido.", emailInvalido.getMessage());
    }

    @Test
    public void EnderecoTesteInvalidoRua(){

        EnderecoException enderecoInvalido = assertThrows(
                EnderecoException.class,
                () -> new Endereco("123","123","Casa")
        );

        assertEquals("Endereço inválido.", enderecoInvalido.getMessage());
    }

    @Test
    public void EnderecoTesteInvalidoNumero(){

        EnderecoException enderecoInvalido = assertThrows(
                EnderecoException.class,
                () -> new Endereco("Rua Abc","abc","Casa")
        );

        assertEquals("Endereço inválido.", enderecoInvalido.getMessage());
    }

    @Test
    public void EnderecoTesteInvalidoComplemento(){

        EnderecoException enderecoInvalido = assertThrows(
                EnderecoException.class,
                () -> new Endereco("Rua Abc","123","xxx")
        );

        assertEquals("Complemento inexistente.", enderecoInvalido.getMessage());
    }

    @Test
    public void TelefoneTesteInvalido(){

        TelefoneException telefoneInvalido = assertThrows(
                TelefoneException.class,
                () -> new Telefone("1233")
        );

        assertEquals("Número de telefone inválido.", telefoneInvalido.getMessage());
    }

    @Test
    public void DataDeNascimentoTesteInvalido(){

        DataNascimentoException dataNascimentoInvalida = assertThrows(
                DataNascimentoException.class,
                () -> new DataDeNascimento("123")
        );

        assertEquals("Data de nascimento inválida.", dataNascimentoInvalida.getMessage());
    }
}
