package org.example.scootertest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;
import static org.example.scootertest.Resources.*;

@RunWith(Parameterized.class)
public class FAQTest {
    private WebDriver driver;
    private HomePageScooter homePage;

    private final int questionNumber;
    private final String expectedText;

    public FAQTest(int questionNumber, String expectedText) {
        this.questionNumber = questionNumber;
        this.expectedText = expectedText;
    }

    @Parameters
    public static Collection<Object[]> provideFAQData() {
        return Arrays.asList(new Object[][] {
                {1, RENTAL_PRICE_INFO},
                {2, MULTIPLE_SCOOTERS_INFO},
                {3, RENTAL_START_INFO},
                {4, DELIVERY_START_DATE_INFO},
                {5, ONLINE_SUPPORT_INFO},
                {6, BATTERY_LIFE_INFO},
                {7, CANCEL_BEFORE_DELIVERY_INFO},
                {8, SERVICE_AREA_INFO}
        });
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        homePage = new HomePageScooter(driver);
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    @Test
    public void FAQCorrectAnswerText() {
        homePage.clickQuestion(questionNumber);
        homePage.isCorrectText(questionNumber, expectedText);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}