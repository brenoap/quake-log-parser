package cloudwalk.quakeparser.usecase;

import cloudwalk.quakeparser.domain.Round;
import cloudwalk.quakeparser.usecase.exception.UnreadableFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Slf4j
@Service
public class RoundReportGenerator {

    private final RoundManager round;

    @Autowired
    public RoundReportGenerator(RoundManager round) {
        this.round = round;
    }

    private static final String FILE_NAME = "/qgames.log";

    /**
     * Reads all lines from a file as a {@code Stream}. Bytes from the file are decoded into
     * characters.
     *
     * @return The rounds report as {@link List}
     */
    public List<Round> generate() {
        try (Stream<String> stream = getLogFileAsStream()) {
            stream.forEach(round::process);

        } catch (UncheckedIOException | NoSuchElementException ex) {
            log.error("An error occurred while processing the log file", ex);
            throw new UnreadableFileException(ex);
        }

        return round.getRounds();
    }

    public List<Round> generateDeathCause() {
        try (Stream<String> stream = getLogFileAsStream()) {
            stream.forEach(round::processDeathCause);

        } catch (UncheckedIOException | NoSuchElementException ex) {
            log.error("An error occurred while processing the log file", ex);
            throw new UnreadableFileException(ex);
        }

        return round.getRounds();
    }

    private Stream<String> getLogFileAsStream() {
        InputStream in = getClass().getResourceAsStream(FILE_NAME);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        return reader.lines();
    }
}
