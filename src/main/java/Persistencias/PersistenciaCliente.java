package Persistencias;

import Usuarios.Cliente;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaCliente {
    private static final String ARQUIVO_JSON = "clientes.json";

    public static void salvarClientes(List<Cliente> clientes) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();;

        try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) {
            gson.toJson(clientes, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar a lista de clientes: " + e.getMessage());
        }
    }

    public static List<Cliente> carregarClientes() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(ARQUIVO_JSON)) {
            return gson.fromJson(reader, new TypeToken<List<Cliente>>() {}.getType());
        }
        catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando novo arquivo...");
        }
        catch (IOException e){
            System.err.println("Erro ao carregar a lista de clientes: " + e.getMessage());
        }

        return new ArrayList<>();
    }
}