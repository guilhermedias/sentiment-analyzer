package solutions.moot.feelings.domain.analyzer;

import solutions.moot.feelings.domain.aggregator.VadAggregator;
import solutions.moot.feelings.domain.dictionary.VadDictionary;
import solutions.moot.feelings.domain.dictionary.VadEntry;
import solutions.moot.feelings.domain.dictionary.VadValue;

import java.util.Collection;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class SimpleVadVadSentenceAnalyzer implements VadSentenceAnalyzer {
    private static final String WORD_SEPARATOR = " ";

    private VadDictionary dictionary;
    private VadAggregator aggregator;

    public SimpleVadVadSentenceAnalyzer(VadDictionary dictionary, VadAggregator aggregator) {
        this.dictionary = dictionary;
        this.aggregator = aggregator;
    }

    @Override
    public VadValue analyzeSentence(String sentence) {
        Collection<VadEntry> entries = stream(sentence.split(WORD_SEPARATOR))
                .map(dictionary::getEntry)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());

        return aggregator.aggregate(sentence, entries);
    }
}
