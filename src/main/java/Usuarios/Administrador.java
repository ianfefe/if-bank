//Nome: Ian Felix Fernandes Matrícula: 202376007

package Usuarios;

public interface Administrador {
    void realizarSaque(Cliente origem, double valor);

    void realizarDeposito(Cliente destino, double valor);
}
