package cloudwalk.quakeparser.controller.json;

import cloudwalk.quakeparser.domain.Round;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeathDetailJsonResponseMapper {

    private DeathDetailJsonResponseMapper() {}

    public static List<DeathDetailJsonResponse> toDeathsDetailJsonResponse(List<Round> rounds) {
        List<DeathDetailJsonResponse> deathsDetailResponse = new ArrayList<>();

        rounds.forEach(
                round -> {
                    DeathDetailJsonResponse deathResponse = new DeathDetailJsonResponse();
                    Map<String, Integer> deathsResponse = new HashMap<>();

                    round.getDeaths()
                            .forEach(
                                    (deathCauseName, deathCause) -> {
                                        deathsResponse.put(deathCause.getName(), deathCause.getDeathsQuantity());
                                    });
                    deathResponse.setKillsByMeans(deathsResponse);
                    deathsDetailResponse.add(deathResponse);
                });

        return deathsDetailResponse;
    }

}
