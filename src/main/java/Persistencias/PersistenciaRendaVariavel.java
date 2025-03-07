//Nome: Ian Felix Fernandes Matrícula: 202376007

package Persistencias;

import Investimentos.RendaVariavel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaRendaVariavel {
    private static final String ARQUIVO_JSON = "rendaVariavel.json";

    public static void salvarRendaVariavel(List<RendaVariavel> rendaVariavel) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) {
            gson.toJson(rendaVariavel, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar a lista de renda variavel: " + e.getMessage());
        }
    }

    public static List<RendaVariavel> carregarVariavel() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(ARQUIVO_JSON)) {
            return gson.fromJson(reader, new TypeToken<List<RendaVariavel>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando novo arquivo...");
        } catch (IOException e) {
            System.err.println("Erro ao carregar a lista de renda variavel: " + e.getMessage());
        }

        return new ArrayList<>();
    }
}