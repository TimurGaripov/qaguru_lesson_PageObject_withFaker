package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.byText;


public class DemoqaFormTest {

    @BeforeAll
    // Настройки для теста
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    // Заполнение формы данными
    void practiceFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Test");
        $("#lastName").setValue("Testov");
        $("#userEmail").setValue("testov@test.ru");
        $(byText("Male")).click();
        $("#userNumber").setValue("9151302211");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").selectOption("1986");
        $(".react-datepicker__day--007").click();
        $("#subjectsInput").setValue("Arts").pressEnter();
        $("[for=hobbies-checkbox-1]").click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/123.jpg"));
        $("#currentAddress").setValue("Bashkortostan, Ufa");
        $("#state").scrollTo().click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();

        // Отправка заполненной формы
        $("#submit").scrollTo().click();

        // Проверка ответа
        $(".modal-body").shouldHave(
                text("Test Testov"),
                text("testov@test.ru"),
                text("Male"),
                text("9151302211"),
                text("07 May,1986"),
                text("Bashkortostan, Ufa"),
                text("Sports"),
                text("123.jpg"),
                text("NCR Delhi")
              );
    }
}