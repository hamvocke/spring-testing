package example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class HelloWorldControllerTest {

    private HelloWorldController subject;

    @Mock
    private PersonRepository personRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        subject = new HelloWorldController(personRepository);
    }

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        assertThat(subject.hello(), is("Hello World!"));
    }

    @Test
    public void shouldReturnFullNameOfAPerson() throws Exception {
        Person ham = new Person("Ham", "Vocke");
        given(personRepository.findByLastName("Vocke")).willReturn(Optional.of(ham));

        String greeting = subject.hello("Vocke");

        assertThat(greeting, is("Hello Ham Vocke!"));
    }

    @Test
    public void shouldTellIfPersonIsUnknown() throws Exception {
        given(personRepository.findByLastName(anyString())).willReturn(Optional.empty());

        String greeting = subject.hello("Vocke");

        assertThat(greeting, is("Who is this 'Vocke' you're talking about?"));
    }

}