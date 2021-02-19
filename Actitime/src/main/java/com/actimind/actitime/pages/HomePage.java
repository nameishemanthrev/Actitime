package com.actimind.actitime.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.actimind.actitime.generic.WebActionUtil;

public class HomePage extends BasePage {
	
	@FindBy(xpath="//td[contains(@class,'navCell relative')]/a")
	private List<WebElement> menuLinksList;
	
	@FindBy(id="logoutLink")
	private WebElement logoutLink;
	
	public HomePage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	public BasePage clickOnMenuLink(String menuLink) {
		for(WebElement ele:menuLinksList) {
			if(ele.getText().equalsIgnoreCase(menuLink)) {
				webActionUtil.elementJSClick(ele);
				break;
			}
		}
		
		switch(menuLink) {
			case "Time-Track": return new TimeTrackPage(driver, webActionUtil);
			case "Users": return new UsersPage(driver, webActionUtil);
			case "Reports": return new ReportsPage(driver, webActionUtil);
			case "Tasks": return new TasksPage(driver, webActionUtil);
			default: return null;
		}
	}
	
	public void logout() {
		webActionUtil.elementJSClick(logoutLink);
	}
	
}
