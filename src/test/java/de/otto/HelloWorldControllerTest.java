package de.otto;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HelloWorldControllerTest {

    private final HelloWorldController subject = new HelloWorldController();

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        assertThat(subject.hello(), is("Hello World!"));
    }

}