package example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryIntegrationTest {

    @Autowired
    private PersonRepository subject;

    @Test
    public void shouldSaveAndFetchPerson() throws Exception {
        Person ham = new Person("Ham", "Vocke");
        subject.save(ham);

        Optional<Person> maybeHam = subject.findByLastName("Vocke");

        assertThat(maybeHam.isPresent(), is(true));
        assertThat(maybeHam.get().firstName, is("Ham"));
    }
}