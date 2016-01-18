package wdsjn;

import morfologik.stemming.PolishStemmer;
import morfologik.stemming.WordData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by grzegorz.miejski on 18/01/16.
 */
public class SnippetExtractor {

    private final PolishStemmer polishStemmer;

    public SnippetExtractor() {
        polishStemmer = new PolishStemmer();
    }

    public Stream<Sentence> extract(IndexedSentence indexedSentence) {
        Sentence sentence = indexedSentence.getSentence();
//        String mainWord = sentence.wordAt(indexedSentence.getIndexes().get(0));
        List<Sentence> snippets = new ArrayList<>();

        for (int index : indexedSentence.getIndexes()) {
            try {
                int left = getLeftPart(sentence, index);
                int right = getRightPart(sentence, index);
                Sentence snippet = indexedSentence.takeWordsBetween(left, right);
                System.out.println(snippet.getSentence());
                System.out.println(snippet.wordAt(0));
                System.out.println(snippet.wordAt(snippet.length()-1));
                Sentence basicFormSnippet = new Sentence(snippet.getWords().stream().map(this::toBasicForm).collect(Collectors.toList()));
                snippets.add(basicFormSnippet);
            } catch (Exception e) {
                System.out.println("error");
            }

        }
        return snippets.stream();
    }

    private String toBasicForm(String word) {
        return polishStemmer.lookup(word).get(0).getStem().toString();
    }

    private int getLeftPart(Sentence sentence, int i) {
        for (; i >= 0; --i) {
            String word = sentence.wordAt(i);
            if (isInfinite(word))
                continue;
            if (isVerb(word)) {
                return i;
            }
        }

        return 0;
    }

    private int getRightPart(Sentence sentence, int i) {
        for (; i < sentence.length(); ++i) {
            String word = sentence.wordAt(i);
            if (isInfinite(word))
                continue;
            if (isVerb(word) || word.equals("być")) {
                return i;
            }
        }
        return i;
    }


    private boolean isVerb(String word) {
        return tags(word)
                .anyMatch(t -> t.startsWith("verb"));
    }

    private boolean isInfinite(String word) {
        return tags(word)
                .anyMatch(t -> t.startsWith("inf"));
    }

    private Stream<String> tags(String word) {
        return polishStemmer.lookup(word).stream()
                .map(WordData::getTag)
                .map(CharSequence::toString);
    }

}
