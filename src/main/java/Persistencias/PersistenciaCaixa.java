package Persistencias;

import Usuarios.Caixa;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaCaixa {
    private static final String ARQUIVO_JSON = "caixas.json";

    public static void salvarAdms(List<Caixa> admins) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) {
            gson.toJson(admins, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar a lista de administradores: " + e.getMessage());
        }
    }

    public static List<Caixa> carregarAdms() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(ARQUIVO_JSON)) {
            return gson.fromJson(reader, new TypeToken<List<Caixa>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando novo arquivo...");
        } catch (IOException e) {
            System.err.println("Erro ao carregar a lista de administradores: " + e.getMessage());
        }

        return new ArrayList<>();
    }
}