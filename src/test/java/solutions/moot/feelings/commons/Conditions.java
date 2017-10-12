package solutions.moot.feelings.commons;

import org.assertj.core.api.Condition;

import java.math.BigDecimal;
import java.util.function.Predicate;

public class Conditions {
    public static Condition<BigDecimal> equalTo(Double other) {
        Predicate<BigDecimal> hasValueOf = bigDecimal ->
                bigDecimal.equals(BigDecimal.valueOf(other));

        return new Condition<>(hasValueOf, "Big decimals are equal");
    }
}
