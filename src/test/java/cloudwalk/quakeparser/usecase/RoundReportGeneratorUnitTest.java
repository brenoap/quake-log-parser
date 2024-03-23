package cloudwalk.quakeparser.usecase;

import cloudwalk.quakeparser.domain.Round;
import cloudwalk.quakeparser.usecase.exception.UnreadableFileException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RoundReportGeneratorUnitTest {


    @InjectMocks
    private RoundReportGenerator roundReportGenerator;

    @Mock
    private RoundManager roundManager;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGenerateRoundReportReadingTheLogFile() {
        // When is tried to generate a round report
        when(roundManager.getRounds()).thenReturn(new ArrayList<>());
        List<Round> roundReport = roundReportGenerator.generate();

        // Then is processed the round and returned a round report
        verify(roundManager, VerificationModeFactory.atLeast(1)).process(any());
        Assert.assertNotNull(roundReport);
    }

    @Test(expected = UnreadableFileException.class)
    public void shouldNotReadTheLogFile() {
        // Given a runtime exception when it is called the round manager to process a round
        doThrow(new NoSuchElementException()).when(roundManager).process(any());

        try {
            // When is tried to generate a round report
            roundReportGenerator.generate();
        } catch (UnreadableFileException ex) {
            // Then is returned a known exception
            throw ex;
        }
    }

}
