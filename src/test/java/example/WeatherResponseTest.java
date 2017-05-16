package example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static example.WeatherResponse.weatherResponse;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class WeatherResponseTest {
    @Test
    public void shouldDeserializeJson() throws Exception {
        String jsonResponse = "{\n" +
                "\"coord\": {\n" +
                "\"lon\": 145.77,\n" +
                "\"lat\": -16.92\n" +
                "},\n" +
                "\"weather\": [\n" +
                "{\n" +
                "\"id\": 802,\n" +
                "\"main\": \"Clouds\",\n" +
                "\"description\": \"scattered clouds\",\n" +
                "\"icon\": \"03n\"\n" +
                "}\n" +
                "],\n" +
                "\"base\": \"stations\",\n" +
                "\"main\": {\n" +
                "\"temp\": 300.15,\n" +
                "\"pressure\": 1007,\n" +
                "\"humidity\": 74,\n" +
                "\"temp_min\": 300.15,\n" +
                "\"temp_max\": 300.15\n" +
                "},\n" +
                "\"visibility\": 10000,\n" +
                "\"wind\": {\n" +
                "\"speed\": 3.6,\n" +
                "\"deg\": 160\n" +
                "},\n" +
                "\"clouds\": {\n" +
                "\"all\": 40\n" +
                "},\n" +
                "\"dt\": 1485790200,\n" +
                "\"sys\": {\n" +
                "\"type\": 1,\n" +
                "\"id\": 8166,\n" +
                "\"message\": 0.2064,\n" +
                "\"country\": \"AU\",\n" +
                "\"sunrise\": 1485720272,\n" +
                "\"sunset\": 1485766550\n" +
                "},\n" +
                "\"id\": 2172797,\n" +
                "\"name\": \"Cairns\",\n" +
                "\"cod\": 200\n" +
                "}";


        WeatherResponse expectedResponse = weatherResponse().description("scattered clouds").build();
        WeatherResponse parsedResponse = new ObjectMapper().readValue(jsonResponse, WeatherResponse.class);
        assertThat(parsedResponse, is(expectedResponse));
    }


}