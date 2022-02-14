package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class iFrameTest{
    WebDriver driver;
    private String searchTerm = "https://www.bing.com/travelguide?q=Redmond";

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void assert_dropdown_list_string() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Actions act = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_iframe");
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        js.executeScript("window.editor.setValue(window.editor.getValue().replace('w3schools','bing'))");

        WebElement runButton;
        runButton = wait.until(presenceOfElementLocated(By.id("runbtn")));
        runButton.click();

        WebElement iframe = driver.findElement(By.xpath("//iframe[@src='https://www.w3schools.com']"));
        driver.switchTo().frame(1).switchTo().frame(iframe);

        WebElement searchField;
        searchField = wait.until(presenceOfElementLocated(By.xpath("//input[@id='sb_form_q']")));
        searchField.click();
        searchField.sendKeys("Redmond");

        List<WebElement> suggestions;
        suggestions = wait.until(presenceOfAllElementsLocatedBy(By.xpath("//span[@class='sa_tm_text']")));
        for (WebElement s:suggestions) {
            Assert.assertTrue(true, "redmond washington");
        }

        WebElement resultLink = driver.findElement(By.xpath("//li[@query=\"redmond washington\"]"));
        resultLink.click();

        List<WebElement> results = driver.findElements(By.xpath("//div[@class='b_attribution']//child::cite"));
        for (int i = 0; i < results.size(); i++) {
            Assert.assertTrue(results
                    .get(i)
                    .getText()
                    .contains(searchTerm), "Search result validation failed at instance [ + i + ].");
        }
    }
}
