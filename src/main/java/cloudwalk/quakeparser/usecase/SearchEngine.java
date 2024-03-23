package cloudwalk.quakeparser.usecase;

import cloudwalk.quakeparser.usecase.exception.DataNotFoundByRegexException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class SearchEngine {
    /**
     * Searches for a data through an expression
     *
     * @param text
     *        The text base used to search a data
     * @param regex
     *        The expression to be compiled
     * @return The result found as {@link String}
     */
    public String search(String text, String regex) {

        try {
            final Pattern pattern = Pattern.compile(regex);
            final Matcher matcher = pattern.matcher(text);
            matcher.find();

            return matcher.group(1);
        } catch (IllegalStateException ex) {
            log.error(
                    "Player data not found in {}, with the expression {}!",
                    text, regex, ex);
            throw new DataNotFoundByRegexException(ex);
        }

    }
}
