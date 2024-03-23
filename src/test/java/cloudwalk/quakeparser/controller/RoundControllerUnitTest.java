package cloudwalk.quakeparser.controller;

import cloudwalk.quakeparser.controller.json.RoundDetailJsonResponse;
import cloudwalk.quakeparser.controller.json.RoundDetailJsonResponseMapper;
import cloudwalk.quakeparser.domain.Round;
import cloudwalk.quakeparser.usecase.RoundReportGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.MockedStatic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@Timeout(value = 5)
class RoundControllerUnitTest {

    private final RoundReportGenerator roundReportGeneratorMock = mock(RoundReportGenerator.class, "roundReportGenerator");

    //Sapient generated method id: ${b26aa3ee-ec49-3a54-983e-ecb4f0fff085}, hash: C7879135E4DB9E64C2CF7257D8BA0623
    @Test()
    void getRoundReportTest() {
        //Arrange Statement(s)
        try (MockedStatic<RoundDetailJsonResponseMapper> roundDetailJsonResponseMapper = mockStatic(RoundDetailJsonResponseMapper.class)) {
            List<RoundDetailJsonResponse> roundDetailJsonResponseList = new ArrayList<>();
            roundDetailJsonResponseMapper.when(() -> RoundDetailJsonResponseMapper.toRoundsDetailJsonResponse(anyList())).thenReturn(roundDetailJsonResponseList);
            RoundController target = new RoundController(roundReportGeneratorMock);
            List<Round> roundList = new ArrayList<>();
            doReturn(roundList).when(roundReportGeneratorMock).generate();
            //Act Statement(s)
            ResponseEntity<List<RoundDetailJsonResponse>> result = target.getRoundReport();
            ResponseEntity<List<RoundDetailJsonResponse>> responseEntity = new ResponseEntity<>(roundDetailJsonResponseList, HttpStatus.OK);
            //Assert statement(s)
            assertAll("result", () -> {
                assertThat(result, equalTo(responseEntity));
                roundDetailJsonResponseMapper.verify(() -> RoundDetailJsonResponseMapper.toRoundsDetailJsonResponse(anyList()));
                verify(roundReportGeneratorMock).generate();
            });
        }
    }
}
