package com.actimind.actitime.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.actimind.actitime.generic.WebActionUtil;

public class BasePage {
	public WebDriver driver;
	public WebActionUtil webActionUtil;
	public BasePage(WebDriver driver, WebActionUtil webActionUtil) {
		this.driver=driver;
		this.webActionUtil=webActionUtil;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyTitle(String expectedTitle) {	
		return driver.getTitle().equals(expectedTitle);
	}
	
	public boolean verifyURL(String expectedURL) {
		webActionUtil.waitUntilUrl(expectedURL);
		return driver.getCurrentUrl().equals(expectedURL);
	}
}
