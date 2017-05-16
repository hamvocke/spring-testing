package example.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private List<Weather> weather;

    public WeatherResponse() {}

    public WeatherResponse(List<Weather> weather) {
        this.weather = weather;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getDescription() {
        return weather.stream()
                .map(Weather::getDescription)
                .collect(Collectors.joining(", "));
    }

    public static WeatherResponseBuilder weatherResponse() {
        return new WeatherResponseBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherResponse that = (WeatherResponse) o;

        return weather != null ? weather.equals(that.weather) : that.weather == null;
    }

    @Override
    public int hashCode() {
        return weather != null ? weather.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "weather=" + weather +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {
        private String description;

        public String getDescription() {
            return description;
        }

        public Weather() {}

        public Weather(String description) {
            this.description = description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Weather weather = (Weather) o;

            return description != null ? description.equals(weather.description) : weather.description == null;
        }

        @Override
        public int hashCode() {
            return description != null ? description.hashCode() : 0;
        }

        @Override
        public String toString() {
            return "Weather{" +
                    "description='" + description + '\'' +
                    '}';
        }

    }

    public static class WeatherResponseBuilder {
        private String description;

        public WeatherResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public WeatherResponse build() {
            return new WeatherResponse(singletonList(new Weather(description)));
        }
    }
}
