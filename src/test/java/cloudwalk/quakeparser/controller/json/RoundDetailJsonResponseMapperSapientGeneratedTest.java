package cloudwalk.quakeparser.controller.json;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import cloudwalk.quakeparser.domain.Player;
import cloudwalk.quakeparser.domain.Round;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

@Timeout(value = 5)
class RoundDetailJsonResponseMapperSapientGeneratedTest {

    //Sapient generated method id: ${d56c5ddd-df6a-338d-b009-3a2b9cb1cca1}, hash: 568BD015A527F9A2204F28DEDF013084
    @Test()
    void toRoundsDetailJsonResponseTest() {
        //Arrange Statement(s)
        Player player = new Player();
        player.setName("name1");
        player.setKillsQuantity(0);
        Map<String, Player> stringPlayerMap = new HashMap<>();
        stringPlayerMap.put("playersItem1Key1", player);
        Round round = new Round();
        round.setPlayers(stringPlayerMap);
        round.setKillsQuantity(0);
        List<Round> roundList = new ArrayList<>();
        roundList.add(round);

        //Act Statement(s)
        List<RoundDetailJsonResponse> result = RoundDetailJsonResponseMapper.toRoundsDetailJsonResponse(roundList);

        //Assert statement(s)
        //TODO: Please implement equals method in RoundDetailJsonResponse for verification of the entire object or you need to adjust respective assertion statements
        assertAll("result", () -> assertThat(result.size(), equalTo(1)));
    }
}
