package Persistencias;

import Investimentos.RendaFixa;
import Usuarios.Cliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaRendaFixa {
    private static final String ARQUIVO_JSON = "rendaFixa.json";

    public static void salvarRendaFixa(List<RendaFixa> rendaFixas) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) {
            gson.toJson(rendaFixas, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar a lista de renda fixa: " + e.getMessage());
        }
    }

    public static List<RendaFixa> carregarRendaFixa() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(ARQUIVO_JSON)) {
            return gson.fromJson(reader, new TypeToken<List<RendaFixa>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando novo arquivo...");
        } catch (IOException e) {
            System.err.println("Erro ao carregar a lista de renda fixa: " + e.getMessage());
        }

        return new ArrayList<>();
    }
}