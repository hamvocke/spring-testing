package example;

import com.intuit.karate.http.HttpRequestBuilder;
import com.intuit.karate.junit4.Karate;
import com.intuit.karate.mock.servlet.MockHttpClient;
import cucumber.api.CucumberOptions;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@RunWith(Karate.class)
@CucumberOptions(features = "classpath:example/example-api.feature")
public class ExampleControllerAPIKarateTest {
    
    @BeforeClass
    public static void beforeClass() {
        System.setProperty("karate.env", "mock-servlet");
    }
    
    public static MockHttpClient getMock() {
        return new MockHttpClient() {
            ServletContext servletContext = new MockServletContext();
            @Override
            protected Servlet getServlet(HttpRequestBuilder hrb) {
                AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
                context.register(ExampleControllerConfig.class);
                context.setServletContext(servletContext);
                DispatcherServlet servlet = new DispatcherServlet(context);
                ServletConfig servletConfig = new MockServletConfig();
                try {
                    servlet.init(servletConfig);
                    return servlet;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            @Override
            protected ServletContext getServletContext() {
                return servletContext;
            }
        };        
    }

}
