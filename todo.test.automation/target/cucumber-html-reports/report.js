$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/featurefiles/Add-mvc-todo.feature");
formatter.feature({
  "name": "As a todoMVC user, I want to manage my todo list",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@TodoSanityTest"
    }
  ]
});
formatter.scenario({
  "name": "Validate the date field with multiple date formats and values",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@TodoSanityTest"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I launch MVC todo application url and verify the UI",
  "keyword": "Given "
});
formatter.match({
  "location": "TodoTest.i_launch_mvc_todo_application_url_and_verify_the_ui()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I enter the task description in text field and save multiple tasks",
  "rows": [
    {
      "cells": [
        "Welcome to SingTel world"
      ]
    },
    {
      "cells": [
        "Buy SingTel SIM only"
      ]
    },
    {
      "cells": [
        "Buy SingTel SIM with Phone"
      ]
    }
  ],
  "keyword": "When "
});
formatter.match({
  "location": "TodoTest.i_enter_the_task_description_in_text_field_and_save_multiple_tasks(DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I verify the footer display",
  "keyword": "Then "
});
formatter.match({
  "location": "TodoTest.i_verify_the_footer_display()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I remove tasks from the UI",
  "rows": [
    {
      "cells": [
        "2"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "TodoTest.i_remove_tasks_from_the_ui(DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I verify the tasks in the UI",
  "keyword": "Then "
});
formatter.match({
  "location": "TodoTest.i_verify_the_tasks_in_the_ui()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});