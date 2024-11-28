Project Setup

1. Clone the repository or extract the provided .zip file.
2. Open the project in your preferred IDE (e.g., IntelliJ, Eclipse).
3. Ensure that *Java 11+* and *Maven* are installed on your system.

**************************************************************************************************************************
How Tests Are Organized

1.	PositiveTest.java: Contains tests for the four positive scenarios.
2.	NegativeTest.java: Contains tests for the four negative scenarios.
3.	pom.xml: added dependencies.
4.	testng.xml: Manages test execution.

*************************************************************************************************************************
Positive Test Scenarios

1.	Add Single Todo Item: Verify that a single todo item can be added successfully.
2.	Click on Arrow to Mark All Todos as Completed: Verify that when the “Select All” arrow is clicked, all todo items are marked as completed, and “0 items left” is displayed.
3.	Clear Completed Todos: Ensure completed todos items can be cleared, and input box is visible.
4.	Click on Active Filter: Verify that when the “Active” filter is clicked, only active todo items are displayed.ed: Validate that clicking the “Select All” arrow marks all todos as completed and displays “0 items left.”

**************************************************************************************************************************
Negative Test Scenarios

1.	Add Blank Todo: Verify that pressing Enter without input does not add a blank todo.
2.	Clear Completed Button Not Visible Without Completed Todos: Validate that the “Clear Completed” button is not visible when no todos are completed.
3.	Tabs Not Visible Without Todos: Confirm that the “All”, “Active”, “Completed”, and “Clear Completed” tabs are not visible when no todos are present.
4.	Duplicate Todos Allowed: Verify that the application allows adding identical todo items.

// for now i have tested functionality using testng in single method but if we want to create steps using cucumber then we can write feature steps as below

***************************************************************************************************************************
Feature: Positive Test Scenarios

  Scenario: Add Single Todo
    Given the ToDoMVC application is open
    When I enter "Pay Rent" in the todo input field and press Enter
    Then the todo list should display "Grocery"

  Scenario: Click on Arrow to Mark All Todos as Completed
    Given the ToDoMVC application is open
    When I add "Pay Rent" and "Pay Lightbill" to the list
    And I click the "Select All" arrow
    Then all todos should be marked as completed, and "0 items left" is displayed

  Scenario: Clear Completed Todos
    Given the ToDoMVC application is open
    When I add "Running" and mark it as completed
    And I click on the "Clear Completed" button
    Then the todo list should be empty, showing only the input field

  Scenario: Click on Active Filter
    Given the ToDoMVC application is open
    When I add "Running" and "Work" to the list
    And I click on the "Active" filter
    Then only active (incomplete) todos should be visible
    
****************************************************************************************************************************

Feature: Negative Test Scenarios

  Scenario: Add Blank Todo
    Given the ToDoMVC application is open
    When I press Enter without entering any text
    Then no todos should be added to the list

  Scenario: Tabs Not Visible Without Todos
    Given the ToDoMVC application is open
    Then the "All", "Active", "Completed", and "Clear Completed" tabs should not be visible

  Scenario: Clear Completed Button Not Visible Without Completed Todos
    Given the ToDoMVC application is open
    And there are no completed todos
    Then the "Clear Completed" button should not be visible

  Scenario: Duplicate Todos Allowed
    Given the ToDoMVC application is open
    When I add "Duplicate Todo" twice
    Then the todo list should display "Duplicate Todo" twice
    
******************************************************************************************************************************** 
Reporting

1.	TestNG generates default reports after test execution in the test-output folder.
2.	Additional reporting libraries like ExtentReports or Allure can be integrated as needed.
3.  We can get cucumber html report as well

****************************************************************************************************************************
TestNG Annotations Overview:

1. @BeforeTest:it is used to set up the environment before any test methods, such as initializing WebDriver or opening a browser.
2. @Test: it is individual test methods. It runs the test logic, including actions and assertions to verify the expected behavior.
3. @AfterTest: It is used for cleanup tasks like closing the browser or WebDriver to release resources.