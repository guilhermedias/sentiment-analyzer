package me.espere.feelings;

import me.espere.feelings.impl.dictionary.InMemoryCsvVadDictionary;
import me.espere.feelings.impl.lemmatizer.NlpCoreBasedLemmatizer;
import me.espere.feelings.spec.aggregator.MeanValueVadAggregator;
import me.espere.feelings.spec.aggregator.VadAggregator;
import me.espere.feelings.spec.analyzer.SimpleVadSentenceAnalyzer;
import me.espere.feelings.spec.analyzer.VadSentenceAnalyzer;
import me.espere.feelings.spec.dictionary.VadDictionary;
import me.espere.feelings.spec.lemmatizer.Lemmatizer;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import me.espere.feelings.log.LoggingVadDictionaryProxy;
import me.espere.feelings.util.SentenceVadValuePrinter;

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
    public LoggingVadDictionaryProxy loggingVadDictionary(VadDictionary inMemoryCsvVadDictionary, Logger logger)
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
