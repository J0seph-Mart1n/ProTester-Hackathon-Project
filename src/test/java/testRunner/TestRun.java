package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Test
@CucumberOptions(features= {
							  "./src/test/resources/features/",
						   },
				 glue={"stepDefinitions"},
				 plugin = {
					        "pretty",
					        "html:target/cucumber.html",
					        "json:target/allure-results/cucumber.json",
					        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
					    })

@Listeners({
    com.test.utilities.CustomListener.class,
    com.test.utilities.RetryTransformer.class
})

public class TestRun extends AbstractTestNGCucumberTests{

}