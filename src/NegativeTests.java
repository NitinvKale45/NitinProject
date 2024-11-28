import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.List;

public class NegativeTests {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://todomvc.com/examples/react/");
    }

    @AfterMethod
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAddEmptyTodo() {
        WebElement inputField = driver.findElement(By.xpath("//input[@id='todo-input']"));
        inputField.sendKeys("");
        inputField.sendKeys(Keys.ENTER);

        List<WebElement> todos = driver.findElements(By.xpath("//ul[@class='todo-list']/li"));
        Assert.assertEquals(todos.size(), 0, "Validate Empty todo item is not added");
    }

    @Test
    public void testClearCompletedWithNoCompletedItems() {
        WebElement clearCompletedButton = driver.findElement(By.xpath("//button[@class='clear-completed']"));
        Assert.assertFalse(clearCompletedButton.isDisplayed(), "Validate Clear Completed button is not visible without completed tasks");
    }

    @Test
    public void testTabsNotInteractableWithoutTodos() {
	     WebElement inputField = driver.findElement(By.xpath("//input[@id='todo-input']"));
         inputField.sendKeys("");
         inputField.sendKeys(Keys.ENTER);
        // Check that the footer section  is not displayed
        List<WebElement> footer = driver.findElements(By.xpath("//footer[@class='footer']"));
        Assert.assertTrue(footer.isEmpty(), "Tabs All, Active, Completed, Clear Completed are not visible");

}
    }

@Test
public void testDuplicateTodosAllowed() {
    // Locate the input field for adding todos
WebElement inputField = driver.findElement(By.xpath("//input[@id='todo-input']"));

// Add the same todo item twice
inputField.sendKeys("Running" + Keys.ENTER);
inputField.sendKeys("Running" + Keys.ENTER);

// Verify that two identical items are added to the todo list
List<WebElement> todos = driver.findElements(By.xpath("//ul[@class='todo-list']/li"));
Assert.assertEquals(todos.size(), 2, "Duplicate todos should be allowed but are not");
Assert.assertEquals(todos.get(0).getText(), "Running", "First todo item matches expected text");
Assert.assertEquals(todos.get(1).getText(), "Running", "Second todo item matches expected text");
}
}