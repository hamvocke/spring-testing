package example;

import example.person.Person;
import example.person.PersonRepository;
import example.weather.WeatherClient;
import example.weather.WeatherResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static example.weather.WeatherResponse.weatherResponse;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExampleControllerTest {

    private ExampleController subject;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private WeatherClient weatherClient;


    @Before
    public void setUp() throws Exception {
        initMocks(this);
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

        String greeting = subject.hello("Pan");

        assertThat(greeting, is("Hello Peter Pan!"));
    }

    @Test
    public void shouldTellIfPersonIsUnknown() throws Exception {
        given(personRepository.findByLastName(anyString())).willReturn(Optional.empty());

        String greeting = subject.hello("Pan");

        assertThat(greeting, is("Who is this 'Pan' you're talking about?"));
    }

    @Test
    public void shouldCallWeatherClient() throws Exception {
        WeatherResponse weatherResponse = weatherResponse().description("Hamburg, 8°C raining").build();
        given(weatherClient.yesterdaysWeather()).willReturn(weatherResponse);

        String weather = subject.yesterdaysWeather();

        assertThat(weather, is("Hamburg, 8°C raining"));
    }
}