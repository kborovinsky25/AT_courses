import org.apache.commons.io.FileUtils;
import org.jcp.xml.dsig.internal.dom.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.openqa.selenium.OutputType.FILE;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Home_work_1 {
    @Test
    public void avito_full_size() throws InterruptedException, IOException {
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
        Thread.sleep(1500);
        TakesScreenshot sc = (TakesScreenshot)driver;
        File screen = sc.getScreenshotAs(FILE);
        FileUtils.copyFile(screen, new File("c:\\driver\\screenshot\\sc.png"));
        driver.close();
    }

    @Test
    public void avito_1000_1000() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "c:/driver/geckodriver.exe");

        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().setSize(new Dimension(1000, 1000));
        driver.navigate().to("https://www.avito.ru/sankt-peterburg");
        Assert.assertEquals(driver.getTitle(), "Доска объявлений от частных лиц и компаний в Санкт-Петербурге на Avito");

        WebElement elemSearch = driver.findElement(By.id("search"));
        elemSearch.sendKeys("Volkswagen Jetta");
//        elemSearch.sendKeys("sdghsdgdfh");
        elemSearch.submit();
        wait.until(titleContains(" - "));
        Assert.assertTrue(driver.findElement(By.className("breadcrumbs-link-count")).isDisplayed());
        driver.close();
    }

    @Test
    public void avito_screenElement() throws InterruptedException, IOException {
        System.setProperty("webdriver.gecko.driver", "c:/driver/geckodriver.exe");

        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.navigate().to("https://www.avito.ru/sankt-peterburg");

        TakesScreenshot sc = (TakesScreenshot)driver;
        File screen = sc.getScreenshotAs(FILE);
        WebElement element_sc = driver.findElement(By.xpath(".//*[@id='catalog']/div[3]/div[2]/div/div[2]/a"));
        FileUtils.copyFile(screenElement(element_sc, screen), new File("c:\\driver\\screenshot\\sc_el.png"));

        driver.close();
    }

    public static File screenElement(WebElement element, File screen) throws IOException {
        BufferedImage image = ImageIO.read(screen);
        int w = element.getSize().getWidth();
        int h = element.getSize().getHeight();
        Rectangle r = new Rectangle(w, h);
        Point p = element.getLocation();
        BufferedImage newScreen = image.getSubimage(p.getX(), p.getY(), r.width, r.height);
        ImageIO.write(newScreen, "png", screen);
        System.out.println(p.getX() + " " + p.getY() + " " + w + " " + h);
        return screen;
    }

    @Test
    public void avito_dc() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "c:/driver/geckodriver.exe");
        DesiredCapabilities cap = DesiredCapabilities.firefox();
        cap.setPlatform(Platform.ANDROID);
        WebDriver driver = new FirefoxDriver(cap);
        driver.manage().window().maximize();
        driver.navigate().to("http://avito.ru");
        Thread.sleep(4000);

        driver.close();
    }
    @Test
    public void avito_dc_chr() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "c:/driver/chromedriver.exe");
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setPlatform(Platform.ANDROID);
        WebDriver driver = new ChromeDriver(cap);
        driver.manage().window().maximize();
        driver.navigate().to("http://avito.ru");
        Thread.sleep(4000);

        driver.close();
    }
}
