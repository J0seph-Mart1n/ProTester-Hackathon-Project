package stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictMoviePage;
import com.test.resources.Movie;
import com.test.utilities.ExcelReaderWrite;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MoviePage_Steps {
	
	WebDriver driver;
	DistrictMoviePage movie;
	List<Movie> movieList;
	
	@Given("the user opens the Movies page")
	public void open_the_movies_page() {
	    // Write code here that turns the phrase above into concrete actions
		driver = BaseClass.getDriver();
	    movie = new DistrictMoviePage(driver);
	    movie.clickMoviesPage();
	}
	@When("the system retrieves the languages for all available movies")
	public void getting_all_the_languages_of_movies_present() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	    movieList = movie.getMovieLanguage();
	}
	@Then("print the list of identified languages")
	public void print_all_the_languages_extracted() {
	    // Write code here that turns the phrase above into concrete actions
	    movie.printMovieLanguage(movieList);
	    BaseClass.setDriver(driver);
	}
	
	@Then("write the complete language list to an Excel file")
	public void writeListToExcel() throws IOException {
		String excelPath  = "src/test/resources/testdata/ExcelData.xlsx";
        String sheetName  = "Movies";

        // write the Activity list into the sheet
        ExcelReaderWrite.writeMovies(excelPath, sheetName, movieList);
        System.out.println("✅ Movies written to Excel at " + excelPath);
	}
}
