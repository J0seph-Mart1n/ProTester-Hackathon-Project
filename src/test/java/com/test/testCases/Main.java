package com.test.testCases;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.test.resources.Activity;
import com.test.resources.Movie;
import com.test.utilities.TimeParser;

public class Main {
	private String baseUrl = "https://www.district.in/";
	public WebDriver driver;
	public HashMap<String,String> list=new HashMap<String,String>();
	
	public WebDriver createDriver(){
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get(baseUrl);
		return driver;
	}
	
	public void getTopValues(WebDriver driver) throws InterruptedException{
		Thread.sleep(5000);
		//Click location
		driver.findElement(By.cssSelector(".dds-w-8.dds-h-8.dds-flex.dds-items-center.dds-justify-center")).click();
		Thread.sleep(5000);
		//Change location to Pune
		driver.findElement(By.xpath("//*[@id=\"page-content\"]/div[3]/div/div/div/div/div[2]/div[1]/div/div[10]/img")).click();
		Thread.sleep(5000);
		
		//Click activities tab
		driver.findElement(By.xpath("//a[text()='Activities']")).click();
		System.out.println("clicked activities");
		
		//Scrolling till the end of page
		System.out.println("scrolling");
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int i=0;
		do{
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			Thread.sleep(3000);
			i++;
		}while(i<10);
		
		//Storing all activities in a list
		List<WebElement> activities= driver.findElements(By.cssSelector("a.dds-h-full"));
		System.out.println("collecting elements");
		
		List<Activity> activityInfo = new ArrayList<>();
		
		for (WebElement act :activities) {
			String text = act.getText();
			String[] parts = text.split("\\R");
			String time = parts[0];
			String activity = parts[1];
			String location = parts[2];
			String price  = parts[3].replaceAll("[^\\d.]", ""); //Only taking digits from the price
			
			
		    double doublePrice = Double.parseDouble(price); //Parse double digit
		    
		    //Splitting date and time
		    String[] timeParts = time.split(",");
		    String datePart = timeParts[0].trim();
		    String timePart = timeParts[1].trim();

		    LocalDateTime startTime, endTime = null;
		    int defaultYear = 2025;
		    
		    //Parsing date and time from the string
		    if (datePart.contains("-")) {
		        String[] dateRange = datePart.split("-");
		        startTime = TimeParser.parseDateTime(dateRange[0].trim(), timePart, defaultYear);
		        endTime = TimeParser.parseDateTime(dateRange[1].trim(), timePart, defaultYear);
		    } else {
		        startTime = TimeParser.parseDateTime(datePart.trim(), timePart, defaultYear);
		    }
		    
		    //Adding to the list
		    activityInfo.add(new Activity(startTime, endTime, activity, location, doublePrice));
		    
		    
//			System.out.println("Time: " + parts[0]);
//			System.out.println("Activity: " + parts[1]);
//			System.out.println("Location: " + parts[2]);
//			System.out.println("Price: " + parts[3]);
		}
		
		//Filtering activities coming on weekends and sorted in lowest price
		List<Activity> weekendActivities = activityInfo.stream()
			    .filter(TimeParser::isHappeningOnWeekend)
			    .sorted(Comparator.comparingDouble(Activity::getPrice))
			    .collect(Collectors.toList());
		
		for (Activity act : weekendActivities) {
		    System.out.println(act);
		}

	}
	
	public void getMovieLanguages(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Movies']")).click();
		Thread.sleep(5000);
		List<WebElement> movies = driver.findElements(By.xpath("//*[@id=\"page-content\"]/section/div/div[1]/div[2]/div[2]/a"));
		
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
		
		for(Movie mov:movieList) {
			System.out.println(mov);
		}
		
	}
	
	public void validateSignIn(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath("//a[@href='/profile']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Continue']")).click();
		String errorMsg = driver.findElement(By.xpath("//div[@class='dds-p-4']/div[1]/div[2]/p")).getText();
		System.out.println(errorMsg);
	}
	
	public void closeDriver() {
		driver.quit();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main ui = new Main();
		WebDriver driver = ui.createDriver();
		try {
			ui.getTopValues(driver);
			ui.getMovieLanguages(driver);
			ui.validateSignIn(driver);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ui.closeDriver();

	}

}

