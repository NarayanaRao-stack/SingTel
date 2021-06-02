package com.test.automation.drivers;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import static com.test.utilities.FinalProperties.*;

public abstract class WebDriverHelper extends EventFiringWebDriver{
	
	private static RemoteWebDriver REAL_DRIVER;

	public WebDriverHelper() {
		super(REAL_DRIVER);
	}
	
	static {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+CHROME_EXE);
	}

	@SuppressWarnings("deprecation")
	public static RemoteWebDriver startChromeDriver() {
		DesiredCapabilities capabilities = getChromeDesiredCapabilities();
		REAL_DRIVER = new ChromeDriver();
		REAL_DRIVER.manage().window().maximize();
		REAL_DRIVER.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		REAL_DRIVER.manage().logs().get("browser");
		return REAL_DRIVER;
	}

	private static DesiredCapabilities getChromeDesiredCapabilities() {
		LoggingPreferences logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.FINEST);
		File f = null;
		InetAddress address ;
		String hostname = null;
		
		try {
			address = InetAddress.getLocalHost();
			hostname = address.getHostName();
		} catch(UnknownHostException e) {
			e.printStackTrace();
		}
		
		HashMap<String, Object> chromeprefs = new HashMap<String, Object>();
		chromeprefs.put("profile.default.content.settings.popups", 0);
		chromeprefs.put("profile.default.content.settings.notifications", 2);
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(CapabilityType.LOGGING_PREFS, logs);
		capabilities.setPlatform(Platform.WINDOWS);
	/*	ChromeOptions chromeOptions = new ChromeOptions();
		
		chromeOptions.addArguments("start-maximized");
		chromeOptions.addArguments("disable-infobars");
		chromeOptions.addArguments("--disable-extensions");
		chromeOptions.addArguments("--disable-web-security");
		chromeOptions.addArguments("--test-type");
		chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--disable-gpu");
	//	chromeOptions.addArguments("--disable-dev-shm-usage");
		chromeOptions.setExperimentalOption("prefs", chromeprefs);*/
		
	    capabilities.setCapability("chrome.verbose", false);
		capabilities.setCapability("nogui", true);
	//	capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		capabilities.setCapability("pageLoadStrategy", "none");
		return capabilities;
	}
	
	public static RemoteWebDriver getWebDriver() {
		
		if(Arrays.asList("chrome", "firefox") != null) {
			switch(BROWSER) {
			
			case ("chrome") :
				return startChromeDriver();
			case("firefox") :
				return startfirefoxDriver();
			}
		}else {
			System.exit(0);
		}
		return null	;
	}

	private static RemoteWebDriver startfirefoxDriver() {
		// TODO Auto-generated method stub
		return null;
	}
}
