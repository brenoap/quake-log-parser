package cloudwalk.quakeparser.config;

import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.plugins.Docket;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.hamcrest.Matchers.is;

@Timeout(value = 5)
class SwaggerUnitTest {

    //Sapient generated method id: ${efdd2d59-2261-34da-8caf-e8acff7f9c01}, hash: EE31687438517E2EA7903F1EEDDBEF1A
    @Test()
    void productApiTest() {
        //Arrange Statement(s)
        Swagger target = new Swagger();

        //Act Statement(s)
        Docket result = target.productApi();

        //Assert statement(s)
        assertAll("result", () -> assertThat(result, is(notNullValue())));
    }
}
