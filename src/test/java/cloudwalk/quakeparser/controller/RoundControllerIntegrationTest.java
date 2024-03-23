package cloudwalk.quakeparser.controller;

import cloudwalk.quakeparser.controller.json.RoundDetailJsonResponse;
import cloudwalk.quakeparser.controller.json.RoundDetailJsonResponseMapper;
import cloudwalk.quakeparser.domain.Round;
import cloudwalk.quakeparser.usecase.RoundReportGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockStatic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Timeout(value = 5)
@WebMvcTest()
@ContextConfiguration(classes = RoundController.class)
class RoundControllerIntegrationTest {

    @Autowired()
    private MockMvc mockMvc;

    @MockBean(name = "roundReportGenerator")
    private RoundReportGenerator roundReportGeneratorMock;

    private AutoCloseable autoCloseableMocks;

    @BeforeEach()
    public void beforeTest() {
        autoCloseableMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach()
    public void afterTest() throws Exception {
        if (autoCloseableMocks != null)
            autoCloseableMocks.close();
    }

    //Sapient generated method id: ${b26aa3ee-ec49-3a54-983e-ecb4f0fff085}, hash: 071366CB32026E588B8BCA88559F3EF1
    @Test()
    void getRoundReportTest() throws Exception {
        //Arrange Statement(s)
        try (MockedStatic<RoundDetailJsonResponseMapper> roundDetailJsonResponseMapper = mockStatic(RoundDetailJsonResponseMapper.class)) {
            List<RoundDetailJsonResponse> roundDetailJsonResponseList = new ArrayList<>();
            roundDetailJsonResponseMapper.when(() -> RoundDetailJsonResponseMapper.toRoundsDetailJsonResponse(anyList())).thenReturn(roundDetailJsonResponseList);
            List<Round> roundList = new ArrayList<>();
            doReturn(roundList).when(roundReportGeneratorMock).generate();
            //Act Statement(s)
            ResultActions resultActions = this.mockMvc.perform(get("/api/report").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
            ResponseEntity<List<RoundDetailJsonResponse>> responseEntity = new ResponseEntity<>(roundDetailJsonResponseList, HttpStatus.OK);
            //Assert statement(s)
            assertAll("result", () -> resultActions.andExpect(status().is(responseEntity.getStatusCode().value())));
        }
    }

    @SpringBootApplication(scanBasePackageClasses = RoundController.class)
    static class RoundControllerSapientGeneratedWebMvcTestConfig {
    }
}
