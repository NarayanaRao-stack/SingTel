package com.test.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

	private static ReadProperties propIns = null;

	public Properties testProperties;

	public static Properties appProperties;
	public static Properties appStringsFile;

	private ReadProperties() {
		testProperties = new Properties();
		FileInputStream propFile;

		try {
			propFile = new FileInputStream("src/main/resources/environment.properties");
			System.out.println("propFile path: " + propFile);
			testProperties.load(propFile);

			// Fetch browser value
			System.out.println("browser value" + System.getProperty("browser"));
			try {
				if (System.getProperty("browser") != null) {
					testProperties.setProperty("browser", System.getProperty("browser").toLowerCase());
				}
			} catch (NullPointerException e) {
				System.out
						.println("browser value is missing, Please provide the browser value and re-execute the tests");
				testProperties.setProperty("browser", System.getProperty("default.browser").toLowerCase());
				e.printStackTrace();
			}
			// Fetch platform value
			System.out.println("Platform value" + System.getProperty("platform"));
			try {
				if (System.getProperty("platform") != null) {
					testProperties.setProperty("platform", System.getProperty("platform").toLowerCase());
				}
			} catch (NullPointerException e) {
				System.out.println(
						"platform value is missing, Please provide the platform value and re-execute the tests");
				e.printStackTrace();
			}
			// highlight value is on or off
			System.out.println("Highlight value" + System.getProperty("highlight"));
			try {
				if (System.getProperty("highlight") != null) {
					testProperties.setProperty("highlight", System.getProperty("highlight").toLowerCase());
				}
			} catch (NullPointerException e) {
				System.out.println("highlight value is missing, so default value for highlight set as ON");
				testProperties.setProperty("highlight", "on");
				e.printStackTrace();
			}
			// screenshot value is on or off
			System.out.println("screenshot value" + System.getProperty("screenshot"));
			try {
				if (System.getProperty("screenshot") != null) {
					testProperties.setProperty("screenshot", System.getProperty("screenshot").toLowerCase());
				}
			} catch (NullPointerException e) {
				System.out.println("screenshot value is missing, so default value for screenshot set as ON");
				testProperties.setProperty("screenshot", "on");
				e.printStackTrace();
			}
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {

		}
	}

	public static ReadProperties getPropInstance() {
		propIns = new ReadProperties();
		return propIns;
	}

	public static Properties getAppProperties() {
		appProperties = new Properties();
		FileInputStream propAppFile;
		try {
			propAppFile = new FileInputStream("src/main/resources/cardDetails.properties");
			appProperties.load(propAppFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appProperties;
	}
	
	public static Properties getAppStringsPropertiesFile() {
		appStringsFile = new Properties();
		FileInputStream appStringsPropFile;
		try {
			appStringsPropFile = new FileInputStream("src/main/resources/applicationLocale.properties");
			appStringsFile.load(appStringsPropFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appStringsFile;
	}
	

}
