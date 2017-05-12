package example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest
public class HelloWorldControllerComponentTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(content().string("Hello World!"));
    }

    @Test
    public void shouldReturnFullName() throws Exception {
        Person ham = new Person("Ham", "Vocke");
        given(personRepository.findByLastName("Vocke")).willReturn(Optional.of(ham));

        mockMvc.perform(get("/hello/Vocke"))
                .andExpect(content().string("Hello Ham Vocke!"));
    }
}
