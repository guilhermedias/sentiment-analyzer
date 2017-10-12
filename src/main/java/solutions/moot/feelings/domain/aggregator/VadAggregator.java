package solutions.moot.feelings.domain.aggregator;

import solutions.moot.feelings.domain.dictionary.VadEntry;
import solutions.moot.feelings.domain.dictionary.VadValue;

import java.util.Collection;

public interface VadAggregator {
    VadValue aggregate(String sentence, Collection<VadEntry> vadEntries);
}
