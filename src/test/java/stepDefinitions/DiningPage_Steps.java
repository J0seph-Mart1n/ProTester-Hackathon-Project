package stepDefinitions;


import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.test.factory.BaseClass;
import com.test.objectRepository.DistrictDiningSearch;
import com.test.resources.Dining;
import com.test.utilities.ExcelReaderWrite;

import io.cucumber.java.en.*;

public class DiningPage_Steps {
	private WebDriver driver;
    private DistrictDiningSearch diningPage;
    private List<Dining> diningInfo;

    @When("The user search for café {string}")
    public void searchCafe(String cafeName) throws InterruptedException {
    	driver = BaseClass.getDriver();
    	driver.navigate().to("https://www.district.in/");
        diningPage = new DistrictDiningSearch(driver);
        diningInfo = diningPage.getCafeInfo(cafeName);
    }

    @Then("Print the café’s name, rate, price, time, and address to console")
    public void printCafeInfo() {
        // All printing is already inside getCafeInfo()
    	BaseClass.setDriver(driver);
    }
    
    @Then("Write dining data to Excel sheet")
    public void writeDataToExcel() throws IOException {
    	String excelPath  = "src/test/resources/testdata/ExcelData.xlsx";
        String sheetName  = "Dining Details";

        // write the Dining Details into the sheet
        ExcelReaderWrite.writeDining(excelPath, sheetName, diningInfo);
        System.out.println("✅ Dining Details written to Excel at " + excelPath);
    }
}
