package wdsjn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by grzegorz.miejski on 18/01/16.
 */
public class Sentence {

    private final List<String> words;
    private String sentence;

    public Sentence(String sentence) {
        this.sentence = sentence;
        this.words = Arrays.asList(this.sentence.split(" "));
    }

    public Sentence(List<String> words) {
        this.words = words;
        this.sentence = words.stream().collect(Collectors.joining(" "));
    }


    public List<String> getWords() {
        return words;
    }

    public String getSentence() {
        return sentence;
    }

    public String wordAt(Integer integer) {
        return words.get(integer);
    }

    public int length() {
        return words.size();
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "sentence='" + sentence + '\'' +
                '}';
    }
}
