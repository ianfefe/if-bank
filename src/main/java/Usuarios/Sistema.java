//Nome: Ian Felix Fernandes Matrícula: 202376007

package Usuarios;

import Persistencias.PersistenciaCaixa;
import Persistencias.PersistenciaCliente;
import Persistencias.PersistenciaGerente;
import TiposAtributos.*;

import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Caixa> caixas = new ArrayList<>();
    private static List<Gerente> gerentes = new ArrayList<>();

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }

    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static List<Caixa> getCaixas() {
        return caixas;
    }

    public static List<Gerente> getGerentes() {
        return gerentes;
    }

    private static void listaUsuarios() {
        usuarios.clear();
        for (Cliente usuario : Sistema.getClientes()) {
            if (usuario != null)
                usuarios.add(usuario);
            else
                break;
        }
        for (Gerente usuario : Sistema.getGerentes()) {
            if (usuario != null)
                usuarios.add(usuario);
            else
                break;
        }
        for (Caixa usuario : Sistema.getCaixas()) {
            if (usuario != null)
                usuarios.add(usuario);
            else
                break;
        }
    }

    public static void salvaUsuarios() {
        PersistenciaCaixa.salvarAdms(caixas);
        PersistenciaGerente.salvarAdms(gerentes);
        PersistenciaCliente.salvarClientes(clientes);
    }

    public static void carregarUsuarios() {
        gerentes = PersistenciaGerente.carregarAdms();
        caixas = PersistenciaCaixa.carregarAdms();
        clientes = PersistenciaCliente.carregarClientes();
        listaUsuarios();
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
        } else if (userId.matches("^[0-9]+G$")) {
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
        carregarUsuarios();
    }

    public static void removerUsuario(Usuario usuario) {
        String tipoUsuario = usuario.getTipoUsuario();
        switch (tipoUsuario) {
            case "Cliente":
                clientes.removeIf(cliente -> cliente.getCpf().equals(usuario.getCpf()));
                break;
            case "Caixa":
                caixas.removeIf(caixa -> caixa.getCpf().equals(usuario.getCpf()));
                break;
            case "Gerente":
                gerentes.removeIf(gerente -> gerente.getCpf().equals(usuario.getCpf()));
                break;
            default:
                throw new RuntimeException("Usuário não encontrado para remoção.");
        }
        usuarios.remove(usuario);
        salvaUsuarios();
    }

    public static void editarUsuario(Usuario usuario,
                                     String nome,
                                     Endereco endereco,
                                     Telefone telefone,
                                     Email email,
                                     String senha) {
        usuario.editaUsuario(nome, endereco, telefone, email, senha);
        salvaUsuarios();
    }
}