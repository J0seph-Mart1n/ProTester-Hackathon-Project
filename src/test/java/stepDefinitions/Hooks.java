package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;

import com.test.factory.BaseClass;


public class Hooks {
	
	static WebDriver driver;
	
	@BeforeAll
    public static void setup() throws Exception
    {
		String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
	    driver = BaseClass.initilizeBrowser(browser);
	    driver.get("https://www.district.in/");
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	    driver.manage().window().maximize();
	}
	
    @AfterAll
    public static void tearDown() {
       driver.quit();
    }
}
