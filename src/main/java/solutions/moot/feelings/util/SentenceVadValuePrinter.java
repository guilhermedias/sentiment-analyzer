package solutions.moot.feelings.util;

import solutions.moot.feelings.spec.dictionary.VadValue;

import static java.lang.String.format;

public class SentenceVadValuePrinter {
    public void printSentenceVadValue(String sentence, VadValue sentenceVadValue) {
        System.out.println(format("Sentence: %s", sentence));
        System.out.println(format("Valence: %s", sentenceVadValue.getValence()));
        System.out.println(format("Arousal: %s", sentenceVadValue.getArousal()));
        System.out.println(format("Dominance: %s", sentenceVadValue.getDominance()));
        System.out.println();
    }
}