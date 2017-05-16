package example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static example.WeatherResponse.weatherResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
@TestPropertySource(locations= "classpath:application.properties")
public class WeatherClientTest {

    private WeatherClient subject;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        subject = new WeatherClient(restTemplate, "http://localhost:8089");
    }

    @Test
    public void shouldCallWeatherService() throws Exception {
        WeatherResponse expectedResponse = weatherResponse().description("Hamburg, 8°C raining").build();
        given(restTemplate.getForObject("http://localhost:8089", WeatherResponse.class)).willReturn(expectedResponse);

        WeatherResponse actualResponse = subject.yesterdaysWeather();

        assertThat(actualResponse, is(expectedResponse));
    }

}