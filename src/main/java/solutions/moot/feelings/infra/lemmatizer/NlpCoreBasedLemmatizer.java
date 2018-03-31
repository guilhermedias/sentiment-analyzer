package solutions.moot.feelings.infra.lemmatizer;

import edu.stanford.nlp.simple.Sentence;
import solutions.moot.feelings.spec.lemmatizer.Lemmatizer;

import java.util.Collection;

public class NlpCoreBasedLemmatizer implements Lemmatizer {
    @Override
    public Collection<String> lemmas(String sentence) {
        return new Sentence(sentence).lemmas();
    }
}
