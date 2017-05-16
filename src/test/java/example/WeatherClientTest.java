package example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import static example.WeatherResponse.weatherResponse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class WeatherClientTest {

    @Mock
    private RestTemplate restTemplate;

    private WeatherClient subject;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        subject = new WeatherClient(restTemplate);
    }

    @Test
    public void shouldCallYahooService() throws Exception {
        given(restTemplate.getForObject(any(), eq(WeatherResponse.class))).willReturn(weatherResponse().description("Hamburg, 8°C raining").build());

        subject.yesterdaysWeather();

        verify(restTemplate).getForObject("yahoo", WeatherResponse.class);
    }

}