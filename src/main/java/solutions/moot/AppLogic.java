package solutions.moot;

import solutions.moot.feelings.domain.analyzer.VadSentenceAnalyzer;
import solutions.moot.feelings.domain.dictionary.VadValue;
import solutions.moot.util.SentenceVadValuePrinter;

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
