package com.test.objectRepository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.resources.Movie;

public class DistrictMoviePage {
	WebDriver driver;
	WebDriverWait wait;
	
	//Constructor
	public DistrictMoviePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	//Locators
	@FindBy(xpath = "//a[text()='Movies']") WebElement moviesBtn;
	
	@FindBy(xpath = "//*[@id='page-content']/section/div[1]/div[1]/div[2]/div[1]/div[2]/a") List<WebElement> movies;
	
	//Actions
	public void clickMoviesPage() {
		//Moves to Movies Page
		wait.until(ExpectedConditions.elementToBeClickable(moviesBtn)).click();
	}
	
	public List<Movie> getMovieLanguage(){
		
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id='page-content']/section/div[1]/div[1]/div[2]/div[1]/div[2]/a")));
		
		List<Movie> movieList = new ArrayList<>();
		
		System.out.println("Total movies: " + movies.size());
		
		for(WebElement movie:movies) {
			String movieInfo = movie.getText();
			String[] movieParts = movieInfo.split("\\R");
			
			String movieName = movieParts[0];
			String[] movieParts2 = movieParts[1].split(" ");
			String rating = movieParts2[0].trim();
			String language = movieParts2[2].trim();
			
			movieList.add(new Movie(movieName, language, rating));	
		}
		
		return movieList;
	}
	
	public void printMovieLanguage(List<Movie> movieList) {
		for(Movie mov:movieList) {
			System.out.println(mov);
		}
	}
}
