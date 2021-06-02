package com.test.automation.Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = {"src/test/resources/featurefiles" }, 
		glue = { "com.test.automation.step_definitions" }, 
		plugin = {"pretty","html:target/cucumber-html-reports","json:target/cucumber.json", "junit:target/cucumber-results.xml"}, 
		tags = {"@TodoFunctional"}, 
		monochrome=true, snippets = SnippetType.CAMELCASE)
public class TestSuiteRunner extends AbstractTestNGCucumberTests{

}
