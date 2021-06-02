package com.test.automation.step_definitions;

import org.assertj.core.api.AutoCloseableSoftAssertions;
import java.util.Map;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import com.test.utilities.*;
import static com.test.utilities.FinalProperties.*;

public class TestHooks {

	public static Scenario myScenario;

	@BeforeSuite
	public void startup() {

	}

	@Before
	public void before_everyScenario(Scenario scenario) {
		softAssert = new AutoCloseableSoftAssertions();
		myScenario = scenario;
		WebActions.getInstance();
	}

	@After
	public void after_everyScenario(Scenario scenario) {
		try {
			Map<String, Object> screenShots = ScreenshotHelper.getScreenshotsForTest();
			for (Map.Entry<String, Object> screenshot : screenShots.entrySet()) {
				scenario.write(screenshot.getKey());
				scenario.embed((byte[]) screenshot.getValue(), "image/png");
			}
			if (scenario.isFailed()) {
				scenario.write(WebActions.getInstance().getCurrentUrl());
				byte[] screenShot = ((TakesScreenshot) WebActions.getInstance()).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenShot, "image/png");
			}
			ScreenshotHelper.removeScreenshotsAfterTest();
		} catch (WebDriverException | ClassCastException wd) {
			wd.printStackTrace();
		} finally {
			softAssert.assertAll();
			softAssertsCount = 0;
		}

	}
	
	@AfterSuite
	public void teardown() {
		WebActions.getInstance().close();
		WebActions.getInstance().quit();
		
	}

}