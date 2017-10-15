package solutions.moot;

import solutions.moot.feelings.domain.analyzer.VadSentenceAnalyzer;
import solutions.moot.feelings.domain.dictionary.VadValue;

import static java.lang.String.format;

public class AppLogic {
    private VadSentenceAnalyzer analyzer;

    public AppLogic(VadSentenceAnalyzer analyzer) {
        this.analyzer = analyzer;
    }

    public void run() {
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
