package com.test.utilities;

import java.io.File;
import java.nio.file.Path;
import java.time.Instant;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.test.automation.drivers.WebDriverHelper;
import com.test.automation.step_definitions.TestHooks;
import com.test.enums.*;
import static com.test.utilities.FinalProperties.*;
import cucumber.api.Scenario;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebActions {

	protected static RemoteWebDriver webDriver;
	Map<Object, String> map = new HashMap<Object, String>();
	public static final int TIME_OUT_SECONDS = 15;

	public void readVariables(Object StepDefinitionClass) {
		// map = new ReadFieldsRef(StepDefinitionClass)
	}

	public void testnonstatic(){
		
	}
	public static WebDriverWait webDriverWait() {
		return new WebDriverWait(getInstance(), TIME_OUT_SECONDS);
	}

	/**
	 * @param idType
	 * @param attributeValue
	 * Locate element method returns the WebElement
	 * @return
	 */
	public static WebElement locateElement(IdType idType, String attributeValue) {
		boolean status = false;
		WebElement elementIdentified = null;
		Instant start = null;
		int count = 0;
		start = Instant.now();
		while (status == false) {
			try {
				count++;
				switch (idType) {
				case Xpath:
					RemoteWebDriver webDriver = getInstance();
					System.out.println(" webdriver attribute value"+attributeValue);
					By by = By.xpath(attributeValue);
					System.out.println("by value>>>>>>>>>>>>>>>>>>>>>"+by);
					if(by != null){
					WebElement webElement = webDriver.findElement(by);
					return elementIdentified = highLightElement(webElement);
					}
					return elementIdentified;
				case Id:
					return elementIdentified = highLightElement(getInstance().findElement(By.id(attributeValue)));
				case linkText:
					return elementIdentified = highLightElement(getInstance().findElement(By.linkText(attributeValue)));
				case PartialLinkText:
					return elementIdentified = highLightElement(
							getInstance().findElement(By.partialLinkText(attributeValue)));
				case CSSSelector:
					return elementIdentified = highLightElement(
							getInstance().findElement(By.cssSelector(attributeValue)));
				case Name: 
					return elementIdentified = highLightElement(
							getInstance().findElement(By.name(attributeValue)));

				default:
					System.out.println("Invalid Id type is given as input parameter");
					break;

				}
				status = true;
			} catch (Exception ex) {
				ex.printStackTrace();
				if (count > 2) {
					break;
				}
			}
		}
		return elementIdentified;
	}

	/**
	 * getInstance returns the web driver
	 * @return
	 */
	public static RemoteWebDriver getInstance() {
		
		if (webDriver == null) {
			if (BROWSER.equals("chrome")) {
				webDriver = WebDriverHelper.getWebDriver();
			}
		}
		return webDriver;

	}

	/**
	 * PrintInReport method is used to embed the msg in Cucumber report
	 * @param msgToWrite
	 */
	public static void printInReport(String msgToWrite) {
		Scenario scn = TestHooks.myScenario;
		scn.write(msgToWrite);
	}

	/**
	 * @param element
	 * @return
	 * highlight method to highlight the element 
	 */
	public static WebElement highLightElement(WebElement element) {
		if(element!=null && element.isDisplayed()) {
		JavascriptExecutor js = (JavascriptExecutor) getInstance();
		
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow;");
        
        

		//js.executeScript("arguments[0].style.background='#fff2ac';", element);

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			System.out.println(e.getMessage());
		}

		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		}
		return element;
	}

	/**
	 * @param idType
	 * @param attributeDesc
	 * click method to perform click functionality on element
	 */
	public static void click(IdType idType, String attributeDesc) {
		locateElement(idType, attributeDesc).click();
	}

	/**
	 * @param idType
	 * @param attributeDesc
	 * @param typeToText
	 * typeText method to clear the text field and enter the text
	 */
	public static void typeText(IdType idType, String attributeDesc, String typeToText) {
		locateElement(idType, attributeDesc).clear();
		locateElement(idType, attributeDesc).click();
		locateElement(idType, attributeDesc).sendKeys(typeToText);
	}
	
	/**
	 * @param url
	 * OpenURL method to open the URL in the given browser
	 */
	public static void openUrl(String url) {
		try {
			getInstance().get(url);
		} catch (Exception e) {
			e.printStackTrace();
			waitForPageToLoadFully();
		}
	}

	/**
	 * Below method is to wait till the page is fully loaded	
	 */
	public static void waitForPageToLoadFully() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver){
				return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};
		
		WebDriverWait wait = new WebDriverWait(getInstance(), 25);
		wait.until(pageLoadCondition);
	}
	
	/**
	 * @param idType
	 * @param attribute
	 * @return
	 * This method to fetch the element text 
	 */
	public static String getText(IdType idType, String attribute) {
		return locateElement(idType, attribute).getText();
	}
	
	/**
	 * @param idType
	 * @param attributeValue
	 * This method to wait until the visibility of the element
	 */
	public static void waitUntil_VisibilityOf_Element(IdType idType, String attributeValue) {
		WebElement element = locateElement(idType, attributeValue);
		if(element!=null) {
		WebDriverWait wait = webDriverWait();
		wait.until(ExpectedConditions.visibilityOf(element));
		}
	}
	
	/**
	 * @param idType
	 * @param attributeValue
	 * This method to wait until invisibility of the element
	 */
	public static void waitUntil_InVisibilityOf_Element(IdType idType, String attributeValue) {
		WebElement element = locateElement(idType, attributeValue);
		if(element!=null) {
		WebDriverWait wait = webDriverWait();
		wait.until(ExpectedConditions.invisibilityOf(element));
		}
	}
	
	/**
	 * @param idType
	 * @param attributeValue
	 * @return
	 * This method to check the element presence on the screen
	 */
	public static boolean isPresent(IdType idType, String attributeValue) {
		boolean elementPresence = false;
		WebElement ele = locateElement(idType, attributeValue);
		try {
			if (ele != null)
				elementPresence = ele.isDisplayed();
		} catch (NoSuchElementException e) {

		}

		return elementPresence;
	}
	
	/**
	 * @param idType
	 * @param attributeDesc
	 * @param attributeType
	 * @return
	 * This method to fetch the attribute of an element
	 */
	public static String getAttribute(IdType idType, String attributeDesc, String attributeType) {
		return locateElement(idType, attributeDesc).getAttribute(attributeType);
	}
	
	/**
	 * @param idType
	 * @param attributeDesc
	 * @return
	 * This method to fetch the list of elements displayed with the same attribute
	 */
	public static List<WebElement> locateElements(IdType idType, String attributeDesc) {
		boolean status = false;
		List<WebElement> elementsIdentified = null;
		Instant start = null;
		int count = 0;
		start = Instant.now();
		while (status == false) {
			try {
				count++;
				switch (idType) {
				case Xpath:
					return elementsIdentified = getInstance().findElementsByXPath(attributeDesc);
				case Id:
					return elementsIdentified = getInstance().findElementsById(attributeDesc);
				case linkText:
					return elementsIdentified = getInstance().findElementsByLinkText(attributeDesc);
				case PartialLinkText:
					return elementsIdentified = 
							getInstance().findElementsByPartialLinkText(attributeDesc);
				case CSSSelector:
					return elementsIdentified = 
							getInstance().findElementsByCssSelector(attributeDesc);
				case Name: 
					return elementsIdentified = 
							getInstance().findElementsByName(attributeDesc);

				default:
					System.out.println("Invalid Id type is given as input parameter");
					break;

				}
				status = true;
			} catch (Exception ex) {
				Instant end = Instant.now();
				if (count > 2) {
					ex.printStackTrace();
				}
			}
		}
		return elementsIdentified;
	}
	
	/**
	 * This method to switch to the default content
	 */
	public static void switchToDefault() {
		getInstance().switchTo().defaultContent();
	}
	
	/**
	 * @param frameId
	 * This method to switch to iFrame
	 */
	public static void switchToiFrame(String frameId) {
		getInstance().switchTo().frame(frameId);
	}
	
	/**
	 * This method to capture the screenshot
	 */
	public static void captureScreenshot() {
		Scenario sceanrio = TestHooks.myScenario;
		final byte[] screenShotscrFile = ((TakesScreenshot)getInstance()).getScreenshotAs(OutputType.BYTES);
		sceanrio.embed(screenShotscrFile, "image/png");
	}
	
	/**
	 * @return
	 * This method to fetch the iFrame
	 */
	public static WebElement getiFrame() {
		WebElement iFrame= getInstance().findElement(By.tagName("iframe"));
		return iFrame;
	}
	
	
	public static void pressEnterKey (IdType idType, String locator) {
		locateElement(idType, locator).sendKeys(Keys.ENTER);
	}
	
	public static void clickDestroyicon(IdType idType, String taskRow, String deleteicon) {
		Actions builder = new Actions(getInstance());
		 WebElement element = locateElement(idType, taskRow);
		 builder.moveToElement(element).build().perform();
		 click(idType, deleteicon);
	}
}