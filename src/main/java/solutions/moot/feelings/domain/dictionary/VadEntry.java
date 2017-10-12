package solutions.moot.feelings.domain.dictionary;

import lombok.Value;

@Value
public class VadEntry {
    private String word;
    private VadValue vadValue;
}
