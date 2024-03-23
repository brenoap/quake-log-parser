package cloudwalk.quakeparser.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeathCause {

    private String name;
    private Integer deathsQuantity = 0;

}
