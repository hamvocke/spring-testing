package example.weather;

import com.intuit.karate.junit4.Karate;
import com.intuit.karate.netty.FeatureServer;
import cucumber.api.CucumberOptions;
import java.io.File;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Karate.class)
@CucumberOptions(features = "classpath:example/weather/weather.feature")
public class WeatherAcceptanceKarateTest {

    @BeforeClass
    public static void beforeClass() {
        File file = new File("src/test/java/example/weather/weather-mock.feature");
        FeatureServer server = FeatureServer.start(file, 0, false, null);
        System.setProperty("karate.env", "mock-weather");
        System.setProperty("weather.server.port", server.getPort() + "");
    }

}
