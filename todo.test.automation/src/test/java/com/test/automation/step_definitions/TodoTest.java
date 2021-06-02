package com.test.automation.step_definitions;

import com.test.utilities.WebActions;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

import static com.test.utilities.FinalProperties.*;
import java.util.ArrayList;
import java.util.List;
import static com.test.enums.IdType.*;

public class TodoTest extends WebActions {

	// Landing page
	private static String Todo_Header = "//h1";
	private static String Todo_Field = "//input[@class='new-todo']";
	private static String Toggle_All = "//label[@for='toggle-all']";
	private static String Tasks_List = "//ul[@class='todo-list']/li";
	private static String Task_Check_Box = "/div[1]/input[@type='checkbox']";
	private static String Task_Label = "/div[1]/label";
	private static String Task_Delete = "/div[1]/button";
	//Tasks Footer section
	private static String Task_Footer = "//footer[@class='footer']/span";
	private static String Footer_Filters ="//footer[@class='footer']/ul/li[%s]/a";
	
	private static List<String> removedTasks = new ArrayList<String>();
    @Given("^User launch MVC todo application url and verify the UI$")
    public void user_launch_mvc_todo_application_url_and_verify_the_ui() throws Throwable {
    	openUrl(env.getProperty("applicationUrl"));
		waitForPageToLoadFully();
		softAssert.assertThat(getText(Xpath, Todo_Header)).as("Date field label is not matching with the UI")
		.isEqualTo("todos");
		softAssert.assertThat(getAttribute(Xpath, Todo_Field, "placeholder")).as("Place Holder text is not matching with the UI")
		.isEqualTo("What needs to be done?");
		softAssert.assertThat(isPresent(Xpath, Todo_Field)).as("Results field label is not matching with the UI")
		.isTrue();
    }

    @When("^User enter the task description in text field and save multiple tasks$")
    public void user_enter_the_task_description_in_text_field_and_save_multiple_tasks(DataTable taskDescriptions) throws Throwable {
        click(Xpath, Todo_Field);
        List<String> descriptions = taskDescriptions.asList();
        for(String desc : descriptions) {
        typeText(Xpath, Todo_Field, desc);
        pressEnterKey(Xpath, Todo_Field);
        waitForPageToLoadFully();
        }
    }
    
    @Then("^User verify the footer display$")
    public void user_verify_the_footer_display() throws Throwable {
    	int tasksInUI = locateElements(Xpath, Tasks_List).size();
    	String tasksLeft = String.valueOf(tasksInUI)+ " items left";
    	softAssert.assertThat(isPresent(Xpath, Toggle_All)).as("Tasks left text in Footer is not matching with the UI")
		.isTrue();
    	softAssert.assertThat(getText(Xpath, Task_Footer)).as("Tasks left text in Footer is not matching with the UI")
		.isEqualTo(tasksLeft);
    	softAssert.assertThat(getText(Xpath, String.format(Footer_Filters, "1"))).as("Footer filter1 text is not matching with the UI")
		.isEqualTo("All");
    	softAssert.assertThat(getText(Xpath, String.format(Footer_Filters, "2"))).as("Footer filter2 text is not matching with the UI")
		.isEqualTo("Active");
    	softAssert.assertThat(getText(Xpath, String.format(Footer_Filters, "3"))).as("Footer filter3 text is not matching with the UI")
		.isEqualTo("Completed");
    }

    @And("^User remove tasks from the UI$")
    public void user_remove_tasks_from_the_ui(DataTable count) throws Throwable {    	
    int noOfTasksInUI = locateElements(Xpath, Tasks_List).size();
    String removalCount =count.asList().get(0);	
    removedTasks = new ArrayList<String>();
    	String taskDescription = "";
    	//Verify the Tasks UI
    	/*for (int t=0; t< noOfTasksInUI; t++) {
    		
    	}*/
    	
    	//below method to delete the given no of tasks (count) from UI
    	if(noOfTasksInUI>Integer.valueOf(removalCount)) {
    			for(int i=0; i<Integer.valueOf(removalCount); i++) {
    				taskDescription = getText(Xpath, Tasks_List+"[1]"+Task_Label);
    				removedTasks.add(taskDescription);
    				clickDestroyicon(Xpath, Tasks_List+"[1]", Tasks_List+"[1]"+Task_Delete);
    				waitForPageToLoadFully();
    			}
    			int noOfTasksAfterRemoval = locateElements(Xpath, Tasks_List).size();
    			if(!(noOfTasksAfterRemoval == (noOfTasksInUI - Integer.valueOf(removalCount))))
    				softAssert.fail("Tasks removing from the UI is partially failed");
    				
    	} else {
    		softAssert.fail("No of Tasks in UI is less than the required no of tasks to be deleted");
    	}
        
    }
    
    @Then("^User verify the tasks in the UI$")
    public void user_verify_the_tasks_in_the_ui() throws Throwable {
        int noOfTasks = locateElements(Xpath, Tasks_List).size();
        for (int j=0; j<noOfTasks; j++) {
        	String taskDescription =getText(Xpath, Tasks_List+"["+(j+1)+"]"+Task_Label);
        		for(int k=0; k<removedTasks.size(); k++) {
        			if(taskDescription.equalsIgnoreCase(removedTasks.get(k)))
        				softAssert.fail("Task description" + removedTasks.get(k) + " is failed deleting from UI");
        		}        	
        }
    }
	
	
}
