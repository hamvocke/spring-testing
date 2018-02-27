package example;

import com.intuit.karate.junit4.Karate;
import cucumber.api.CucumberOptions;
import example.person.Person;
import example.person.PersonRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@RunWith(Karate.class)
@CucumberOptions(features = "classpath:example/example-api.feature")
public class HelloE2ERestKarateTest {
    
    private static PersonRepository personRepository;
    
    @BeforeClass
    public static void beforeClass() {
        ConfigurableApplicationContext context = SpringApplication.run(ExampleApplication.class, new String[]{"--server.port=0"});
        personRepository = context.getBean(PersonRepository.class);
        String port = context.getEnvironment().getProperty("local.server.port");
        System.setProperty("example.server.port", port);
        System.setProperty("karate.env", "mock-e2e");
        Person peter = new Person("Peter", "Pan");
        personRepository.save(peter);        
    }
     
    @AfterClass
    public static void afterClass() {
        personRepository.deleteAll();
    }
        
}
