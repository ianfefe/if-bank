import TiposAtributos.Endereco;
import Usuarios.Caixa;
import Usuarios.Cliente;
import Usuarios.Gerente;
import Usuarios.Usuario;

public class Sistema {
    static int id = 0;

    void criarUsuario(String nome, String endereco, String telefone, String email, int senha){
        String tipoUsuario = "CLIENTE";
        switch (tipoUsuario){
            case "CAIXA":
                Caixa id1 = new Caixa(nome, endereco, telefone, email, senha);
                id++;
            case "CLIENTE":
                Cliente id2 = new Cliente(nome, endereco, telefone, email, senha);
                id++;
            case "GERENTE":
                Gerente id3 = new Gerente(nome, endereco, telefone, email, senha);
                id++;
        }
    };

    void removerUsuario(Usuario usuario){
    };

    void editarUsuario(Usuario usuario){

    };
}
