package Usuarios;

public interface Administrador {
    void realizarSaque(Cliente origem, double valor);

    void realizarDeposito(Cliente destino, double valor);
}
