package com.actimind.actitime.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.actimind.actitime.generic.Utility;
import com.actimind.actitime.generic.WebActionUtil;

public class TimeTrackPage extends BasePage {
	
	@FindBy(xpath="//span[text()='New']")
	private WebElement newLink;
	
	@FindBy(xpath="//div[contains(@class,'customerSelector')]")
	private WebElement selectCustomerListBox;
	
	@FindBy(xpath="//div[contains(@class,'customerSelector')]//div[contains(@class,'itemRow')]")
	private List<WebElement> customerOptionsList;
	
	@FindBy(xpath="//input[@placeholder='Enter Customer Name']")
	private WebElement customerNameTextField;
	
	@FindBy(xpath="//input[@placeholder='Enter Project Name']")
	private WebElement projectNameTextField;
	
	@FindBy(xpath="//input[@placeholder='Enter task name']")
	private WebElement taskNameTextField;
	
	@FindBy(xpath="//input[@placeholder='not needed']")
	private WebElement estimateHoursTextField;
	
	@FindBy(xpath="//button[text()='set deadline']")
	private WebElement setDeadlineButton;
	
	@FindBy(xpath="//td[contains(@class,'x-date-active')]//span[text()]")
	private List<WebElement> selectDateInCalendar;
	
	@FindBy(xpath="//div[text()='Create Tasks']")
	private WebElement createTaskButton;
	
	@FindBy(xpath = "//div[@class='task']")
	private List<WebElement> desiredTasksList;
	
	public TimeTrackPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	public void selectOptionInCustomerListBox(String option) {
		webActionUtil.elementClick(selectCustomerListBox);
		for(WebElement ele:customerOptionsList) {
			if(ele.getText().contains(option)) {
				webActionUtil.elementClick(ele);
				break;
			}
		}
	}
	
	public void selectDeadlineDate(String deadlineDate) {
		webActionUtil.elementClick(setDeadlineButton);
		for(WebElement ele:selectDateInCalendar) {
			if(ele.getText().equals(deadlineDate)) {
				webActionUtil.elementClick(ele);
				break;
			}
		}
	}
	
	public void addNewTask(String option, String customerName, String projectName, String taskName,
						   int estimateHours, int deadlineDate) {
		Utility.sleepInSeconds(5);
		webActionUtil.elementJSClick(newLink);
		selectOptionInCustomerListBox(option);
		webActionUtil.enterKeys(customerNameTextField, customerName);
		webActionUtil.enterKeys(projectNameTextField, projectName);
		webActionUtil.enterKeys(taskNameTextField, taskName);
		webActionUtil.enterKeys(estimateHoursTextField, estimateHours+"");
		selectDeadlineDate(deadlineDate+"");
		webActionUtil.elementClick(createTaskButton);
	}
	
	public boolean isTaskDisplayed(String taskName) {
		Utility.sleepInSeconds(10);
		for(WebElement ele:desiredTasksList) {
			if(ele.getText().equalsIgnoreCase(taskName)) {
				return true;
			}
		}
		return false;
	}
	
}
