import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

import static org.openqa.selenium.OutputType.FILE;


public class TestHomeWork {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static TakesScreenshot sc;

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.gecko.driver", "c:/driver/geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        sc = (TakesScreenshot)driver;
    }


    @Test(dataProvider = "correctData", dataProviderClass = TestData.class)
    public void test_positive(String log, String psw) throws IOException {
        driver.navigate().to("https://www.f1news.ru/login");
        WebElement login = driver.findElement(By.id("username"));
        WebElement pass = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("_submit"));
        wait.until(ExpectedConditions.visibilityOf(submit));
        login.clear();
        pass.clear();
        login.sendKeys(log);
        pass.sendKeys(psw);
        submit.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("article")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.f1news.ru/");
        File screen = sc.getScreenshotAs(FILE);
        FileUtils.copyFile(screen, new File("c:\\driver\\screenshot\\f1_sc_1.png"));
    }
    @Test(dataProvider = "incorrectData", dataProviderClass = TestData.class)
    public void test_negative(String log, String psw) throws IOException {
        driver.navigate().to("https://www.f1news.ru/login");
        WebElement login = driver.findElement(By.id("username"));
        WebElement pass = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("_submit"));
        wait.until(ExpectedConditions.visibilityOf(submit));
        login.clear();
        pass.clear();
        login.sendKeys(log);
        pass.sendKeys(psw);
        submit.click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".search_wrapper>div"), "Недействительные аутентификационные данные."));
        Assert.assertEquals(driver.findElement(By.cssSelector(".search_wrapper>div")).getText(), "Недействительные аутентификационные данные.");
        File screen = sc.getScreenshotAs(FILE);
        FileUtils.copyFile(screen, new File("c:\\driver\\screenshot\\f1_sc_1.png"));
    }
    @Test(dataProvider = "dataFromCSV", dataProviderClass = TestData.class)
    public void test_negative_csv(String log, String psw) throws IOException {
        driver.navigate().to("https://www.f1news.ru/login");
        WebElement login = driver.findElement(By.id("username"));
        WebElement pass = driver.findElement(By.id("password"));
        WebElement submit = driver.findElement(By.id("_submit"));
        wait.until(ExpectedConditions.visibilityOf(submit));
        login.clear();
        pass.clear();
        login.sendKeys(log);
        pass.sendKeys(psw);
        submit.click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".search_wrapper>div"), "Недействительные аутентификационные данные."));
        Assert.assertEquals(driver.findElement(By.cssSelector(".search_wrapper>div")).getText(), "Недействительные аутентификационные данные.");
        File screen = sc.getScreenshotAs(FILE);
        FileUtils.copyFile(screen, new File("c:\\driver\\screenshot\\f1_sc_1.png"));
    }
    @AfterClass
    public static void setDown(){
        driver.close();
    }

}
