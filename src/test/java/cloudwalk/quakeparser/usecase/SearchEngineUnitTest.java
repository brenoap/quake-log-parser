package cloudwalk.quakeparser.usecase;

import cloudwalk.quakeparser.domain.enumeration.RegexInterpreter;
import cloudwalk.quakeparser.usecase.exception.DataNotFoundByRegexException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class SearchEngineUnitTest {

    @InjectMocks
    private SearchEngine searchEngine;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSearchPlayerCode() {
        // Given a text with a player code information and an expression to search a player code
        String text =
                " 20:34 ClientUserinfoChanged: 2 n\\Isgalamido\\t\\0\\model\\xian/default\\hmodel\\"
                        + "xian/default\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\100\\w\\0\\l\\0\\"
                        + "tt\\0\\tl\\0\n";
        String regex = RegexInterpreter.TO_GET_PLAYER_CODE.getLabel();

        // When is searched a player code
        String result = searchEngine.search(text, regex);

        // Then is returned correctly a player code
        Assert.assertEquals("2", result);
    }

    @Test
    public void shouldSearchPlayerCodeWithMultipleDigits() {
        // Given a text with many digits code information and an expression to search a player code
        String text =
                " 20:34 ClientUserinfoChanged: 12345678910 n\\Isgalamido\\t\\0\\model\\xian/default\\"
                        + "hmodel\\xian/default\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\100\\w\\0\\"
                        + "l\\0\\tt\\0\\tl\\0\n";
        String regex = RegexInterpreter.TO_GET_PLAYER_CODE.getLabel();

        // When is searched a player code
        String result = searchEngine.search(text, regex);

        // Then is returned correctly a player code
        Assert.assertEquals("12345678910", result);
    }

    @Test
    public void shouldSearchPlayerName() {
        // Given a text with a player name information and an expression to search a player name
        String text =
                " 20:34 ClientUserinfoChanged: 2 n\\Isgalamido\\t\\0\\model\\xian/default\\hmodel\\"
                        + "xian/default\\g_redteam\\\\g_blueteam\\\\c1\\4\\c2\\5\\hc\\100\\w\\0\\l\\0\\"
                        + "tt\\0\\tl\\0\n";
        String regex = RegexInterpreter.TO_GET_PLAYER_NAME.getLabel();

        // When is searched a player name
        String result = searchEngine.search(text, regex);

        // Then is returned correctly a player name
        Assert.assertEquals("Isgalamido", result);
    }

    @Test
    public void shouldSearchPlayerNameWithMultipleWords() {
        // Given a text with a player name information using multiple words and an expression
        // to search a player name
        String text =
                "  21:51 ClientUserinfoChanged: 3 n\\Dono da Bola de futebol mais antiga do mundo"
                        + " vista até os dias de hoje\\t\\0\\model\\sarge/krusade\\hmodel\\sarge/krus"
                        + "ade\\g_redteam\\\\g_blueteam\\\\c1\\5\\c2\\5\\hc\\95\\w\\0\\l\\0\\tt\\0\\tl"
                        + "\\0\n";
        String regex = RegexInterpreter.TO_GET_PLAYER_NAME.getLabel();

        // When is searched a player name
        String result = searchEngine.search(text, regex);

        // Then is returned correctly a player name
        Assert.assertEquals(
                "Dono da Bola de futebol mais antiga do mundo vista até os dias de hoje",
                result);
    }

    @Test
    public void shouldSearchKillerCode() {
        // Given a text with a killer information and an expression to search a killer code
        String textOneWorldNameKiller = " 22:06 Kill: 2 3 7: Isgalamido killed Mocinha by MOD_ROCKET_SPLASH\n";
        // Given a text with a killer code information using multiple digits and an expression
        // to search a killer code
        String textMultipleWorldNameKiller = " 22:06 Kill: 2 3 7: Dono da Bola killed Mocinha by MOD_ROCKET_SPLASH\n";

        String regex = RegexInterpreter.TO_GET_KILLER_CODE.getLabel();

        // When is searched a killer code
        String resultOneWorldNameKiller = searchEngine.search(textOneWorldNameKiller, regex);

        // And searched a killer code with multiple worlds name
        String resultMultipleWorldNameKiller = searchEngine.search(textMultipleWorldNameKiller, regex);

        // Then is returned correctly a killer code
        Assert.assertEquals("2", resultOneWorldNameKiller);
        // Then is returned correctly a killer code
        Assert.assertEquals("2", resultOneWorldNameKiller);
    }

    @Test
    public void shouldSearchKillerCodeWhenItIsTheWorld() {
        // Given a text with the world player and an expression to search a killer code
        String text = " 21:42 Kill: 1022 2 22: <world> killed Isgalamido by MOD_TRIGGER_HURT\n";
        String regex = RegexInterpreter.TO_GET_KILLER_CODE.getLabel();

        // When is searched a killer code
        String result = searchEngine.search(text, regex);

        // Then is returned correctly the world player code as the killer
        Assert.assertEquals("1022", result);
    }

    @Test
    public void shouldSearchKilledPlayerCode() {
        // Given a text with a killed player code and an expression to search a killed player code
        String text = " 22:06 Kill: 2 3 7: Isgalamido killed Mocinha by MOD_ROCKET_SPLASH\n";
        String regex = RegexInterpreter.TO_GET_KILLED_PLAYER_CODE.getLabel();

        // When is searched a killed player code
        String result = searchEngine.search(text, regex);

        // Then is returned correctly a killed player code
        Assert.assertEquals("3", result);
    }

    @Test
    public void shouldSearchKilledPlayerCodeWithMultipleDigits() {
        // Given a text with a killed player code using multiple digits and an expression
        // to search a killed player code
        String text =
                " 22:06 Kill: 2 3 7: Isgalamido killed Dono da Bola by MOD_ROCKET_SPLASH\n";
        String regex = RegexInterpreter.TO_GET_KILLED_PLAYER_CODE.getLabel();

        // When is searched a killed player code
        String result = searchEngine.search(text, regex);

        // Then is returned correctly a killed player code
        Assert.assertEquals(
                "3", result);
    }

    @Test(expected = DataNotFoundByRegexException.class)
    public void shouldNotSearchDataWhenIsUsedIncorrectTheExpression() {
        // Given a text with only death information and an expression to search player information
        String text = " 22:06 Kill: 2 3 7: Isgalamido killed Mocinha by MOD_ROCKET_SPLASH\n";
        String regex = RegexInterpreter.TO_GET_PLAYER_NAME.getLabel();

        try {
            // When is searched a player information in a text with different purpose
            searchEngine.search(text, regex);
        } catch (DataNotFoundByRegexException ex) {
            // Then is returned an error about data not found
            throw ex;
        }
    }
    
}
