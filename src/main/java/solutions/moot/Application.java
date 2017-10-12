package solutions.moot;

import solutions.moot.feelings.domain.aggregator.MeanValueVadAggregator;
import solutions.moot.feelings.domain.aggregator.VadAggregator;
import solutions.moot.feelings.domain.dictionary.VadDictionary;
import solutions.moot.feelings.domain.dictionary.VadEntry;
import solutions.moot.feelings.domain.dictionary.VadValue;
import solutions.moot.feelings.infra.dictionary.InMemoryCsvVadDictionary;

import java.util.Collection;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class Application {
    public static void main(String[] args) throws Exception {
        VadDictionary dictionary = new InMemoryCsvVadDictionary("vad-dictionary.csv");
        VadAggregator aggregator = new MeanValueVadAggregator();

        analyzeSentence("We are excited to announce that our last release was a success", dictionary, aggregator);
    }

    private static void analyzeSentence(String sentence, VadDictionary dictionary, VadAggregator aggregator) {
        Collection<VadEntry> vadEntries = stream(sentence.split(" "))
                .map(dictionary::getEntry)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());

        VadValue sentenceVadValue = aggregator.aggregate(sentence, vadEntries);

        printSentenceScore(sentence, sentenceVadValue);
    }

    private static void printSentenceScore(String sentence, VadValue sentenceVadValue) {
        System.out.println(format("Sentence: %s", sentence));
        System.out.println(format("Valence: %s", sentenceVadValue.getValence()));
        System.out.println(format("Arousal: %s", sentenceVadValue.getArousal()));
        System.out.println(format("Dominance: %s", sentenceVadValue.getDominance()));
        System.out.println();
    }
}
