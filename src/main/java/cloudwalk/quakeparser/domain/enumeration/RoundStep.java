package cloudwalk.quakeparser.domain.enumeration;

import lombok.Getter;

@Getter
public enum RoundStep {

    KILL_STEP("Kill"),
    INIT_ROUND_STEP("InitGame"),
    SHUT_DOWN_STEP("------------------------------------------------------------"),
    NEW_PLAYER_STEP("ClientUserinfoChanged"),
    WORLD_PLAYER_CODE_STEP("1022");

    private final String label;

    RoundStep(final String label) {
        this.label = label;
    }

}
