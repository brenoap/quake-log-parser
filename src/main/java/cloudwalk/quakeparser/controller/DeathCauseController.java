package cloudwalk.quakeparser.controller;

import cloudwalk.quakeparser.controller.json.DeathDetailJsonResponse;
import cloudwalk.quakeparser.controller.json.DeathDetailJsonResponseMapper;
import cloudwalk.quakeparser.domain.Round;
import cloudwalk.quakeparser.usecase.RoundReportGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/death-cause-count")
@Api(tags = "DeathCauseCounterJsonResponse Report", produces = MediaType.APPLICATION_JSON_VALUE)
public class DeathCauseController {

    private final RoundReportGenerator roundReportGenerator;
    @Autowired
    public DeathCauseController(RoundReportGenerator roundReportGenerator) {
        this.roundReportGenerator = roundReportGenerator;
    }

    @ApiOperation(value = "Resource to count death cause through Quake round logs")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Death Report Success"),
                    @ApiResponse(code = 500, message = "Internal Server Error")
            })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DeathDetailJsonResponse>> getRoundReport() {

        final List<Round> rounds = roundReportGenerator.generateDeathCause();
        return new ResponseEntity<>(buildRoundsJson(rounds), HttpStatus.OK);
    }

    private List<DeathDetailJsonResponse> buildRoundsJson(final List<Round> rounds) {
        return DeathDetailJsonResponseMapper.toDeathsDetailJsonResponse(rounds);
    }
}
