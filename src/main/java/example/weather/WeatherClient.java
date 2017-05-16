package example.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherClient {

    private final RestTemplate restTemplate;
    private final String weatherServiceUrl;

    @Autowired
    public WeatherClient(final RestTemplate restTemplate,
                         @Value("${weather.url}") final String weatherServiceUrl) {
        this.restTemplate = restTemplate;
        this.weatherServiceUrl = weatherServiceUrl;
    }

    public WeatherResponse yesterdaysWeather() {
        return restTemplate.getForObject(weatherServiceUrl, WeatherResponse.class);
    }
}
