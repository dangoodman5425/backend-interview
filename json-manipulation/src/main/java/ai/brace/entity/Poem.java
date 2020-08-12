package ai.brace.entity;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Entity representing a poem including text and metadata
 *
 * @author dgoodman
 */
public class Poem {
    private final String version;
    private final UUID uuid;
    private final long lastModified;
    private final String title;
    private final String author;
    private final String translator;
    private final String language;
    /**
     * This field is presumed to be optional as it only appears in one of the JSON
     * (without further knowledge of the schema it's hard to say what else is)
     */
    private final String releaseDate;
    private final List<PoemText> textArray;

    public Poem(final Builder builder) {
        this.version = builder.version;
        this.uuid = builder.uuid;
        this.lastModified = builder.lastModified;
        this.title = builder.title;
        this.author = builder.author;
        this.translator = builder.translator;
        this.language = builder.language;
        this.releaseDate = builder.releaseDate;
        this.textArray = builder.textArray;
    }

    public String getVersion() {
        return version;
    }

    public UUID getUuid() {
        return uuid;
    }

    public long getLastModified() {
        return lastModified;
    }

    public String getTitle() {
        return title;
    }

    public String getTranslator() {
        return translator;
    }

    public String getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    public Optional<String> getReleaseDate() {
        return Optional.ofNullable(releaseDate);
    }

    public List<PoemText> getTextArray() {
        return textArray;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poem poem = (Poem) o;
        return lastModified == poem.lastModified &&
                Objects.equals(version, poem.version) &&
                Objects.equals(uuid, poem.uuid) &&
                Objects.equals(title, poem.title) &&
                Objects.equals(author, poem.author) &&
                Objects.equals(translator, poem.translator) &&
                Objects.equals(language, poem.language) &&
                Objects.equals(releaseDate, poem.releaseDate) &&
                Objects.equals(textArray, poem.textArray);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, uuid, lastModified, title, author, translator, language, releaseDate, textArray);
    }

    public static final class Builder {
        private String version;
        private UUID uuid;
        private Long lastModified;
        private String title;
        private String translator;
        private String author;
        private String language;
        private String releaseDate;
        private List<PoemText> textArray;

        private Builder() {
            uuid = UUID.randomUUID();
            textArray = List.of();
        }

        public Builder withVersion(final String version) {
            this.version = version;
            return this;
        }

        public Builder withUuid(final UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder withLastModified(final long lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        public Builder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public Builder withTranslator(final String translator) {
            this.translator = translator;
            return this;
        }

        public Builder withAuthor(final String author) {
            this.author = author;
            return this;
        }

        public Builder withLanguage(final String language) {
            this.language = language;
            return this;
        }

        public Builder withReleaseDate(final String releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder withTextArray(final Collection<PoemText> textArray) {
            this.textArray = List.copyOf(Objects.requireNonNull(textArray, "Null text array"));
            return this;
        }

        public Poem build() {
            Objects.requireNonNull(version, "Null version");
            Objects.requireNonNull(uuid, "Null uuid");
            Objects.requireNonNull(lastModified, "Null last modified");
            Objects.requireNonNull(title, "Null title");
            Objects.requireNonNull(translator, "Null translator");
            Objects.requireNonNull(author, "Null author");
            Objects.requireNonNull(language, "Null language");
            Objects.requireNonNull(textArray, "Null text array");
            return new Poem(this);
        }
    }
}
