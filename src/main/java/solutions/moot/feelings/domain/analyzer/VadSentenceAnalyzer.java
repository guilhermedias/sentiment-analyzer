package solutions.moot.feelings.domain.analyzer;

import solutions.moot.feelings.domain.dictionary.VadValue;

public interface VadSentenceAnalyzer {
    VadValue analyzeSentence(String sentence);
}
