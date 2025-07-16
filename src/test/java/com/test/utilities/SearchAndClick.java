package com.test.utilities;

import org.openqa.selenium.WebElement;

public class SearchAndClick {
	public void search(WebElement element, String value) {
		element.sendKeys(value);
	}
	
	public void clickFunc(WebElement element) {
		element.click();
	}
}
