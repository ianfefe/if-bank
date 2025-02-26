package Usuarios;

import Frames.LoginFrame;
import TiposAtributos.CPF;
import TiposAtributos.Email;
import TiposAtributos.Endereco;
import TiposAtributos.Telefone;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private static List<Usuario> usuarios = new ArrayList<>();

    public static List<Usuario> getUsuarios(){
        return usuarios;
    }

    public static void salvaUsuarios(){
        Persistencia.salvarUsuarios(usuarios);
    }

    public static void carregarUsuarios() {
        usuarios = Persistencia.carregarUsuarios();
    }

    public static Usuario logarUsuario(String cpf, String senha){
        for (Usuario usuario : Sistema.usuarios) {
            if(usuario.getCpfString().equals(cpf) && usuario.getSenha().equals(senha))
                return usuario;
            else
                return null;
        }
        return null;
    }

    public static void criarUsuario(String nome, String dataNascimento, CPF cpf, Endereco endereco, Telefone telefone, Email email, String senha){

        String tipoUsuario = "";

        switch (tipoUsuario){
            case "Gerente":
                usuarios.add(new Gerente( nome,dataNascimento,cpf, endereco,  telefone,  email,  senha));
                break;
            case "Caixa":
                usuarios.add(new Caixa( nome,dataNascimento,cpf, endereco,  telefone,  email,  senha));
                break;
            default:
                usuarios.add(new Cliente( nome,dataNascimento,cpf, endereco,  telefone,  email,  senha));
                break;
        }

        Persistencia.salvarUsuarios(usuarios);
    };

    void removerUsuario(Usuario usuario){
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
