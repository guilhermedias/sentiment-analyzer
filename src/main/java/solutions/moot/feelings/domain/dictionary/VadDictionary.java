package solutions.moot.feelings.domain.dictionary;

import java.util.Optional;

public interface VadDictionary {
    Optional<VadEntry> getEntry(String word);
}
