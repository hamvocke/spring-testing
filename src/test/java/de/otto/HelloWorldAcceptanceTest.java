package de.otto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = DEFINED_PORT)
public class HelloWorldAcceptanceTest {

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        when()
                .get("http://localhost:8080/hello")
        .then()
                .statusCode(is(200))
                .body(containsString("Hello World!"));
    }
}
