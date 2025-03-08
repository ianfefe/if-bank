//Nome: Ian Felix Fernandes Matrícula: 202376007

package Persistencias;

import Usuario.Caixa;
import Usuario.Cliente;
import Usuario.Gerente;
import Usuario.Usuario;
import com.google.gson.*;

import java.lang.reflect.Type;

public class AdaptadorDeUsuario implements JsonDeserializer<Usuario>, JsonSerializer<Usuario> {

        @Override
        public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();

            if (!jsonObject.has("tipo")) {
                throw new JsonParseException("Campo 'tipo' ausente no JSON.");
            }

            String tipo = jsonObject.get("tipo").getAsString();

            switch (tipo) {
                case "Caixa":
                    return context.deserialize(jsonObject, Caixa.class);
                case "Gerente":
                    return context.deserialize(jsonObject, Gerente.class);
                case "Cliente":
                    return context.deserialize(jsonObject, Cliente.class);
                default:
                    throw new JsonParseException("Tipo de usuário desconhecido: " + tipo);
            }
        }

        @Override
        public JsonElement serialize(Usuario src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject;

            if (src instanceof Caixa) {
                jsonObject = context.serialize(src, Caixa.class).getAsJsonObject();
                jsonObject.addProperty("tipo", "Caixa");
            } else if (src instanceof Gerente) {
                jsonObject = context.serialize(src, Gerente.class).getAsJsonObject();
                jsonObject.addProperty("tipo", "Gerente");
            } else if (src instanceof Cliente) {
                jsonObject = context.serialize(src, Cliente.class).getAsJsonObject();
                jsonObject.addProperty("tipo", "Cliente");
            } else {
                throw new JsonParseException("Tipo de usuário desconhecido");
            }

            return jsonObject;
        }
}
