package example.weather;

import com.intuit.karate.netty.FeatureServer;
import java.io.File;
import java.util.Optional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;

public class WeatherClientIntegrationKarateTest {
    
    private static WeatherClient subject;    
    
    @BeforeClass
    public static void beforeClass() {
        File file = new File("src/test/java/example/weather/weather-mock.feature");
        FeatureServer server = FeatureServer.start(file, 0, false, null);
        subject = new WeatherClient(new RestTemplateBuilder().build(), "http://localhost:" + server.getPort(), "some-test-api-key");
    }    
    
    @Test
    public void shouldCallWeatherService() throws Exception {
        Optional<WeatherResponse> weatherResponse = subject.fetchWeather();
        Optional<WeatherResponse> expectedResponse = Optional.of(new WeatherResponse("Rain"));
        assertThat(weatherResponse, is(expectedResponse));
    }
    
}