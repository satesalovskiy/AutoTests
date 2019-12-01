package tv.twitch;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FirstTest {
    ChromeDriver driver;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "email", "password" }
        });
    }

    @Parameterized.Parameter(0)
    public String email;

    @Parameterized.Parameter(1)
    public String password;

    private void Delay(int d) {
        try {
            Thread.sleep(d);
        } catch (InterruptedException e) {}
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/tessergey/Downloads/chromedriver");
        driver = new ChromeDriver();
        System.out.println("Start testing...");
        driver.get("https://www.youtube.com/?gl=RU&hl=ru");
    }

    @After
    public void close() {
       Delay(7000);
       driver.quit();
    }

    @Test
    public void Test1() {
        WebElement search = driver.findElement(By.id("search"));
        for (int i=0; i<5;i++) {
            search.click();
            search.sendKeys("Most popular video " + i);
            search.sendKeys(Keys.ENTER);
            Delay(2500);
            search.click();
            for (int j =0; j<20;j++){
                search.sendKeys(Keys.BACK_SPACE);
            }
        }
    }

    @Test
    public void Test2() {
        WebElement item = driver.findElement(By.xpath("//*[@id=\"buttons\"]/ytd-button-renderer/a"));
        item.click();
    }

    @Test
    public void Test3() {
        WebElement item = driver.findElement(By.xpath("//*[@id=\"buttons\"]/ytd-button-renderer/a"));
        item.click();
        item = driver.findElement(By.xpath(" //*[@id=\"identifierId\"]"));
        item.sendKeys(email);
        item.sendKeys(Keys.ENTER);
        Delay(1000);
        item = driver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input"));
        item.sendKeys(password);
        item.sendKeys(Keys.ENTER);
    }

    @Test
    public void Test4() {
        for (int i=0;i<10;i++){
            driver.executeScript("window.scrollBy(0,10000)", "");
            //Delay for page load
            Delay(1000);
        }
    }

    @Test
    public void Test5() {
        Delay(2000);
        WebElement item;
        for (int i=1;i<6;i++){
            item = driver.findElement(By.xpath("//*[@id=\"items\"]/ytd-mini-guide-entry-renderer["+i+"]"));
            item.click();
            Delay(2000);
        }
    }
}
