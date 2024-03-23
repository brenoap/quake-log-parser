package cloudwalk.quakeparser.controller.exception;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;

@Timeout(value = 5)
class AppExceptionHandlerUnitTest {

    //Sapient generated method id: ${5980321b-6181-3764-a5e1-eeaf2fd37c9f}, hash: 9E9E4E8074035D29BD625EA834D6A4D6
    @Test()
    void handleRuntimeExceptionTest() {
        //Arrange Statement(s)
        AppExceptionHandler target = new AppExceptionHandler();

        //Act Statement(s)
        ResponseEntity<String> result = target.handleRuntimeException();
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<String> responseEntity = bodyBuilder.body("The server encountered an internal error or misconfiguration and was unable to complete your request.");

        //Assert statement(s)
        assertAll("result", () -> assertThat(result, equalTo(responseEntity)));
    }
}
