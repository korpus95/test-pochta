package ru.pochta;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ru.pochta.common.BaseTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YandexMarketTest extends BaseTest {

    @BeforeEach
    public void setUp(){
        super.setUpDriver();
    }

    @Test
    void secondTest() throws InterruptedException {
        //Step 1 - Open https://market.yandex.ru/
        driver.get("https://market.yandex.ru/");
        comparisonOfTitle(YANDEX_MARKET_TITLE);

        //Step 2 - Search 'пылесосы'
        driver.findElement(By.id("header-search")).sendKeys("пылесосы", Keys.ENTER);
        isElementPresent(By.tagName("title"));
        comparisonOfTitle("«пылесосы» — Результаты поиска — Яндекс.Маркет");

        //Step 3 - Open categories 'пылесосы'
        findElementByText("Пылесосы").click();
        comparisonOfTitle("«пылесосы» — Пылесосы — купить на Яндекс.Маркете");

        //Step 4 - Click on button 'Все фильтры'
        findElementByText("Все фильтры").click();
        Thread.sleep(5000);
        comparisonOfTitle("Все фильтры — Пылесосы — выбор по параметрам на Яндекс.Маркете");

        //Step 5 - Filter settings
        WebElement buttonAll = driver.findElement(By.xpath("//span[contains(text(), 'Показать ещё')]/.."));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttonAll);
        buttonAll.click();
        findElementByText("Polaris").click();
        findElementByText("VITEK").click();
        driver.findElement(By.xpath("//div[@data-prefix='до']/input[contains(@placeholder, '24 999')]"))
                .sendKeys("6000");
        driver.findElement(By.xpath("//a[contains(text(), 'Показать') and contains(text(), 'предложений')]")).click();
        comparisonOfTitle("«пылесосы» — Пылесосы — купить на Яндекс.Маркете");

        //Step 6 - Checking installed filters
        boolean fixedPrice = driver.findElement(By.xpath("//input[@name='Цена до']")).getAttribute("value").equals("6000");
        boolean polarisChecked = isAttribtuePresent(driver.findElement(By.xpath("//input[@name='Производитель Polaris']")),"checked");
        boolean vitekChecked = isAttribtuePresent(driver.findElement(By.xpath("//input[@name='Производитель VITEK']")), "checked");
        loggingResult(fixedPrice, "Price is not equals 6000");
        loggingResult(polarisChecked, "Attribute checked on element Polaris not found");
        loggingResult(vitekChecked, "Attribute checked on element VITEK not found");
        assertTrue(fixedPrice & polarisChecked & vitekChecked, "finish test step is failed");
    }

    @AfterEach
    public void tearDown(){
        super.tearDown();
    }
}
