package org.example.scootertest;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.example.scootertest.Resources.confirmHeader;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String subway;
    private final String phoneNumber;
    private final String date;
    private final String rentalPeriod;
    private final String color;
    private final String comment;

    public OrderTest(String name, String surname, String address, String subway, String phoneNumber, String date, String rentalPeriod, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subway = subway;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getDateSetForOrder() {
        return new Object[][] {
                {"Иван", "Иванов", "г. Москва, ул. Достоевского, д.10", "Театральная", "89126543267", "01.01.2010", "сутки", "чёрный жемчуг", "Не звонить в дверь"},
                {"Ирина", "Авдеева", "проспект Маяковского 6", "Маяковская", "+79657654321", "10.10.2030", "двое суток", "серая безысходность", "Привезите чистый самокат"},
        };
    }

    @Test
    public void orderPositiveTest() {
        // Создать веб‑драйвер для Firefox
        driver = new FirefoxDriver();
        // Открыть страницу заказа Яндекс Самокат
        driver.get("https://qa-scooter.praktikum-services.ru");

        // Создать объект класса с домашней страницей
        HomePageScooter homePage = new HomePageScooter(driver);
        // Нажать на кнопку «Заказать» на чердаке
        homePage.clickHeaderOrderButton();

        // Создать объект класса со страницей заказа
        OrderPageScooter orderPage = new OrderPageScooter(driver);
        // Принять куки
        orderPage.clickAcceptCookieButton();

        // Позитивный сценарий оформления заказа
        orderPage.setFirstName(name);
        orderPage.setLastName(surname);
        orderPage.setAddress(address);
        orderPage.setSubway(subway);
        orderPage.setPhoneNumber(phoneNumber);
        orderPage.clickOrderNextButton();

        orderPage.setRentalDate(date);
        orderPage.setRentalPeriod(rentalPeriod);
        orderPage.selectColor(color);
        orderPage.setComment(comment);
        orderPage.clickOrderCreateButton();
        orderPage.clickOrderConfirmButton();

        // Проверить, что открылась страница успешного создания заказа
        orderPage.isPageOpen(orderPage.getConfirmHeader(), confirmHeader);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}