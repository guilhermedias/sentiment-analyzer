package me.espere.feelings.log;

import me.espere.feelings.commons.Conditions;
import me.espere.feelings.spec.dictionary.VadDictionary;
import me.espere.feelings.spec.dictionary.VadEntry;
import me.espere.feelings.spec.dictionary.VadValue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoggingVadDictionaryProxyTest {
    private LoggingVadDictionaryProxy dictionaryProxy;

    @Mock
    private VadDictionary dictionary;

    @Mock
    private Logger logger;

    @Before
    public void setUp() throws Exception {
        dictionaryProxy = new LoggingVadDictionaryProxy(dictionary, logger);
    }

    @Test
    public void shouldDelegateLookupToActualDictionary() {
        VadEntry vadEntry = new VadEntry("abnormal", new VadValue(
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO
        ));

        when(dictionary.getEntry("abnormal")).thenReturn(Optional.of(vadEntry));

        Optional<VadEntry> optionalEntry = dictionaryProxy.getEntry("abnormal");

        verify(dictionary).getEntry("abnormal");

        assertThat(optionalEntry).isPresent();

        VadEntry entry = optionalEntry.get();

        assertThat(vadEntry.getWord()).isEqualToIgnoringCase("abnormal");

        VadValue vadValue = entry.getVadValue();
        assertThat(vadValue.getValence()).is(Conditions.equalTo(0.0));
        assertThat(vadValue.getArousal()).is(Conditions.equalTo(0.0));
        assertThat(vadValue.getDominance()).is(Conditions.equalTo(0.0));
    }

    @Test
    public void shouldLogSuccessfulLookup() {
        VadEntry vadEntry = new VadEntry("abnormal", new VadValue(
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO
        ));

        when(dictionary.getEntry("abnormal")).thenReturn(Optional.of(vadEntry));

        dictionaryProxy.getEntry("abnormal");

        verify(logger).info("Looking up word [ {} ]", vadEntry.getWord());
        verify(logger).info("Valence: [ {} ]", vadEntry.getVadValue().getValence());
        verify(logger).info("Valence: [ {} ]", vadEntry.getVadValue().getArousal());
        verify(logger).info("Valence: [ {} ]", vadEntry.getVadValue().getDominance());
    }

    @Test
    public void shouldLogUnsuccessfulLookup() {
        when(dictionary.getEntry("abnormal")).thenReturn(Optional.empty());

        dictionaryProxy.getEntry("abnormal");

        verify(logger).info("Looking up word [ {} ]", "abnormal");
        verify(logger).info("Word [ {} ] not found", "abnormal");
    }
}
