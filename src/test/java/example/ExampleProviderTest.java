package example;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.MockMvcTestTarget;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import example.person.Person;
import example.person.PersonRepository;
import example.weather.WeatherClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 *  A provider test for the contract between this service (as a provider) and
 *  a primitive consumer. The implementation of the consumer can be
 *  found under https://github.com/hamvocke/spring-testing-consumer
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Provider("person_provider")// same as in the "provider_name" part in our pact file
@PactFolder("target/pacts") // tells pact where to load the pact files from
public class ExampleProviderTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private WeatherClient weatherClient;

    private ExampleController exampleController;

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @BeforeEach
    public void before(PactVerificationContext context) {
        MockitoAnnotations.openMocks(this);
        MockMvcTestTarget target = new MockMvcTestTarget();
        exampleController = new ExampleController(personRepository, weatherClient);
        target.setControllers(exampleController);
        context.setTarget(target);
    }

    @State("person data") // same as the "given()" part in our consumer test in https://github.com/hamvocke/spring-testing-consumer
    public void personData() {
        var peterPan = new Person("Peter", "Pan");
        when(personRepository.findByLastName("Pan"))
                .thenReturn(Optional.of(peterPan));
    }
}
