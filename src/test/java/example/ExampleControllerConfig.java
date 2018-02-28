package example;

import example.person.Person;
import example.person.PersonRepository;
import example.weather.WeatherClient;
import example.weather.WeatherResponse;
import java.util.Optional;
import static org.mockito.BDDMockito.given;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.mockito.Mockito.*;

@Configuration
@EnableAutoConfiguration
public class ExampleControllerConfig {

    @Bean
    public ExampleController exampleController() {
        PersonRepository personRepository = mock(PersonRepository.class);
        Person peter = new Person("Peter", "Pan");
        given(personRepository.findByLastName("Pan")).willReturn(Optional.of(peter));
        WeatherClient weatherClient = mock(WeatherClient.class);
        WeatherResponse weatherResponse = new WeatherResponse("Hamburg, 8°C raining");
        given(weatherClient.fetchWeather()).willReturn(Optional.of(weatherResponse));
        return new ExampleController(personRepository, weatherClient);
    }

}
