package solutions.moot.feelings;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import solutions.moot.feelings.impl.dictionary.InMemoryCsvVadDictionary;
import solutions.moot.feelings.impl.lemmatizer.NlpCoreBasedLemmatizer;
import solutions.moot.feelings.log.LoggingVadDictionaryProxy;
import solutions.moot.feelings.spec.aggregator.MeanValueVadAggregator;
import solutions.moot.feelings.spec.aggregator.VadAggregator;
import solutions.moot.feelings.spec.analyzer.SimpleVadSentenceAnalyzer;
import solutions.moot.feelings.spec.analyzer.VadSentenceAnalyzer;
import solutions.moot.feelings.spec.dictionary.VadDictionary;
import solutions.moot.feelings.spec.lemmatizer.Lemmatizer;
import solutions.moot.feelings.util.SentenceVadValuePrinter;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
public class AppConfig {
    @Bean
    public Lemmatizer nlpCoreBasedLemmatizer() {
        return new NlpCoreBasedLemmatizer();
    }

    @Bean
    public Logger dictionaryLookupLogger() {
        return getLogger("VAD dictionary look up logger");
    }

    @Bean
    public VadDictionary inMemoryCsvVadDictionary() throws IOException {
        return new InMemoryCsvVadDictionary("vad-dictionary.csv");
    }

    @Bean
    public VadDictionary loggingVadDictionary(VadDictionary inMemoryCsvVadDictionary, Logger logger)
            throws IOException {
        return new LoggingVadDictionaryProxy(inMemoryCsvVadDictionary, logger);
    }

    @Bean
    public VadAggregator meanValueVadAggregator() {
        return new MeanValueVadAggregator();
    }

    @Bean
    public VadSentenceAnalyzer simpleVadSentenceAnalyzer(Lemmatizer lemmatizer,
                                                         VadDictionary loggingVadDictionary,
                                                         VadAggregator aggregator) {
        return new SimpleVadSentenceAnalyzer(lemmatizer, loggingVadDictionary, aggregator);
    }

    @Bean
    public SentenceVadValuePrinter sentenceVadValuePrinter() {
        return new SentenceVadValuePrinter();
    }

    @Bean
    public AppLogic appLogic(VadSentenceAnalyzer analyzer, SentenceVadValuePrinter sentenceVadValuePrinter) {
        return new AppLogic(analyzer, sentenceVadValuePrinter);
    }
}