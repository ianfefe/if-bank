//Nome: Ian Felix Fernandes Matrícula: 202376007

package Usuario;

import Persistencias.PersistenciaUsuarios;
import TiposAtributos.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private static List<Usuario> usuarios = new ArrayList<>();

    public static List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public static void salvaUsuarios() {
        PersistenciaUsuarios.salvarUsuarios(usuarios);
    }

    public static void carregarUsuarios() {
        usuarios = PersistenciaUsuarios.carregarUsuarios();
    }

    public static Usuario logarUsuario(String usuario, String senha) {
        if(usuario.matches("\\d+[CG]")){
            for(Usuario usuarioDaLista : usuarios){
                if(usuarioDaLista.getUserID().equals(usuario) && usuarioDaLista.comparaSenha(senha)){
                    return usuarioDaLista;
                }
            }
        }else{
            for(Usuario usuarioDaLista : usuarios){
                if(usuarioDaLista.getCpfString().equals(usuario) && usuarioDaLista.comparaSenha(senha) && usuarioDaLista.getTipo().equals("Cliente")){
                    return usuarioDaLista;
                }
            }
        }
        JOptionPane.showMessageDialog(null,"Usuário não cadastrado.");
        throw new RuntimeException("Não foi possível logar usuário.");
    }

    public static void criarUsuario(Nome nome, DataDeNascimento dataNascimento, CPF cpf, Endereco endereco, Telefone telefone, Email email, String senha, String tipoUsuario) {

        switch (tipoUsuario) {
            case "Gerente":
                usuarios.add(new Gerente(nome, dataNascimento, cpf, endereco, telefone, email, senha));
                break;
            case "Caixa":
                usuarios.add(new Caixa(nome, dataNascimento, cpf, endereco, telefone, email, senha));
                break;
            case "Cliente":
                usuarios.add(new Cliente(nome, dataNascimento, cpf, endereco, telefone, email, senha));
                break;
            default:
                System.err.println("Tipo de usuário desconhecido.");
                break;
        }
        PersistenciaUsuarios.salvarUsuarios(usuarios);
        carregarUsuarios();
    }

    public static void removerUsuario(Usuario usuario) {
        usuarios.remove(usuario);
        salvaUsuarios();
    }

    public static void editarUsuario(Usuario usuario,
                                     Nome nome,
                                     Endereco endereco,
                                     Telefone telefone,
                                     Email email,
                                     String senha) {
        usuario.editaUsuario(nome, endereco, telefone, email, senha);
        salvaUsuarios();
    }
}