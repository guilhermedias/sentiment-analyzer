package solutions.moot.feelings;

import solutions.moot.feelings.spec.analyzer.VadSentenceAnalyzer;
import solutions.moot.feelings.spec.dictionary.VadValue;
import solutions.moot.feelings.util.SentenceVadValuePrinter;

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
