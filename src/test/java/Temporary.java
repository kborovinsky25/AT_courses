import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleContains;

public class Temporary {
    @Test
    public void avito_full_size() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "c:/driver/geckodriver.exe");

        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.avito.ru/sankt-peterburg");
        Assert.assertEquals(driver.getTitle(), "Доска объявлений от частных лиц и компаний в Санкт-Петербурге на Avito");

        WebElement elemSearch = driver.findElement(By.id("search"));
        elemSearch.sendKeys("Volkswagen Jetta");
//        elemSearch.sendKeys("sdghsdgdfh");
        elemSearch.submit();
        wait.until(titleContains(" - "));
        Assert.assertTrue(driver.findElement(By.className("breadcrumbs-link-count")).isDisplayed());
        TakesScreenshot sc = (TakesScreenshot)driver;

        driver.close();
    }

}
