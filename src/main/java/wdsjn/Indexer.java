package wdsjn;

import org.jooq.lambda.Seq;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grzegorz.miejski on 18/01/16.
 */
public class Indexer {
    private String mainWord;

    public Indexer(String mainWord) {
        this.mainWord = mainWord;
    }

    public IndexedSentence toIndexedSentence(Sentence sentence) {

        List<Integer> indexes = new ArrayList<>();
        int i = 0;
        for (String word : sentence.getWords()) {
            if (word.equals(mainWord)) {
                indexes.add(i);
            }
            i++;
        }
        return new IndexedSentence(sentence, indexes);
    }
}
