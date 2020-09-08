import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class JPetStoreTest extends TestCase {


    @Test
    public void testMainPage() {
        webDriver.navigate().to("https://jpetstore.cfapps.io/catalog");

        WebElement webElement = webDriver.findElement(By.xpath("//div[@id='Content']//a[5]"));
        String url = webElement.getAttribute("href");
        webDriver.navigate().to(url);

        List<WebElement> list = webDriver.findElements(By.xpath("//body//tr"));
        Assert.assertTrue(list.size() > 1);

    }
}
