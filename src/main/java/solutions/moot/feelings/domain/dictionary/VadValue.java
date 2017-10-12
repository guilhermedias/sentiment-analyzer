package solutions.moot.feelings.domain.dictionary;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class VadValue {
    private BigDecimal valence;
    private BigDecimal arousal;
    private BigDecimal dominance;
}
