package com.test.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.objectRepository.DistrictActivitiesPage;
import com.test.objectRepository.DistrictHomePage;
import com.test.objectRepository.DistrictMoviePage;
import com.test.objectRepository.DistrictSignInPage;
import com.test.utilities.DriverSetup;

public class Main {
	WebDriver driver;
	
	@BeforeClass
	@Parameters("browser")
	public void setUp(String browser) throws Exception {
		// Instantiate driver
		driver = DriverSetup.getDriver(browser);
		System.out.println("Opened the URL in the browser");
	}
	
	@Test(priority=1)
	public void districtHomePage() {
		DistrictHomePage home = new DistrictHomePage(driver);
		home.clickLocation();
		home.clickPune();
	}
	
	@Test(priority=2)
	public void districtActivitiesPage() {
		DistrictActivitiesPage activity = new DistrictActivitiesPage(driver);
		activity.clickActivitiesPage();
		activity.printActivites();
	}
	
	@Test(priority=3)
	public void districtMoviePage() {
		DistrictMoviePage movie = new DistrictMoviePage(driver);
		movie.printMovieLanguage();
	}
	
	@Test(priority=4)
	public void districtSignIn() {
		DistrictSignInPage signIn = new DistrictSignInPage(driver);
		signIn.validateSignIn();
	}
	
	
	@AfterClass
	public void tearDown() {
		if(driver!=null) {
			DriverSetup.driverTearDown();
		}
	}
}

