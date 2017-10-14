package solutions.moot.feelings.domain.analyzer;

import solutions.moot.feelings.domain.aggregator.VadAggregator;
import solutions.moot.feelings.domain.dictionary.VadDictionary;
import solutions.moot.feelings.domain.dictionary.VadEntry;
import solutions.moot.feelings.domain.dictionary.VadValue;
import solutions.moot.feelings.domain.lemmatizer.Lemmatizer;

import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class SimpleVadVadSentenceAnalyzer implements VadSentenceAnalyzer {
    private Lemmatizer lemmatizer;
    private VadDictionary dictionary;
    private VadAggregator aggregator;

    public SimpleVadVadSentenceAnalyzer(Lemmatizer lemmatizer, VadDictionary dictionary, VadAggregator aggregator) {
        this.lemmatizer = lemmatizer;
        this.dictionary = dictionary;
        this.aggregator = aggregator;
    }

    @Override
    public VadValue analyzeSentence(String sentence) {
        Collection<VadEntry> entries = lemmatizer.lemmas(sentence)
                .stream()
                .map(dictionary::getEntry)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());

        return aggregator.aggregate(sentence, entries);
    }
}
