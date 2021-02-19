package com.actimind.actitime.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.actimind.actitime.generic.ExcelLibrary;
import com.actimind.actitime.pages.TasksPage;
import com.actimind.actitime.pages.TimeTrackPage;

public class TC001 extends BaseTest {
	String customerName;
	@Test
	public void testCreateTask() {
		
		String option = ExcelLibrary.getStringData(XL_PATH, "TC001", 1, 0);
		customerName = ExcelLibrary.getStringData(XL_PATH, "TC001", 1, 1);
		String projectName = ExcelLibrary.getStringData(XL_PATH, "TC001", 1, 2);
		String taskName = ExcelLibrary.getStringData(XL_PATH, "TC001", 1, 3);
		int estimateHours = (int)(double)ExcelLibrary.getNumericData(XL_PATH, "TC001", 1, 4);
		int deadlineDay = (int)(double)ExcelLibrary.getNumericData(XL_PATH, "TC001", 1, 5);
		
		TimeTrackPage timeTrackPage = (TimeTrackPage) homePage.clickOnMenuLink("Time-Track");
		timeTrackPage.addNewTask(option, customerName, projectName, taskName, estimateHours, deadlineDay);
		Assert.assertEquals(timeTrackPage.isTaskDisplayed("NASA Negotiations1"), true);
	}
	
	@AfterMethod
	public void deleleCustomer() {
		TasksPage tasksPage = (TasksPage) homePage.clickOnMenuLink("Tasks");
		tasksPage.deleteCustomer(customerName);
	}
}
