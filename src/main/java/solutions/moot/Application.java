package solutions.moot;

import solutions.moot.feelings.domain.aggregator.MeanValueVadAggregator;
import solutions.moot.feelings.domain.aggregator.VadAggregator;
import solutions.moot.feelings.domain.analyzer.SimpleVadVadSentenceAnalyzer;
import solutions.moot.feelings.domain.analyzer.VadSentenceAnalyzer;
import solutions.moot.feelings.domain.dictionary.VadDictionary;
import solutions.moot.feelings.domain.dictionary.VadValue;
import solutions.moot.feelings.infra.dictionary.InMemoryCsvVadDictionary;

import static java.lang.String.format;

public class Application {
    public static void main(String[] args) throws Exception {
        VadDictionary dictionary = new InMemoryCsvVadDictionary("vad-dictionary.csv");
        VadAggregator aggregator = new MeanValueVadAggregator();

        VadSentenceAnalyzer analyzer = new SimpleVadVadSentenceAnalyzer(dictionary, aggregator);

        String sentence = "We are super excited to announce that our last release was a success";
        VadValue sentenceVadValue = analyzer.analyzeSentence(sentence);
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
