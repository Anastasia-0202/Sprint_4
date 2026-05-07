package org.example.scootertest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.example.scootertest.Resources.*;

public class FAQTest {
    private WebDriver driver;
    private org.example.scootertest.HomePageScooter homePage;

    @Before
    public void setUp() {
        // Автоматически загружает и настраивает ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new org.example.scootertest.HomePageScooter(driver);
        // Открываем страницу один раз перед тестами
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    @Test
    public void FAQCorrectAnswerText() {
        String[] expectedAnswers = {
                Resources.RENTAL_PRICE_INFO,
                Resources.MULTIPLE_SCOOTERS_INFO,
                Resources.RENTAL_START_INFO,
                Resources.DELIVERY_START_DATE_INFO,
                Resources.ONLINE_SUPPORT_INFO,
                Resources.BATTERY_LIFE_INFO,
                Resources.CANCEL_BEFORE_DELIVERY_INFO,
                Resources.SERVICE_AREA_INFO
        };

        for (int i = 0; i < expectedAnswers.length; i++) {
            int questionNumber = i + 1;
            homePage.clickQuestion(questionNumber);
            homePage.isCorrectText(questionNumber, expectedAnswers[i]);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Безопасное закрытие драйвера
        }
    }
}