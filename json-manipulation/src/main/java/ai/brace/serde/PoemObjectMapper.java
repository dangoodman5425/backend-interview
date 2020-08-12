package ai.brace.serde;

import ai.brace.entity.Poem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * Object mapper responsible for the serde of {@link Poem} entities
 *
 * NOTE: There appears to be an extra space in your expectedOutput.json at the beginning of sentences. I am not sure
 * why this is but I cannot replicate this (potentially could be a bug with the expectedOutput.json)
 *
 * @author dgoodman
 */
public class PoemObjectMapper {
    private static final Logger LOGGER = Logger.getLogger(PoemObjectMapper.class.getName());

    private PoemObjectMapper() {

    }

    /**
     * Deserializes a JSON file at the given path into a {@link Poem} object if able
     *
     * @param path to read the JSON
     * @return {@link Poem} deserialization of the JSON
     */
    public static Poem readJson(final String path)  {
        try (final Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(path).toURI()))) {
            final Gson gson = new Gson();
            return gson.fromJson(reader, Poem.class);
        } catch (final IOException | URISyntaxException e) {
            LOGGER.info(String.format("Unable to process JSON: %s", path));
            return null;
        }
    }

    /**
     * Serializes a {@link Poem} into a JSON file at the given path
     *
     * @param path to write the JSON
     * @param poem {@link Poem} to serialize
     */
    public static void writeJson(final String path, final Poem poem) {
        try (final Writer writer = new FileWriter(path)) {
            final Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(Poem.class, new PoemSerializer())
                    // We have some strange encoding if we don't disable this
                    .disableHtmlEscaping()
                    .create();
            gson.toJson(poem, writer);
            writer.flush();
        } catch (final IOException e) {
            LOGGER.info(String.format("Unable to process JSON: %s", path));
        }
    }
}
