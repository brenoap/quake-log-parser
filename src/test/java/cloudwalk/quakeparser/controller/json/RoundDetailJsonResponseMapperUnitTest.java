package cloudwalk.quakeparser.controller.json;

import cloudwalk.quakeparser.domain.Player;
import cloudwalk.quakeparser.domain.Round;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoundDetailJsonResponseMapperUnitTest {

    @Test
    public void shouldMapperRoundToRoundDetailJsonResponse() {
        // Given a round list
        List<Round> rounds = populateRoundList();

        // When is tried to mapping the rounds object to the rounds response object
        List<RoundDetailJsonResponse> roundsDetail = RoundDetailJsonResponseMapper
                .toRoundsDetailJsonResponse(rounds);

        // Then is mapped the round response object
        Assert.assertNotNull(roundsDetail);
        Assert.assertEquals(Integer.valueOf(300), roundsDetail.iterator().next().getKillsQuantity());
        Assert.assertTrue(roundsDetail.iterator().next().getKills().containsKey("Player name"));
        Assert.assertEquals(Integer.valueOf(100), roundsDetail.iterator().next().getKills().get("Player name"));
        Assert.assertEquals("Player name", roundsDetail.iterator().next().getPlayers().iterator().next());
        Assert.assertEquals(3, roundsDetail.size());
    }

    private List<Round> populateRoundList() {
        List<Round> rounds = new ArrayList<>();
        rounds.add(createRound());
        rounds.add(createRound());
        rounds.add(createRound());

        return rounds;
    }

    private Round createRound() {
        Round round = new Round();

        round.setKillsQuantity(300);
        round.setPlayers(createPlayers());

        return round;
    }

    private Map<String, Player> createPlayers() {
        Map<String, Player> players = new HashMap<>();

        players.put("1", createPlayer());
        players.put("2", createPlayer());
        players.put("3", createPlayer());

        return players;
    }

    private Player createPlayer() {
        Player player = new Player();

        player.setName("Player name");
        player.setKillsQuantity(100);

        return player;
    }
}
