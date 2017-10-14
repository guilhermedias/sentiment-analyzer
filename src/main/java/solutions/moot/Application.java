package solutions.moot;

import org.slf4j.Logger;
import solutions.moot.feelings.domain.aggregator.MeanValueVadAggregator;
import solutions.moot.feelings.domain.aggregator.VadAggregator;
import solutions.moot.feelings.domain.analyzer.SimpleVadVadSentenceAnalyzer;
import solutions.moot.feelings.domain.analyzer.VadSentenceAnalyzer;
import solutions.moot.feelings.domain.dictionary.VadDictionary;
import solutions.moot.feelings.domain.dictionary.VadValue;
import solutions.moot.feelings.domain.lemmatizer.Lemmatizer;
import solutions.moot.feelings.infra.dictionary.InMemoryCsvVadDictionary;
import solutions.moot.feelings.infra.lemmatizer.NlpCoreBasedLemmatizer;
import solutions.moot.feelings.infra.proxy.LoggingVadDictionaryProxy;

import java.io.IOException;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;

public class Application {
    public static void main(String[] args) throws Exception {
        Lemmatizer lemmatizer = new NlpCoreBasedLemmatizer();
        VadDictionary dictionary = constructVadDictionary();
        VadAggregator aggregator = new MeanValueVadAggregator();

        VadSentenceAnalyzer analyzer = new SimpleVadVadSentenceAnalyzer(
                lemmatizer,
                dictionary,
                aggregator
        );

        String sentence = "We are super excited to announce that our last release was a success";
        VadValue sentenceVadValue = analyzer.analyzeSentence(sentence);
        printSentenceScore(sentence, sentenceVadValue);
    }

    private static VadDictionary constructVadDictionary() throws IOException {
        InMemoryCsvVadDictionary actualDictionary =
                new InMemoryCsvVadDictionary("vad-dictionary.csv");

        Logger logger = getLogger("VAD dictionary look up logger");

        return new LoggingVadDictionaryProxy(actualDictionary, logger);
    }

    private static void printSentenceScore(String sentence, VadValue sentenceVadValue) {
        System.out.println(format("Sentence: %s", sentence));
        System.out.println(format("Valence: %s", sentenceVadValue.getValence()));
        System.out.println(format("Arousal: %s", sentenceVadValue.getArousal()));
        System.out.println(format("Dominance: %s", sentenceVadValue.getDominance()));
        System.out.println();
    }
}
