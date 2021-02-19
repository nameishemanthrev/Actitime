package com.actimind.actitime.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.actimind.actitime.generic.Utility;
import com.actimind.actitime.generic.WebActionUtil;

public class TasksPage extends BasePage {
	
	String xpath ="//div[@class='title']/div[text()='customerName']/../../div[@class='editButton']";
	
	@FindBy(xpath = "//div[contains(@class,'customerNode')]//div[@class='text']")
	private List<WebElement> customerNamesList;
	
	@FindBy(xpath="//div[@class='editCustomerPanelHeader']//div[text()='ACTIONS']")
	private WebElement actionsButton;
	
	@FindBy(xpath="//div[@class='taskManagement_customerPanel']//div[text()='Delete']")
	private WebElement deleteIcon;
	
	@FindBy(xpath="//span[text()='Delete permanently']")
	private WebElement deletePermanentlyButton;
	
	public TasksPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	public void mouseHoverAndClickOnCustSettingsIcon(String customerName) {
		
		for(WebElement ele:customerNamesList) {
			if(ele.getText().contains(customerName)) {
				webActionUtil.elementJSClick(ele);
				break;
			}
		}
		
		WebElement ele=driver.findElement(By.xpath(xpath.replace("customerName", customerName)));
		webActionUtil.elementJSClick(ele);		
	}
	
	public void deleteCustomer(String customerName) {
		mouseHoverAndClickOnCustSettingsIcon(customerName);
		Utility.sleepInSeconds(5);
		webActionUtil.elementJSClick(actionsButton);
		webActionUtil.elementClick(deleteIcon);
		webActionUtil.elementJSClick(deletePermanentlyButton);
	}
}
