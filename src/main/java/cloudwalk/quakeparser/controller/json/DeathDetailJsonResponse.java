package cloudwalk.quakeparser.controller.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@JsonSerialize
public class DeathDetailJsonResponse {

    @JsonProperty("kills_by_means")
    private Map<String, Integer> killsByMeans;

}
