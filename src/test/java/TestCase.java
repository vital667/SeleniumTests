import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestCase {

    protected WebDriver webDriver;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "src/driver/chromedriver");
        webDriver = new ChromeDriver();
    }

    @AfterMethod
    public void afterTest() {
        webDriver.close();
        webDriver.quit();
    }
}
