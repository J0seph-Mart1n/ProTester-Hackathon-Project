package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import com.test.utilities.DriverSetup;

public class Hooks {
	
	static WebDriver driver;
	
	@BeforeAll
    public static void setup() throws Exception
    {
	    driver = DriverSetup.getDriver("Edge");
	    driver.get("https://www.district.in/");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	    driver.manage().window().maximize();
	}
	
    @AfterAll
    public static void tearDown() {
       driver.quit();
    }
}
