package Usuarios;

import Frames.LoginFrame;
import Persistencias.PersistenciaAdm;
import Persistencias.PersistenciaCliente;
import TiposAtributos.CPF;
import TiposAtributos.Email;
import TiposAtributos.Endereco;
import TiposAtributos.Telefone;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Usuario> administradores = new ArrayList<>();

    public static List<Usuario> getAdministradores(){
        return administradores;
    }

    public static List<Cliente> getClientes(){
        return clientes;
    }

    public static void salvaUsuarios(){
        PersistenciaAdm.salvarAdms(administradores);
        PersistenciaCliente.salvarClientes(clientes);
    }

    public static void carregarUsuarios() {
        administradores = PersistenciaAdm.carregarAdms();
        clientes = PersistenciaCliente.carregarClientes();
    }

    public static Cliente logarCliente(String cpf, String senha){
        for (Cliente cliente : Sistema.clientes) {
            if(cliente.getCpfString().equals(cpf) && cliente.verificaSenha(senha))
                return cliente;
        }
        return null;
    }

    public static Usuario logarAdm(String userId, String senha){
        for (Usuario adm : Sistema.administradores) {
            if(adm.getUserID().equals(userId) && adm.verificaSenha(senha))
                return adm;
        }
        return null;
    }

    public static void criarUsuario(String nome, String dataNascimento, CPF cpf, Endereco endereco, Telefone telefone, Email email, String senha, String tipoUsuario){

        switch (tipoUsuario){
            case "Gerente":
                administradores.add(new Gerente( nome,dataNascimento,cpf, endereco,  telefone,  email,  senha));
                PersistenciaAdm.salvarAdms(administradores);
                break;
            case "Caixa":
                administradores.add(new Caixa( nome,dataNascimento,cpf, endereco,  telefone,  email,  senha));
                PersistenciaAdm.salvarAdms(administradores);
                break;
            case "Cliente":
                clientes.add( new Cliente( nome,dataNascimento,cpf, endereco,  telefone,  email,  senha));
                PersistenciaCliente.salvarClientes(clientes);
                break;
            default:
                System.err.println("Tipo de usuário desconhecido.");
                break;
        }

    };

    void removerUsuario(Cliente usuario){
        clientes.remove(usuario);
    };

    void editarUsuario(Usuario usuario){

    };

    public static void main(String[] args) {

        carregarUsuarios();

        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
