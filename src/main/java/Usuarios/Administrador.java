package Usuarios;

public interface Administrador {
    public void realizarSaque(Cliente origem,double valor);

    public void realizarDeposito(Cliente destino, double valor);
}
