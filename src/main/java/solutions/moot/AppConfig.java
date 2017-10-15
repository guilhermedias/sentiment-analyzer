package solutions.moot;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import solutions.moot.feelings.domain.aggregator.MeanValueVadAggregator;
import solutions.moot.feelings.domain.aggregator.VadAggregator;
import solutions.moot.feelings.domain.analyzer.SimpleVadVadSentenceAnalyzer;
import solutions.moot.feelings.domain.analyzer.VadSentenceAnalyzer;
import solutions.moot.feelings.domain.dictionary.VadDictionary;
import solutions.moot.feelings.domain.lemmatizer.Lemmatizer;
import solutions.moot.feelings.infra.dictionary.InMemoryCsvVadDictionary;
import solutions.moot.feelings.infra.lemmatizer.NlpCoreBasedLemmatizer;
import solutions.moot.feelings.infra.proxy.LoggingVadDictionaryProxy;
import solutions.moot.util.SentenceVadValuePrinter;

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
        return new SimpleVadVadSentenceAnalyzer(lemmatizer, loggingVadDictionary, aggregator);
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
