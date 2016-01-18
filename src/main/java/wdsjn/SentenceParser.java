package wdsjn;

import morfologik.stemming.PolishStemmer;
import morfologik.stemming.WordData;
import wdsjn.marek.Word;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by grzegorz.miejski on 18/01/16.
 */
public class SentenceParser {

    public static final String SENTENCE_ENDINGS = "[\\.?!]";
    private final PolishStemmer polishStemmer;
    private final LinkedHashMap<String, String> charMappings = new LinkedHashMap<>();

    public SentenceParser() {
        charMappings.put("[0-9]+(r(\\.)?)?", " ");
        charMappings.put("[#,…—:\\\"»*„;' \\-\n\t\\(\\)]", " ");
        charMappings.put(SENTENCE_ENDINGS, " ");
        charMappings.put(" +", " ");
        polishStemmer = new PolishStemmer();
    }

    public Sentence parse(String str) {
        str = str.toLowerCase();
        for (Map.Entry<String, String> entry : charMappings.entrySet()) {
            String from = entry.getKey();
            String to = entry.getValue();
            str = str.replaceAll(from, to);
        }

        String[] words = str.split(" ");
//        List<Word> collect = Arrays.stream(words).map(this::toWordForm).collect(Collectors.toList());
        return new Sentence(str);
    }

    private Word toWordForm(String s) {
        List<List<WordData>> collect = Stream.of(polishStemmer.lookup(s)).collect(Collectors.toList());
        return new Word(s, null);
    }

    public static void main(String[] args) {
        SentenceParser preProcessor = new SentenceParser();
        System.out.println(preProcessor.parse("głowy-kupił"));
    }

}
