package cloudwalk.quakeparser.usecase;

import cloudwalk.quakeparser.domain.Player;
import cloudwalk.quakeparser.domain.Round;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@Timeout(value = 5)
class RoundManagerUnitTest {

    private final SearchEngine searchEngineMock = mock(SearchEngine.class, "searchEngine");

    private final Round roundMock = mock(Round.class, "round");

    private AutoCloseable autoCloseableMocks;

    @InjectMocks()
    private RoundManager target;

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${e879cb2b-89c3-3432-8490-e4f1d778556d}, hash: 9976FB73D202AC8B711697B314504350
    @Test()
    void processWhenIsAnInstructionToStartNewRoundRow() {
        /* Branches:
         * (text.contains(INIT_ROUND_STEP.getLabel())) : true  #  inside isAnInstructionToStartNewRound method
         * (isAnInstructionToStartNewRound(row)) : true
         */
        //Arrange Statement(s)
        target = new RoundManager(searchEngineMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        //Act Statement(s)
        target.process("InitGame");
        Assertions.assertNotNull(target);
    }

    //Sapient generated method id: ${23af9c92-6208-3c94-bbb9-8124f03fe8b5}, hash: 6C4530224368711B25780EB22C1A47A7
    @Disabled()
    @Test()
    void processWhenRoundGetPlayersGetPlayerCodeIsNotNull() {
        /* Branches:
         * (text.contains(INIT_ROUND_STEP.getLabel())) : false  #  inside isAnInstructionToStartNewRound method
         * (isAnInstructionToStartNewRound(row)) : false
         * (text.contains(NEW_PLAYER_STEP.getLabel())) : true  #  inside isAnInstructionToRegisterNewPlayer method
         * (isAnInstructionToRegisterNewPlayer(row)) : true
         * (round.getPlayers().get(playerCode) != null) : true  #  inside registerNewPlayer method
         */
        //Arrange Statement(s)
        target = new RoundManager(searchEngineMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        target.setRound(roundMock);
        doReturn("return_of_search1").when(searchEngineMock).search("row1", "ClientUserinfoChanged\\:\\s(\\d+)");
        doReturn("return_of_search1").when(searchEngineMock).search("row1", "\\sn\\\\([\\w\\W]+)\\\\t\\\\");
        //Act Statement(s)
        target.process("row1");
        //Assert statement(s)
        assertAll("result", () -> {
            verify(searchEngineMock).search("row1", "ClientUserinfoChanged\\:\\s(\\d+)");
            verify(searchEngineMock).search("row1", "\\sn\\\\([\\w\\W]+)\\\\t\\\\");
        });
    }

    //Sapient generated method id: ${f8dfe07a-b282-3ad0-b723-37bfd5ccfc15}, hash: 6DE00C615B76B3DFDDE5AB6693896F4E
    @Test()
    void processWhenKillerCodeEqualsKilledPlayerCode() {
        /* Branches:
         * (text.contains(INIT_ROUND_STEP.getLabel())) : false  #  inside isAnInstructionToStartNewRound method
         * (isAnInstructionToStartNewRound(row)) : false
         * (text.contains(NEW_PLAYER_STEP.getLabel())) : false  #  inside isAnInstructionToRegisterNewPlayer method
         * (isAnInstructionToRegisterNewPlayer(row)) : false
         * (text.contains(KILL_STEP.getLabel())) : true  #  inside isAnInstructionToRegisterNewDeath method
         * (isAnInstructionToRegisterNewDeath(row)) : true
         * (killerCode.equals(WORLD_PLAYER_CODE_STEP.getLabel())) : false  #  inside collectScore method
         * (killerCode.equals(killedPlayerCode)) : true  #  inside collectScore method
         */
        //Arrange Statement(s)
        target = new RoundManager(searchEngineMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(1).when(roundMock).getKillsQuantity();
        doNothing().when(roundMock).setKillsQuantity(2);
        doReturn("A").when(searchEngineMock).search("Kill", "Kill:\\s(\\d+)");
        doReturn("A").when(searchEngineMock).search("Kill", "Kill:\\s\\d+\\s(\\d+)");
        Player playerMock = mock(Player.class);
        Map<String, Player> stringPlayerMap = new HashMap<>();
        stringPlayerMap.put("A", playerMock);
        doReturn(2).when(playerMock).getKillsQuantity();
        doNothing().when(playerMock).setKillsQuantity(1);
        Map<String, Player> stringPlayerMap2 = new HashMap<>();
        doReturn(stringPlayerMap, stringPlayerMap2).when(roundMock).getPlayers();
        //Act Statement(s)
        target.process("Kill");
        //Assert statement(s)
        assertAll("result", () -> {
            verify(roundMock).getKillsQuantity();
            verify(roundMock).setKillsQuantity(2);
            verify(searchEngineMock).search("Kill", "Kill:\\s(\\d+)");
            verify(searchEngineMock).search("Kill", "Kill:\\s\\d+\\s(\\d+)");
            verify(roundMock, times(2)).getPlayers();
            verify(playerMock).getKillsQuantity();
            verify(playerMock).setKillsQuantity(1);
        });
    }

    //Sapient generated method id: ${2a24ea0c-0776-3834-ba8b-025878194585}, hash: 646A5FB84D805ED312C92C036A636B79
    @Test()
    void processWhenKillerCodeNotEqualsKilledPlayerCode() {
        /* Branches:
         * (text.contains(INIT_ROUND_STEP.getLabel())) : false  #  inside isAnInstructionToStartNewRound method
         * (isAnInstructionToStartNewRound(row)) : false
         * (text.contains(NEW_PLAYER_STEP.getLabel())) : false  #  inside isAnInstructionToRegisterNewPlayer method
         * (isAnInstructionToRegisterNewPlayer(row)) : false
         * (text.contains(KILL_STEP.getLabel())) : true  #  inside isAnInstructionToRegisterNewDeath method
         * (isAnInstructionToRegisterNewDeath(row)) : true
         * (killerCode.equals(WORLD_PLAYER_CODE_STEP.getLabel())) : false  #  inside collectScore method
         * (killerCode.equals(killedPlayerCode)) : false  #  inside collectScore method
         */
        //Arrange Statement(s)
        target = new RoundManager(searchEngineMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        doReturn(1).when(roundMock).getKillsQuantity();
        doNothing().when(roundMock).setKillsQuantity(2);
        doReturn("A").when(searchEngineMock).search("Kill", "Kill:\\s(\\d+)");
        doReturn("B").when(searchEngineMock).search("Kill", "Kill:\\s\\d+\\s(\\d+)");
        Player playerMock = mock(Player.class);
        Map<String, Player> stringPlayerMap = new HashMap<>();
        stringPlayerMap.put("A", playerMock);
        doReturn(1).when(playerMock).getKillsQuantity();
        doNothing().when(playerMock).setKillsQuantity(2);
        Map<String, Player> stringPlayerMap2 = new HashMap<>();
        doReturn(stringPlayerMap, stringPlayerMap2).when(roundMock).getPlayers();
        //Act Statement(s)
        target.process("Kill");
        //Assert statement(s)
        assertAll("result", () -> {
            verify(roundMock).getKillsQuantity();
            verify(roundMock).setKillsQuantity(2);
            verify(searchEngineMock).search("Kill", "Kill:\\s(\\d+)");
            verify(searchEngineMock).search("Kill", "Kill:\\s\\d+\\s(\\d+)");
            verify(roundMock, times(2)).getPlayers();
            verify(playerMock).getKillsQuantity();
            verify(playerMock).setKillsQuantity(2);
        });
    }

    //Sapient generated method id: ${8bee1e38-69ae-3afe-ba84-31448373f718}, hash: 782CA26F6BBDB92A73BAEC3F1CD626BE
    @Test()
    void processWhenIsAnInstructionToFinishTheRoundNotRow() {
        /* Branches:
         * (text.contains(INIT_ROUND_STEP.getLabel())) : false  #  inside isAnInstructionToStartNewRound method
         * (isAnInstructionToStartNewRound(row)) : false
         * (text.contains(NEW_PLAYER_STEP.getLabel())) : false  #  inside isAnInstructionToRegisterNewPlayer method
         * (isAnInstructionToRegisterNewPlayer(row)) : false
         * (text.contains(KILL_STEP.getLabel())) : false  #  inside isAnInstructionToRegisterNewDeath method
         * (isAnInstructionToRegisterNewDeath(row)) : false
         * (isRoundStarted()) : false  #  inside isAnInstructionToFinishTheRound method
         * (isAnInstructionToFinishTheRound(row)) : false
         */
        //Arrange Statement(s)
        target = new RoundManager(searchEngineMock);
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
        //Act Statement(s)
        target.process("A");
        Assertions.assertNotNull(target);
    }
}
