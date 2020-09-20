package example.weather;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class WeatherClientTest {

    private WeatherClient subject;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        subject = new WeatherClient(restTemplate, "http://localhost:8089", "someAppId");
    }

    @Test
    public void shouldCallWeatherService() throws Exception {
        WeatherResponse expectedResponse = new WeatherResponse("raining", "a light drizzle");
        given(restTemplate.getForObject("http://localhost:8089/data/2.5/weather?q=Hamburg,de&appid=someAppId", WeatherResponse.class))
                .willReturn(expectedResponse);

        Optional<WeatherResponse> actualResponse = subject.fetchWeather();

        assertThat(actualResponse, is(Optional.of(expectedResponse)));
    }

    @Test
    public void shouldReturnEmptyOptionalIfWeatherServiceIsUnavailable() throws Exception {
        given(restTemplate.getForObject("http://localhost:8089/data/2.5/weather?q=Hamburg,de&appid=someAppId", WeatherResponse.class))
                .willThrow(new RestClientException("something went wrong"));

        Optional<WeatherResponse> actualResponse = subject.fetchWeather();

        assertThat(actualResponse, is(Optional.empty()));

    }

}