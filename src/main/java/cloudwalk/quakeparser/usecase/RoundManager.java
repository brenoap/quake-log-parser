package cloudwalk.quakeparser.usecase;

import cloudwalk.quakeparser.domain.DeathCause;
import cloudwalk.quakeparser.domain.Player;
import cloudwalk.quakeparser.domain.Round;
import cloudwalk.quakeparser.domain.enumeration.RegexInterpreter;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static cloudwalk.quakeparser.domain.enumeration.RoundStep.*;

@Slf4j
@Service
@Getter
@Setter
public class RoundManager implements AutoCloseable {

    private SearchEngine searchEngine;
    protected List<Round> rounds = new ArrayList<>();
    private Round round;
    private boolean roundStarted = false;

    @Autowired
    public RoundManager(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    /**
     * Processes the round using key markers to identify all valid scenarios in the round
     *
     * @param row
     *        Each row from the file that represents round instructions
     */
    public void process(String row) {

        if (isAnInstructionToStartNewRound(row)) {
            initNewRound();

        } else {
            if (isAnInstructionToRegisterNewPlayer(row)) {
                registerNewPlayer(row);

            } else if (isAnInstructionToRegisterNewDeath(row)) {

                collectScore(row);
                increaseRoundKillsQuantity();

            } else if (isAnInstructionToFinishTheRound(row)) {
                finishCurrentRound();
            }
        }
    }

    /**
     * Processes the round using key markers to identify all valid scenarios in the round
     *
     * @param row
     *        Each row from the file that represents round instructions
     */
    public void processDeathCause(String row) {

        if (isAnInstructionToStartNewRound(row)) {
            initNewRound();

        } else {
            if (isAnInstructionToRegisterNewDeath(row)) {

                registerNewDeath(row);
                collectDeathCauseScore(row);
                increaseRoundDeathsQuantity();

            } else if (isAnInstructionToFinishTheRound(row)) {
                finishCurrentRound();
            }
        }
    }

    /**
     * Registers a new round player
     *
     * @param row
     *        The text base used to get player information
     */
    private void registerNewPlayer(String row) {

        final String playerCode = searchEngine.search(row, RegexInterpreter.TO_GET_PLAYER_CODE.getLabel());
        final String playerName = searchEngine.search(row, RegexInterpreter.TO_GET_PLAYER_NAME.getLabel());

        Player player = new Player();
        player.setName(playerName);

        // Checks if the player is already registered in the current round and if so, its score is recovered
        if (round.getPlayers().get(playerCode) != null) {
            player.setKillsQuantity(round.getPlayers().get(playerCode).getKillsQuantity());
        }
        round.getPlayers().put(playerCode, player);
    }

    private void registerNewDeath(String row) {

        final String deathName = searchEngine.search(row, RegexInterpreter.BY_DEATH_CAUSE.getLabel());

        DeathCause deathCause = new DeathCause();
        deathCause.setName(deathName);

        // Checks if the player is already registered in the current round and if so, its score is recovered
        if (round.getDeaths().get(deathName) != null) {
            deathCause.setDeathsQuantity(round.getDeaths().get(deathName).getDeathsQuantity());
        }
        round.getDeaths().put(deathName, deathCause);
    }

    /**
     * Collects points for the round players
     *
     * @param row
     *        The text base used to get player information
     */
    private void collectScore(String row) {

        final String killerCode = searchEngine.search(row, RegexInterpreter.TO_GET_KILLER_CODE.getLabel());
        final String killedPlayerCode = searchEngine.search(row, RegexInterpreter.TO_GET_KILLED_PLAYER_CODE.getLabel());

        // Checks if the world is the killer in this turn or if the killer and the killed player are the same person
        if (killerCode.equals(WORLD_PLAYER_CODE_STEP.getLabel()) || killerCode.equals(killedPlayerCode)) {
            registerNegativePointForTheKilledPlayer(killedPlayerCode);
        } else {
            registerPositivePointForTheKiller(killerCode);
        }

    }

    /**
     * Collects points for the round players
     *
     * @param row
     *        The text base used to get player information
     */
    private void collectDeathCauseScore(String row) {

        final String deathCauseName = searchEngine.search(row, RegexInterpreter.BY_DEATH_CAUSE.getLabel());
        registerDeathCause(deathCauseName);
    }

    /**
     * Registers a negative point for the killed player in this turn
     *
     * @param killedPlayerCode
     *        The killed player in this turn
     */
    private void registerNegativePointForTheKilledPlayer(String killedPlayerCode) {
        Player player = round.getPlayers().get(killedPlayerCode);
        player.setKillsQuantity(player.getKillsQuantity() - 1);

        round.getPlayers().put(killedPlayerCode, player);
    }

    /**
     * Registers a positive point for the killer in this turn
     *
     * @param killerCode
     *        The killer in this turn
     */
    private void registerPositivePointForTheKiller(String killerCode) {
        Player player = round.getPlayers().get(killerCode);
        player.setKillsQuantity(player.getKillsQuantity() + 1);

        round.getPlayers().put(killerCode, player);
    }

    private void registerDeathCause(String deathCauseName) {
        DeathCause deathCause = round.getDeaths().get(deathCauseName);
        deathCause.setDeathsQuantity(deathCause.getDeathsQuantity() + 1);

        round.getDeaths().put(deathCauseName, deathCause);
    }

    private boolean isAnInstructionToStartNewRound(String text) {
        return text.contains(INIT_ROUND_STEP.getLabel());
    }

    private boolean isAnInstructionToRegisterNewPlayer(String text) {
        return text.contains(NEW_PLAYER_STEP.getLabel());
    }

    private boolean isAnInstructionToRegisterNewDeath(String text) {
        return text.contains(KILL_STEP.getLabel());
    }

    private boolean isAnInstructionToFinishTheRound(String text) {
        return isRoundStarted() && text.contains(SHUT_DOWN_STEP.getLabel());
    }

    private void initNewRound() {
        round = new Round();
        setRoundStarted(true);
    }

    private void increaseRoundKillsQuantity() {
        round.setKillsQuantity(round.getKillsQuantity() + 1);
    }

    private void increaseRoundDeathsQuantity() {
        round.setDeathsQuantity(round.getDeathsQuantity() + 1);
    }

    private void finishCurrentRound() {
        rounds.add(round);
        setRoundStarted(false);
    }

    @Override
    public void close() throws Exception {
        searchEngine = new SearchEngine();
        rounds = new ArrayList<>();
        round = new Round();
        roundStarted = false;
    }

}
