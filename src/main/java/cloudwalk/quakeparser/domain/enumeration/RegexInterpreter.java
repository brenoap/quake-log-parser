package cloudwalk.quakeparser.domain.enumeration;

import lombok.Getter;

@Getter
public enum RegexInterpreter {

    TO_GET_PLAYER_CODE("ClientUserinfoChanged\\:\\s(\\d+)"),
    TO_GET_PLAYER_NAME("\\sn\\\\([\\w\\W]+)\\\\t\\\\"),
    TO_GET_KILLER_CODE("Kill:\\s(\\d+)"),
    TO_GET_KILLED_PLAYER_CODE("Kill:\\s\\d+\\s(\\d+)"),
    BY_DEATH_CAUSE("by\\s(?=\\S*['_])([a-zA-Z'_]+)");

    private final String label;

    RegexInterpreter(final String label) {
        this.label = label;
    }

}