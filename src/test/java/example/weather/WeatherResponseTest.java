package example.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.helper.FileLoader;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WeatherResponseTest {

    @Test
    public void shouldDeserializeJson() throws Exception {
        var jsonResponse = FileLoader.read("classpath:weatherApiResponse.json");
        var expectedResponse = new WeatherResponse("raining", "a light drizzle");

        var parsedResponse = new ObjectMapper().readValue(jsonResponse, WeatherResponse.class);

        assertThat(parsedResponse, is(expectedResponse));
    }
}