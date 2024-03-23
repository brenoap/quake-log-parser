package cloudwalk.quakeparser.controller.json;

import cloudwalk.quakeparser.domain.Round;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RoundDetailJsonResponseMapper {

    private RoundDetailJsonResponseMapper() {}

    public static List<RoundDetailJsonResponse> toRoundsDetailJsonResponse(List<Round> rounds) {
        List<RoundDetailJsonResponse> roundsResponse = new ArrayList<>();

        rounds.forEach(
                round -> {
                    RoundDetailJsonResponse roundResponse = new RoundDetailJsonResponse();
                    List<String> playersResponse = new ArrayList<>();
                    Map<String, Integer> killsResponse = new HashMap<>();

                    round.getPlayers()
                            .forEach(
                                    (playerCode, player) -> {
                                        playersResponse.add(player.getName());
                                        killsResponse.put(player.getName(), player.getKillsQuantity());
                                    });

                    roundResponse.setPlayers(playersResponse);
                    roundResponse.setKills(killsResponse);
                    roundResponse.setKillsQuantity(round.getKillsQuantity());
                    roundsResponse.add(roundResponse);
                });

        return roundsResponse;
    }

}
