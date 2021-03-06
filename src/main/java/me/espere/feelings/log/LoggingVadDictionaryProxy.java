package me.espere.feelings.log;

import me.espere.feelings.spec.dictionary.VadDictionary;
import me.espere.feelings.spec.dictionary.VadEntry;
import org.slf4j.Logger;

import java.util.Optional;

public class LoggingVadDictionaryProxy implements VadDictionary {
    private VadDictionary dictionary;
    private Logger logger;

    public LoggingVadDictionaryProxy(VadDictionary dictionary, Logger logger) {
        this.dictionary = dictionary;
        this.logger = logger;
    }

    @Override
    public Optional<VadEntry> getEntry(String word) {
        logger.info("Looking up word [ {} ]", word);

        Optional<VadEntry> optionalEntry = dictionary.getEntry(word);

        if (optionalEntry.isPresent()) {
            VadEntry entry = optionalEntry.get();
            logger.info("Valence: [ {} ]", entry.getVadValue().getValence());
            logger.info("Arousal: [ {} ]", entry.getVadValue().getArousal());
            logger.info("Dominance: [ {} ]", entry.getVadValue().getDominance());
        } else {
            logger.info("Word [ {} ] not found", word);
        }

        return optionalEntry;
    }
}
