package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features= {
							  "./src/test/resources/features/Test1_HomePage.feature",
							  "./src/test/resources/features/Test2_ActivityPage.feature",
							  "./src/test/resources/features/Test3_MoviePage.feature",
							  "./src/test/resources/features/Test4_SignInPage.feature",
						   },
				 glue={"stepDefinitions"})
public class TestRun extends AbstractTestNGCucumberTests{

}