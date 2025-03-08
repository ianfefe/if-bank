//Nome: Ian Felix Fernandes Matrícula: 202376007

package Persistencias;

import Usuario.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaUsuarios {
    private static final String ARQUIVO_JSON = "usuarios.json";

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Usuario.class, new AdaptadorDeUsuario())
            .setPrettyPrinting()
            .create();

    public static void salvarUsuarios(List<? extends Usuario> usuarios) {

        try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar a lista de usuários: " + e.getMessage());
        }
    }

    public static List<Usuario> carregarUsuarios() {
        try (FileReader reader = new FileReader(ARQUIVO_JSON)) {
            return gson.fromJson(reader, new TypeToken<List<Usuario>>() {}.getType());
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado (" + ARQUIVO_JSON + "). Criando novo arquivo...");
        } catch (IOException e) {
            System.err.println("Erro ao carregar a lista de usuários: " + e.getMessage());
        }

        return new ArrayList<>();
    }
}