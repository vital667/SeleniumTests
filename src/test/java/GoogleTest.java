import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class GoogleTest {
    private WebDriver webDriver;

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


    @Test
    public void testGoogle() {
        webDriver.navigate().to("http://google.pl");
        WebElement input = webDriver.findElement(By.name("q"));
        input.sendKeys("warszawa");
        input.submit();
        assertTrue(webDriver.getTitle().contains("warszawa"));
    }


    @Test
    public void testGoogle2() {
        webDriver.navigate().to("http://google.pl");
        WebElement input = webDriver.findElement(By.name("q"));
        input.sendKeys("kurs NBP");
        input.submit();
        assertTrue(webDriver.getTitle().contains("kurs NBP"));
    }


    @Test
    public void testAkademiaKodu() {
        webDriver.navigate().to("http://www.akademiakodu.pl");
        List<WebElement> elements = webDriver.findElements(By.cssSelector(".menu-links ul li a"));
        assertEquals("Facebook", elements.get(0).getText());
        assertEquals("Szkolenie Python", elements.get(1).getText());
        assertEquals("Youtube", elements.get(2).getText());
        assertEquals("Grupa facebook", elements.get(3).getText());
        assertEquals("Dofinansowania", elements.get(4).getText());
        assertEquals("Książka", elements.get(5).getText());
    }


    @Test
    public void testAkademiaKodu2() {
        webDriver.navigate().to("http://www.akademiakodu.pl");
        WebElement webElement = webDriver.findElement(By.linkText("Dofinansowania"));
        String url = webElement.getAttribute("href");
        webDriver.navigate().to(url);
        assertTrue(webDriver.getPageSource().contains("Urząd Pracy"));
    }


    @Test
    public void Test5Animals() {
        webDriver.navigate().to("https://jpetstore.cfapps.io/catalog");
        WebElement webElement = webDriver.findElement(By.xpath("//div[@id='Content']//a[5]"));
        String url = webElement.getAttribute("href");
        webDriver.navigate().to(url);

        List<WebElement> list = webDriver.findElements(By.xpath("//body//tr"));
        Assert.assertTrue(list.size() > 1);
    }


    @Test
    public void Test6Heroku() throws InterruptedException {
        webDriver.navigate().to("http://the-internet.herokuapp.com/dropdown");
        Select dropdown = new Select(webDriver.findElement(By.id("dropdown")));
        Assert.assertTrue(dropdown.getFirstSelectedOption().getText().equals("Please select an option"));
        Thread.sleep(1000);

        dropdown.selectByIndex(1);
        Thread.sleep(1000);
        Assert.assertTrue(dropdown.getFirstSelectedOption().getText().equals("Option 1"));

        Thread.sleep(1000);
        dropdown.selectByIndex(2);
        Thread.sleep(1000);
        Assert.assertTrue(dropdown.getFirstSelectedOption().getText().equals("Option 2"));
    }


    @Test
    public void Test7Heroku() throws InterruptedException {
        webDriver.navigate().to("http://the-internet.herokuapp.com/hovers");
        Actions action = new Actions(webDriver);

        action.moveToElement(webDriver.findElement(By.xpath("//div[@class='example']//div[1]//img[1]"))).perform();
        List<WebElement> elements = webDriver.findElements(By.tagName("h5"));
        Assert.assertTrue(elements.get(0).getText().contains("user1"));
        Assert.assertTrue(elements.get(1).getText().isEmpty());
        Assert.assertTrue(elements.get(2).getText().isEmpty());
        Thread.sleep(500);

        action.moveToElement(webDriver.findElement(By.xpath("//div[@class='row']//div[2]//img[1]"))).perform();
        List<WebElement> elements2 = webDriver.findElements(By.tagName("h5"));
        Assert.assertTrue(elements.get(0).getText().isEmpty());
        Assert.assertTrue(elements.get(1).getText().contains("user2"));
        Assert.assertTrue(elements.get(2).getText().isEmpty());
        Thread.sleep(500);

        action.moveToElement(webDriver.findElement(By.xpath("//div[3]//img[1]"))).perform();
        List<WebElement> elements3 = webDriver.findElements(By.tagName("h5"));
        Assert.assertTrue(elements.get(0).getText().isEmpty());
        Assert.assertTrue(elements.get(1).getText().isEmpty());
        Assert.assertTrue(elements.get(2).getText().contains("user3"));
        Thread.sleep(500);
    }


    @Test
    public void ExplicitWaitTest() throws InterruptedException {
        webDriver.navigate().to("http://the-internet.herokuapp.com/dynamic_controls");
        Actions action = new Actions(webDriver);
        webDriver.manage().window().maximize();

        WebElement checkBox = webDriver.findElement(By.xpath("//div[@id='checkbox']//input"));
        Assert.assertFalse(checkBox.isSelected());
        Assert.assertTrue(checkBox.isDisplayed());

        action.moveToElement(webDriver.findElement(By.xpath("//button[contains(text(),'Remove')]"))).click().perform();

        WebDriverWait wait = new WebDriverWait(webDriver, 15);
        wait.until(ExpectedConditions.invisibilityOf(checkBox));

        Assert.assertTrue(webDriver.findElement(By.id("message")).getText().equals("It's gone!"));
    }


    @Test
    public void DynamicPage() throws InterruptedException {
        webDriver.navigate().to("http://the-internet.herokuapp.com/dynamic_loading/1");
        Actions action = new Actions(webDriver);

        WebElement hidden = webDriver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]"));
        Assert.assertFalse(hidden.isDisplayed());

        WebElement start = webDriver.findElement(By.xpath("//button[contains(text(),'Start')]"));
        action.moveToElement(start).click().perform();

        Thread.sleep(5000);
        Assert.assertTrue(hidden.isDisplayed());
    }
}