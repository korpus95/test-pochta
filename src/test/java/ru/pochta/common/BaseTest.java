package ru.pochta.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseTest {
    protected static WebDriver driver;
    public static final String YANDEX_MARKET_TITLE = "Яндекс.Маркет — выбор и покупка товаров из проверенных интернет-магазинов";

    protected void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", ".\\driver\\86_0_4240_22\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public boolean isElementPresent(By locator){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(locator);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (list.size() == 0) {
            return false;
        } else {
            return  list.get(0).isDisplayed();
        }
    }

    public boolean isAttribtuePresent(WebElement element, String attribute) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null){
                result = true;
            }
        } catch (Exception e) {}

        return result;
    }

    public WebElement findElementByText(String text){
        return driver.findElement(By.xpath("//*[contains(text(), '" + text + "')]"));
    }

    public static void loggingResult(boolean result, String failedText){
        if(result) {
            System.out.println("Step is passed");
        } else System.out.println("Step is failed by caused = " + failedText);

    }

    public static void comparisonOfTitle(String title){
        assertEquals((driver.getTitle()), title, "The title does not match the expected, title = [" + driver.getTitle()+"]");
    }

    protected void tearDown() {
        driver.quit();
    }
}
