import java.util.Objects;

public class Caixa extends Usuario{

    void transferencia(Cliente origem, Cliente destino, double valor){
        Objects.requireNonNull(destino, "Conta de destino não encontrada.");
        if(origem.getSaldo() <= valor){
            origem.transferir(destino ,valor);
            origem.confirmarSenha();
        }
    }
}
