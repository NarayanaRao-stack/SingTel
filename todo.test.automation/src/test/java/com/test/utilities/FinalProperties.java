package com.test.utilities;

import java.io.IOException;
import java.util.Properties;

import org.assertj.core.api.AutoCloseableSoftAssertions;

public class FinalProperties {

	public static AutoCloseableSoftAssertions softAssert;
	public static int softAssertsCount = 0;
	public FinalProperties() throws IOException {

	}

	public static Properties env = ReadProperties.getPropInstance().testProperties;
	//public static Properties bankDetails = ReadProperties.getAppProperties();
	//public static Properties appStrings = ReadProperties.getAppStringsPropertiesFile();
	
	public static final String BROWSER = env.getProperty("browser");
	public static final String PLATFORM = env.getProperty("platform");
	public static final String CHROME_EXE = env.getProperty("chrome.driver");

}
