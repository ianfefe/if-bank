package Persistencias;

import Frames.LoginFrame;
import TiposAtributos.*;
import Usuarios.Caixa;
import Usuarios.Cliente;
import Usuarios.Gerente;
import Usuarios.Usuario;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Caixa> caixas = new ArrayList<>();
    private static List<Gerente> gerentes = new ArrayList<>();

    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static void salvaUsuarios() {
        PersistenciaCaixa.salvarAdms(caixas);
        PersistenciaGerente.salvarAdms(gerentes);
        PersistenciaCliente.salvarClientes(clientes);
    }

    public static void carregarUsuarios() {
        caixas = PersistenciaCaixa.carregarAdms();
        gerentes = PersistenciaGerente.carregarAdms();
        clientes = PersistenciaCliente.carregarClientes();
    }

    public static Cliente logarCliente(String cpf, String senha) {
        for (Cliente cliente : Sistema.clientes) {
            if (cliente.getCpfString().equals(cpf) && cliente.verificaSenha(senha))
                return cliente;
        }
        return null;
    }

    public static Object logarAdm(String userId, String senha) {
        if (userId.matches("^[0-9]+C$")) {
            for (Caixa adm : Sistema.caixas) {
                if (adm.getUserID().equals(userId) && adm.verificaSenha(senha))
                    return adm;
            }
        } else if(userId.matches("^[0-9]+G$")){
            for (Gerente adm : Sistema.gerentes) {
                if (adm.getUserID().equals(userId) && adm.verificaSenha(senha))
                    return adm;
            }
        }
        return null;
    }

    public static void criarUsuario(String nome, DataDeNascimento dataNascimento, CPF cpf, Endereco endereco, Telefone telefone, Email email, String senha, String tipoUsuario) {

        switch (tipoUsuario) {
            case "Gerente":
                gerentes.add(new Gerente(nome, dataNascimento, cpf, endereco, telefone, email, senha));
                PersistenciaGerente.salvarAdms(gerentes);
                break;
            case "Caixa":
                caixas.add(new Caixa(nome, dataNascimento, cpf, endereco, telefone, email, senha));
                PersistenciaCaixa.salvarAdms(caixas);
                break;
            case "Cliente":
                clientes.add(new Cliente(nome, dataNascimento, cpf, endereco, telefone, email, senha));
                PersistenciaCliente.salvarClientes(clientes);
                break;
            default:
                System.err.println("Tipo de usuário desconhecido.");
                break;
        }

    }

    ;

    public static void main(String[] args) {

        carregarUsuarios();

        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }

    ;

    void removerUsuario(Cliente usuario) {
        clientes.remove(usuario);
    }

    ;

    void editarUsuario(Usuario usuario) {

    }
}
