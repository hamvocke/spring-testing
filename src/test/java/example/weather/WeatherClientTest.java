package example.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class WeatherClientTest {

    private WeatherClient subject;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() throws Exception {
        subject = new WeatherClient(restTemplate, "http://localhost:8089", "someAppId");
    }

    @Test
    public void shouldCallWeatherService() throws Exception {
        var expectedResponse = new WeatherResponse("raining", "a light drizzle");
        given(restTemplate.getForObject("http://localhost:8089/data/2.5/weather?q=Hamburg,de&appid=someAppId", WeatherResponse.class))
                .willReturn(expectedResponse);

        var actualResponse = subject.fetchWeather();

        assertThat(actualResponse, is(Optional.of(expectedResponse)));
    }

    @Test
    public void shouldReturnEmptyOptionalIfWeatherServiceIsUnavailable() throws Exception {
        given(restTemplate.getForObject("http://localhost:8089/data/2.5/weather?q=Hamburg,de&appid=someAppId", WeatherResponse.class))
                .willThrow(new RestClientException("something went wrong"));

        var actualResponse = subject.fetchWeather();

        assertThat(actualResponse, is(Optional.empty()));
    }
}