package example.weather;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import example.helper.FileLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@SpringBootTest
@WireMockTest(httpPort = 8089)
public class WeatherClientIntegrationTest {

    @Autowired
    private WeatherClient subject;

    @Test
    public void shouldCallWeatherService() throws Exception {
        stubFor(get(urlEqualTo("/data/2.5/weather?q=Hamburg,de&appid=someAppId"))
                .willReturn(aResponse()
                        .withBody(FileLoader.read("classpath:weatherApiResponse.json"))
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)));

        var weatherResponse = subject.fetchWeather();

        var expectedResponse = Optional.of(new WeatherResponse("raining", "a light drizzle"));
        assertThat(weatherResponse, is(expectedResponse));
    }
}
