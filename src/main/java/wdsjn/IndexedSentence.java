package wdsjn;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by grzegorz.miejski on 18/01/16.
 */
public class IndexedSentence {
    private final Sentence sentence;
    private final List<Integer> indexes;

    public IndexedSentence(Sentence sentence, List<Integer> indexes) {
        this.sentence = sentence;
        this.indexes = indexes;
    }


    public Sentence getSentence() {
        return sentence;
    }

    public List<Integer> getIndexes() {
        return indexes;
    }

    public Sentence takeWordsBetween(int left, int right) {
        return new Sentence(sentence.getWords().subList(left , right));
    }
}
