package ru.pochta;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ru.pochta.common.BaseTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleTest extends BaseTest {

    @BeforeEach
    public void setUp(){
        super.setUpDriver();
    }

    @Test
    void firstTest() {
        //Step 1 - Open https://www.google.com/
        driver.get("https://www.google.com/");
        comparisonOfTitle("Google");

        //Step 2 - Search 'яндекс маркет'
        driver.findElement(By.name("q")).sendKeys("яндекс маркет", Keys.ENTER);
        List<WebElement> list = driver.findElements(By.className("g"));
        WebElement href = list.get(0).findElement(By.tagName("a"));
        assertEquals(href.getAttribute("href"), "https://market.yandex.ru/", "the first link refers to ["+ href.getAttribute("href") + "]");

        //Step 3 - Go to link
        href.sendKeys(Keys.ENTER);
        comparisonOfTitle(YANDEX_MARKET_TITLE);
    }

    @AfterEach
    public void tearDown(){
        super.tearDown();
    }
}
