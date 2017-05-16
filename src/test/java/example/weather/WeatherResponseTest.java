package example.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import example.helper.FileLoader;
import example.weather.WeatherResponse;
import org.junit.Test;

import static example.weather.WeatherResponse.weatherResponse;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class WeatherResponseTest {

    @Test
    public void shouldDeserializeJson() throws Exception {
        String jsonResponse = FileLoader.read("classpath:weatherApiResponse.json");
        WeatherResponse expectedResponse = weatherResponse().description("light intensity drizzle").build();

        WeatherResponse parsedResponse = new ObjectMapper().readValue(jsonResponse, WeatherResponse.class);

        assertThat(parsedResponse, is(expectedResponse));
    }
}