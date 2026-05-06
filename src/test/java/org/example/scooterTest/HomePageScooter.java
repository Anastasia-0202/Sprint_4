package org.example.scootertest;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class HomePageScooter {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы вопросов (используем более устойчивые селекторы)
    private By questions = By.className("accordion__item");
    private By answers = By.cssSelector("[id^='accordion__panel-']");

    // Кнопки "Заказать"
    private By headerOrderButton = By.xpath("//button[contains(text(), 'Заказать')][1]");
    private By pageOrderButton = By.cssSelector(".Home_FinishButton button");

    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10); // 10 секунд
    }

    /**
     * Кликает на вопрос по номеру (1–8)
     */
    public void clickQuestion(int questionNumber) {
        if (questionNumber < 1 || questionNumber > 8) {
            throw new IllegalArgumentException("Номер вопроса должен быть от 1 до 8");
        }
        List<WebElement> questionElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(questions));
        WebElement question = questionElements.get(questionNumber - 1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);
        question.click();
    }

    /**
     * Получает текст ответа на вопрос по номеру
     */
    public String getAnswerText(int answerNumber) {
        if (answerNumber < 1 || answerNumber > 8) {
            throw new IllegalArgumentException("Номер ответа должен быть от 1 до 8");
        }
        By answerLocator = By.id("accordion__panel-" + (answerNumber - 1));
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answerElement.getText().trim();
    }

    /**
     * Проверяет, что текст ответа соответствует ожидаемому
     */
    public void isCorrectText(int answerNumber, String expectedText) {
        String actualText = getAnswerText(answerNumber);
        System.out.println("Проверяем вопрос " + answerNumber + ":");
        System.out.println("Ожидаемый: '" + expectedText + "'");
        System.out.println("Фактический: '" + actualText + "'");
        MatcherAssert.assertThat(actualText, is(expectedText));
    }

    /**
     * Кликает по кнопке "Заказать" в шапке
     */
    public void clickHeaderOrderButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(headerOrderButton));
        button.click();
    }

    /**
     * Кликает по большой кнопке "Заказать" на странице
     */
    public void clickPageOrderButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(pageOrderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }
}