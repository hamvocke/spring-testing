package example.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private List<Weather> weather;

    public WeatherResponse() {}

    public WeatherResponse(String main, String description) {
        this.weather = Collections.singletonList(new Weather(main, description));
    }

    public String getSummary() {
        return weather.stream()
                .map(w -> w.main + ": " + w.description)
                .collect(Collectors.joining("\n"));
    }

    public List<Weather> getWeather() {
        return weather;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherResponse that = (WeatherResponse) o;
        return Objects.equals(weather, that.weather);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weather);
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "weather=" + weather +
                '}';
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather {
        private String main;
        private String description;

        public Weather() {}

        public Weather(String main, String description) {
            this.main = main;
            this.description = description;
        }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Weather weather = (Weather) o;
            return Objects.equals(main, weather.main) &&
                    Objects.equals(description, weather.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(main, description);
        }

        @Override
        public String toString() {
            return "Weather{" +
                    "main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
