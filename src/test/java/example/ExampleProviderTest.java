//package example;
//
//import au.com.dius.pact.provider.spring.target.MockMvcTarget;
//import example.person.Person;
//import example.person.PersonRepository;
//import example.weather.WeatherClient;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//
///**
// *  A provider test for the contract between this service (as a provider) and
// *  a primitive consumer. The implementation of the consumer can be
// *  found under https://github.com/hamvocke/spring-testing-consumer
// */
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@Provider("person_provider")// same as in the "provider_name" part in our pact file
//@PactFolder("target/pacts") // tells pact where to load the pact files from
//public class ExampleProviderTest {
//
//    @Mock
//    private PersonRepository personRepository;
//
//    @Mock
//    private WeatherClient weatherClient;
//
//    private ExampleController exampleController;
//
//    @TestTarget
//    public final MockMvcTarget target = new MockMvcTarget();
//
//    @BeforeEach
//    public void before() {
//        MockitoAnnotations.openMocks(this);
//        exampleController = new ExampleController(personRepository, weatherClient);
//        target.setControllers(exampleController);
//    }
//
//    @State("person data") // same as the "given()" part in our consumer test
//    public void personData() {
//        var peterPan = new Person("Peter", "Pan");
//        when(personRepository.findByLastName("Pan")).thenReturn(Optional.of
//                (peterPan));
//    }
//}
