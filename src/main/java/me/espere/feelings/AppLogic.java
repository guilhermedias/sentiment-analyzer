package me.espere.feelings;

import me.espere.feelings.spec.analyzer.VadSentenceAnalyzer;
import me.espere.feelings.spec.dictionary.VadValue;
import me.espere.feelings.util.SentenceVadValuePrinter;

public class AppLogic {
    private VadSentenceAnalyzer analyzer;
    private SentenceVadValuePrinter vadValuePrinter;

    public AppLogic(VadSentenceAnalyzer analyzer, SentenceVadValuePrinter vadValuePrinter) {
        this.analyzer = analyzer;
        this.vadValuePrinter = vadValuePrinter;
    }

    public void run() {
        String sentence = "We are super excited to announce that our last release was a success";
        VadValue sentenceVadValue = analyzer.analyzeSentence(sentence);
        vadValuePrinter.printSentenceVadValue(sentence, sentenceVadValue);
    }
}
