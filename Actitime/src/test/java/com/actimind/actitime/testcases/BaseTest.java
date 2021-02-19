package com.actimind.actitime.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.actimind.actitime.generic.IAutoConstants;
import com.actimind.actitime.generic.Utility;
import com.actimind.actitime.generic.WebActionUtil;
import com.actimind.actitime.pages.HomePage;
import com.actimind.actitime.pages.LoginPage;

public class BaseTest implements IAutoConstants {
	public WebDriver driver;
	public WebActionUtil webActionUtil;
	public HomePage homePage;
	
	@Parameters({"browserName","appUrl","implicit","explicit"})
	@BeforeClass(alwaysRun = true)
	public void openApp(@Optional(DEFAULT_BROWSER)String browserName,
						@Optional(MAIN_URL)String appUrl,
						@Optional(ITO)String implicit,
						@Optional(ETO)String explicit) {
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty(CHROME_KEY, CHROME_PATH);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
		} else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty(GECKO_KEY, GECKO_PATH);
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("dom.webnotifications.enabled", false);
			driver = new FirefoxDriver(options);
		} else {
			throw new IllegalArgumentException("Only Chrome and Firefox is Supporter");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Long.parseLong(implicit), TimeUnit.SECONDS);
		driver.get(appUrl);
		webActionUtil = new WebActionUtil(driver, Long.parseLong(explicit));
	}
	
	@Parameters({"username","password"})
	@BeforeMethod(alwaysRun = true)
	public void login(@Optional(DEFAULT_USERNAME)String username,
					  @Optional(DEFAULT_PASSWORD)String password) {
		
		LoginPage loginPage = new LoginPage(driver, webActionUtil);
		homePage = loginPage.login(username, password);
		
	}
	

	@AfterMethod(alwaysRun = true)
	public void logout(ITestResult result) {
		String testCaseName=result.getName();
		if(result.getStatus()==ITestResult.FAILURE) {
			System.out.println(Utility.getScreenshot(driver, testCaseName));
		}
		homePage.logout();
	}
	
	@AfterClass(alwaysRun = true)
	public void closeApp() {
		driver.quit();
	}
	
}
