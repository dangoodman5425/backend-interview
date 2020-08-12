package ai.brace.entity;

import java.util.Objects;

/**
 * Entity representing a line of text in a poem
 *
 * @author dgoodman
 */
public class PoemText {
    private final int id;
    private final String textdata;

    public PoemText(final Builder builder) {
        this.id = builder.id;
        this.textdata = builder.textdata;
    }

    public int getId() {
        return id;
    }

    public String getTextdata() {
        return textdata;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoemText poemText = (PoemText) o;
        return id == poemText.id &&
                Objects.equals(textdata, poemText.textdata);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, textdata);
    }

    public static final class Builder {
        private Integer id;
        private String textdata;

        private Builder() {
        }

        public Builder withId(final int id) {
            this.id = id;
            return this;
        }

        public Builder withTextdata(final String textdata) {
            this.textdata = textdata;
            return this;
        }

        public PoemText build() {
            Objects.requireNonNull(id, "Null id");
            Objects.requireNonNull(textdata, "Null text data");
            return new PoemText(this);
        }
    }
}
