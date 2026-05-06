package org.example.scooterTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.example.scooterTest.Resources.*;

public class FAQTest {
    private WebDriver driver;
    private HomePageScooter homePage;

    @Before
    public void setUp() {
        // Автоматически загружает и настраивает ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new HomePageScooter(driver);
        // Открываем страницу один раз перед тестами
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    @Test
    public void FAQCorrectAnswerText() {
        // Проверка соответствия текста ответов с ожидаемыми

        // Вопрос 1
        homePage.clickQuestion(1);
        homePage.isCorrectText(1, answer1Text);

        // Вопрос 2
        homePage.clickQuestion(2);
        homePage.isCorrectText(2, answer2Text);

        // Вопрос 3
        homePage.clickQuestion(3);
        homePage.isCorrectText(3, answer3Text);

        // Вопрос 4
        homePage.clickQuestion(4);
        homePage.isCorrectText(4, answer4Text);

        // Вопрос 5
        homePage.clickQuestion(5);
        homePage.isCorrectText(5, answer5Text);

        // Вопрос 6
        homePage.clickQuestion(6);
        homePage.isCorrectText(6, answer6Text);

        // Вопрос 7
        homePage.clickQuestion(7);
        homePage.isCorrectText(7, answer7Text);

        // Вопрос 8
        homePage.clickQuestion(8);
        homePage.isCorrectText(8, answer8Text);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Безопасное закрытие драйвера
        }
    }
}