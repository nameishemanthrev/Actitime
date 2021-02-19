package com.actimind.actitime.generic;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebActionUtil {
	public WebDriver driver;
	public WebDriverWait wait;
	public JavascriptExecutor js;
	public Actions actions;
	public WebActionUtil(WebDriver driver, long ETO) {
		this.driver=driver;
		wait = new WebDriverWait(driver,ETO);
		js = (JavascriptExecutor) driver;
		actions = new Actions(driver);
	}
	
	public void enterKeys(WebElement target,String textToEnter) {
		target.clear();
		target.sendKeys(textToEnter);
	}
	
	public void staleElementClick(WebElement target) {
		wait.until(ExpectedConditions.elementToBeClickable(target));
		wait.until(ExpectedConditions.stalenessOf(target));
		target.click();
	}
	
	public void elementClick(WebElement target) {
		wait.until(ExpectedConditions.elementToBeClickable(target));
		target.click();
	}
	
	
	
	public void elementJSClick(WebElement target) {		
		js.executeScript("arguments[0].click();", target);
	}
	
	public void scrollIntoView(WebElement target) {
		js.executeScript("arguments[0].scrollIntoView(true)", target);
	}
	
	public void enterJsKeys(WebElement target, String textToEnter) {
		js.executeScript("arguments[0].value='';", target);
		js.executeScript("arguments[0].value='arguments[1]';",target, textToEnter);
	}
	
	public void scrollToTopOrDown(boolean top) {
		if(top) {
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		} else {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		}		
	}
	
	public void scrollDownOrUpPixels(int pixel, boolean top) {
		if(top) {
			js.executeScript("window.scrollBy(0, -arguments[0]);", pixel);
		} else {
			js.executeScript("window.scrollBy(0, arguments[0]);", pixel);
		}
	}
	
	public void dragAndDrop(WebElement source, WebElement target) {
		actions.dragAndDrop(source, target).perform();
	}
	
	public void moveToElement(WebElement target) {
		actions.moveToElement(target).perform();
	}
	
	public void selectVisibleText(WebElement targetListBox, String visibleText) {
		Select s = new Select(targetListBox);
		s.deselectByVisibleText(visibleText);
	}
	
	public void switchToFrame(String indexNameOrId) {
		try {
			int index=Integer.parseInt(indexNameOrId);
			driver.switchTo().frame(index);
		} catch(NumberFormatException e) {
			driver.switchTo().frame(indexNameOrId);
		}	
	}
	
	public void waitForExist(WebElement target, String text) {
		wait.until(ExpectedConditions.textToBePresentInElementValue(target, text));
	}

	public void waitUntilUrl(String expectedUrl) {
		wait.until(ExpectedConditions.urlContains(expectedUrl));		
	}
	
	public void scrollElementDown(WebElement target, int pixels) {
		actions.dragAndDropBy(target, 0, pixels).perform();
	}
	
	
}
