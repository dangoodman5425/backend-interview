package ai.brace.serde;

import ai.brace.entity.Poem;
import ai.brace.entity.PoemText;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * {@link JsonSerializer} used for the serialization of {@link Poem}
 *
 * @author dgoodman
 */
public class PoemSerializer implements JsonSerializer<Poem> {

    @Override
    public JsonElement serialize(final Poem src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject json = new JsonObject();
        json.addProperty("version", src.getVersion());
        json.addProperty("uuid", src.getUuid().toString());
        json.addProperty("lastModified", DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochSecond(
                src.getLastModified())));
        json.addProperty("title", src.getTitle());
        json.addProperty("author", src.getAuthor());
        json.addProperty("translator", src.getTranslator());
        if (src.getReleaseDate().isPresent()) {
            json.addProperty("releaseDate", src.getReleaseDate().get());
        }
        json.addProperty("language", src.getLanguage());
        final TypeToken<List<PoemText>> typeDescription = new TypeToken<>() {};
        final JsonElement members = context.serialize(src.getTextArray(), typeDescription.getType());
        json.add("textArray", members);
        return json;
    }
}
