package Usuarios;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    private static final String ARQUIVO_JSON = "pessoas.json";

    public static void salvarUsuarios(List<Usuario> usuarios) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Usuario> carregarUsuarios() {
        Gson gson = new Gson();
        List<Usuario> usuarios = new ArrayList<>();
        try (FileReader reader = new FileReader(ARQUIVO_JSON)) {
            Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();

            List<Usuario> converteUsuario = gson.fromJson(reader, tipoLista);

            for(Usuario usuario : converteUsuario){
                switch (usuario.getTipo()) {
                    case "Gerente":
                        usuarios.add(new Gerente( usuario.getNome(),usuario.getDataNascimento(),usuario.getCpf(), usuario.getEndereco(), usuario.getTelefone(),usuario.getEmail(),usuario.getSenha()));
                        break;
                    case "Caixa":
                        usuarios.add(new Caixa( usuario.getNome(),usuario.getDataNascimento(),usuario.getCpf(), usuario.getEndereco(), usuario.getTelefone(),usuario.getEmail(),usuario.getSenha()));
                        break;
                    default:
                        usuarios.add(new Cliente( usuario.getNome(),usuario.getDataNascimento(),usuario.getCpf(), usuario.getEndereco(), usuario.getTelefone(),usuario.getEmail(),usuario.getSenha()));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando novo arquivo...");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
}