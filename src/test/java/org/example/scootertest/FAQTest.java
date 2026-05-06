package org.example.scootertest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.stream.Stream;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.example.scootertest.Resources.*;

public class FAQTest {
    private WebDriver driver;
    private HomePageScooter homePage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new HomePageScooter(driver);
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    @ParameterizedTest
    @MethodSource("faqData")
    public void checkFAQAnswer(int questionNumber, String expectedText) {
        homePage.clickQuestion(questionNumber);
        homePage.isCorrectText(questionNumber, expectedText);
    }

    private static Stream<Arguments> faqData() {
        return Stream.of(
                arguments(1, answer1Text),
                arguments(2, answer2Text),
                arguments(3, answer3Text),
                arguments(4, answer4Text),
                arguments(5, answer5Text),
                arguments(6, answer6Text),
                arguments(7, answer7Text),
                arguments(8, answer8Text)
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}