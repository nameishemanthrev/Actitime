package com.actimind.actitime.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.actimind.actitime.generic.WebActionUtil;

public class LoginPage extends BasePage {
	
	@FindBy(id="username")private WebElement usernameTextFiled;
	@FindBy(name="pwd")private WebElement passwordTextFiled;
	@FindBy(id="loginButton")private WebElement loginButton;
	
	
	
	public LoginPage(WebDriver driver, WebActionUtil webActionUtil) {
		super(driver,webActionUtil);
	}
	
	public HomePage login(String username, String password) {
		webActionUtil.enterKeys(usernameTextFiled, username);
		webActionUtil.enterKeys(passwordTextFiled, password);
		webActionUtil.elementClick(loginButton);
		return new HomePage(driver, webActionUtil);
	}
}
