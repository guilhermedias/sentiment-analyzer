package solutions.moot.feelings.domain.lemmatizer;

import java.util.Collection;

public interface Lemmatizer {
    Collection<String> lemmas(String sentence);
}
