package example;

import example.person.Person;
import example.person.PersonRepository;
import example.weather.WeatherClient;
import example.weather.WeatherResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

public class ExampleControllerTest {

    private ExampleController subject;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private WeatherClient weatherClient;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        subject = new ExampleController(personRepository, weatherClient);
    }

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        assertThat(subject.hello(), is("Hello World!"));
    }

    @Test
    public void shouldReturnFullNameOfAPerson() throws Exception {
        Person peter = new Person("Peter", "Pan");
        given(personRepository.findByLastName("Pan")).willReturn(Optional.of(peter));

        var greeting = subject.hello("Pan");

        assertThat(greeting, is("Hello Peter Pan!"));
    }

    @Test
    public void shouldTellIfPersonIsUnknown() throws Exception {
        given(personRepository.findByLastName(anyString())).willReturn(Optional.empty());

        var greeting = subject.hello("Pan");

        assertThat(greeting, is("Who is this 'Pan' you're talking about?"));
    }

    @Test
    public void shouldReturnWeatherClientResult() throws Exception {
        WeatherResponse weatherResponse = new WeatherResponse("raining", "a light drizzle");
        given(weatherClient.fetchWeather()).willReturn(Optional.of(weatherResponse));

        var weather = subject.weather();

        assertThat(weather, is("raining: a light drizzle"));
    }

    @Test
    public void shouldReturnErrorMessageIfWeatherClientIsUnavailable() throws Exception {
        given(weatherClient.fetchWeather()).willReturn(Optional.empty());

        var weather = subject.weather();

        assertThat(weather, is("Sorry, I couldn't fetch the weather for you :("));
    }
}