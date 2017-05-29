package example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExampleFunctionalTest {

    protected WebDriver driver;


    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        driver.close();
    }


    @Test
    public void helloPageHasTextHelloWorld(){

        driver.get("http://127.0.0.1:8080/hello");

        assertThat(driver.findElement(By.tagName("body")).getText(), containsString("Hello World!"));

    }
}
