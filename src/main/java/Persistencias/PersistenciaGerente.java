//Nome: Ian Felix Fernandes Matrícula: 202376007

package Persistencias;

import TiposAtributos.*;
import Usuarios.Gerente;
import Usuarios.Sistema;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaGerente {
    private static final String ARQUIVO_JSON = "gerentes.json";

    public static void salvarAdms(List<Gerente> admins) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(ARQUIVO_JSON)) {
            gson.toJson(admins, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar a lista de administradores: " + e.getMessage());
        }
    }

    public static List<Gerente> carregarAdms() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(ARQUIVO_JSON)) {
            return gson.fromJson(reader, new TypeToken<List<Gerente>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado. Criando novo arquivo...");
            Sistema.criarUsuario("Adm", new DataDeNascimento("01/01/2000"), new CPF("111.111.111-11"), new Endereco("abc", "123", "Casa"), new Telefone("(32)3222-2223"), new Email("aaaaa@gmail.com"), "123", "Gerente");
        } catch (IOException e) {
            System.err.println("Erro ao carregar a lista de administradores: " + e.getMessage());
        }

        return new ArrayList<>();
    }
}