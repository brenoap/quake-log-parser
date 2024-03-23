package cloudwalk.quakeparser.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Round {

    private Integer killsQuantity = 0;
    private Map<String, Player> players = new HashMap<>();
    private Integer deathsQuantity = 0;
    private Map<String, DeathCause> deaths = new HashMap<>();

}
