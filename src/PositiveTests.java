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

public class PositiveTests {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://todomvc.com/examples/react/");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAddTodoItem() {
        WebElement inputField = driver.findElement(By.xpath("//input[@id='todo-input']"));
        inputField.sendKeys("Pay Rent");
        inputField.sendKeys(Keys.ENTER);

        List<WebElement> todos = driver.findElements(By.xpath("//ul[@class='todo-list']/li"));
        Assert.assertEquals(todos.size(), 1, "Validate No of Items in List");
        Assert.assertEquals(todos.get(0).getText(), "Pay Rent", "Validate added item name");
    }

    @Test
    public void testSelectAllAndMarkCompleted() {
        WebElement inputField = driver.findElement(By.xpath("//input[@id='todo-input']"));
        inputField.sendKeys("Pay LightBill");
        inputField.sendKeys(Keys.ENTER);
        inputField.sendKeys("Study");
        inputField.sendKeys(Keys.ENTER);

        WebElement selectArrow = driver.findElement(By.xpath("//input[@class='toggle-all']"));
        selectArrow.click();

        //here we are checking no of items marked as completed
        List<WebElement> completedTodos = driver.findElements(By.xpath("//ul[@class='todo-list']/li[@class='completed']"));
        Assert.assertEquals(completedTodos.size(), 2, "validate no of items marked as completed");

        WebElement itemsLeft = driver.findElement(By.xpath("//span[@class='todo-count']"));
        Assert.assertEquals(itemsLeft.getText(), "0 items left!", "Validate no of items left message");
    }

    @Test
    public void testClearCompleted() {
        WebElement inputField = driver.findElement(By.xpath("//input[@id='todo-input']"));
        inputField.sendKeys("Running");
        inputField.sendKeys(Keys.ENTER);
        inputField.sendKeys("Work");
        inputField.sendKeys(Keys.ENTER);

        WebElement selectArrow = driver.findElement(By.xpath("//input[@class='toggle-all']"));
        selectArrow.click();

        WebElement clearCompletedButton = driver.findElement(By.xpath("//button[@class='clear-completed']"));
        clearCompletedButton.click();

        List<WebElement> todos = driver.findElements(By.xpath("//ul[@class='todo-list']/li"));
        Assert.assertEquals(todos.size(), 0, "Validate after clicking on clear complete button all items are cleared");
    }

    @Test
    public void testFilterActiveItems() {
        WebElement inputField = driver.findElement(By.xpath("//input[@id='todo-input']"));
        inputField.sendKeys("Running");
        inputField.sendKeys(Keys.ENTER);
        inputField.sendKeys("Work");
        inputField.sendKeys(Keys.ENTER);

        WebElement checkbox = driver.findElement(By.xpath("//ul[@class='todo-list']/li[1]//input[@class='toggle']"));
        checkbox.click();

        WebElement activeFilter = driver.findElement(By.xpath("//a[text()='Active']"));
        activeFilter.click();

        List<WebElement> activeTodos = driver.findElements(By.xpath("//ul[@class='todo-list']/li"));
        Assert.assertEquals(activeTodos.size(), 1, "Validate no of items pending in Active Tab");
    }
}