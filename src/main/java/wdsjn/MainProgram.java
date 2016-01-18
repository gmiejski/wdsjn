package wdsjn;

import wdsjn.marek.WordGraph;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class MainProgram {

    private static final List<String> MAIN_WORDS_LIST = Arrays.asList("ocean", "morze", "spokojny", "woda");
    private static final Path NOTES_DIR = new File("/Users/grzegorz.miejski/home/workspaces/aaaaStudia/Semestr_IX/WDSJN/src/main/resources/notes/short/short_ocean_korpus.txt").toPath();
    private static final File DATA_DIR = new File("/Users/grzegorz.miejski/home/workspaces/aaaaStudia/Semestr_IX/WDSJN/src/main/resources/data");

    public static void main(String[] args) throws IOException {
//        WordGraph graph = new WordGraph(DATA_DIR);

        SentenceParser sentenceParser = new SentenceParser();
        SnippetExtractor snippetExtractor = new SnippetExtractor();

        Indexer indexer = new Indexer("ocean");

        List<Sentence> sentences = Files.readAllLines(NOTES_DIR).stream()
                .map(sentenceParser::parse).collect(toList());

        List<Sentence> collect = sentences.stream().map(indexer::toIndexedSentence).flatMap(snippetExtractor::extract).collect(toList());


        collect.forEach(System.out::println);
        System.out.println("Finished");



    }
}
