package example;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class ExampleFunctionalTest {

    @Test
    public void helloPageHasTextHelloWorld(){
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://127.0.0.1:8080/hello");

        assertThat(driver.findElement(By.tagName("body")).getText(), containsString("Hello World!"));

        driver.close();
    }
}
