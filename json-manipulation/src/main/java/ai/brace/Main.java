package ai.brace;

import ai.brace.entity.Poem;
import ai.brace.entity.PoemText;
import ai.brace.serde.PoemObjectMapper;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final Poem poem1 = PoemObjectMapper.readJson("a1.json");
        final Poem poem2 = PoemObjectMapper.readJson("a2.json");
        problemOne(poem1);
        problemTwo(poem1, poem2);
        problemThree(poem1, poem2);
        problemFour(poem1, poem2);
    }

    public static void problemOne(final Poem poem) {
        final List<PoemText> poemTexts = poem.getTextArray();
        // Sort the poems by ID (default is ascending)
        poemTexts.sort(Comparator.comparing(PoemText::getId));
        System.out.println("Problem 1:");
        for (final PoemText poemText : poemTexts) {
            System.out.println(poemText.getTextdata());
        }
    }

    public static void problemTwo(final Poem poem1, final Poem poem2) {
        // Concatenate the two collections of poem text then sort by ID
        final List<PoemText> poemTexts = Stream.concat(poem1.getTextArray().stream(), poem2.getTextArray().stream())
                .sorted(Comparator.comparing(PoemText::getId))
                .collect(Collectors.toList());
        System.out.println("Problem 2:");
        for (final PoemText poemText : poemTexts) {
            System.out.println(poemText.getTextdata());
        }
    }

    public static void problemThree(final Poem poem1, final Poem poem2) {
        // Concatenate the two collections then join them into a single string of poem text
        final String poemText = Stream.concat(poem1.getTextArray().stream(), poem2.getTextArray().stream())
                .map(PoemText::getTextdata)
                .collect(Collectors.joining())
                .toLowerCase();
        final Map<String, Long> wordFrequencies = Stream.of(poemText.split(" "))
                // Filter non-alphabetic 'words'
                .filter(w -> w.chars().allMatch(Character::isLetter))
                // Counts number of appearances for each 'word'
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Problem 3:");
        wordFrequencies.forEach((k, v) -> System.out.println(String.format("(%s) : %d", k, v)));
    }

    public static void problemFour(final Poem poem1, final Poem poem2) {
        final List<PoemText> poemTexts = Stream.concat(poem1.getTextArray().stream(), poem2.getTextArray().stream())
                .sorted(Comparator.comparing(PoemText::getId))
                .collect(Collectors.toList());
        final Poem poem = Poem.newBuilder()
                .withVersion(poem1.getVersion())
                .withTranslator(poem1.getTranslator())
                .withAuthor(poem1.getAuthor())
                .withTitle(poem1.getTitle())
                .withLanguage(poem1.getLanguage())
                // Release date is optional
                .withReleaseDate(poem1.getReleaseDate().orElse(poem2.getReleaseDate().orElse(null)))
                .withTextArray(poemTexts)
                .withLastModified(poem1.getLastModified())
                .build();
        PoemObjectMapper.writeJson("output.json", poem);
    }

}
