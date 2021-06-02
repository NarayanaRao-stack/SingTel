@TodoFunctional
Feature: As a todoMVC user, I want to manage my todo list

  Scenario: Validate the date field with multiple date formats and values
	 Given User launch MVC todo application url and verify the UI
	 When User enter the task description in text field and save multiple tasks
		| Welcome to SingTel world  | 
		|    Buy SingTel SIM only		  |    
		| Buy SingTel SIM with Phone |  
	 Then User verify the footer display
	 And User remove tasks from the UI 
		 |2|
	 Then User verify the tasks in the UI
	
  