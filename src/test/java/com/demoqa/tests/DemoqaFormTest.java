package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.DemoqaRegistrationForm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class DemoqaFormTest {
    DemoqaRegistrationForm registrationFormPage = new DemoqaRegistrationForm();

    @BeforeAll
    // Настройки для теста
    static void configure() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1024x768";
    }

    @Test
    // Заполнение формы данными
    void practiceFormTest() {
        registrationFormPage.openPage()
                .setFirstName("Test")
                .setLastName("Testov")
                .setEmail("testov@test.ru")
                .setGender("Male")
                .setNumber("9151302211")
                .setBirthDate("07", "May", "1986")
                .setSubject("Arts")
                .setHobbie()
                .setAddress("Bashkortostan, Ufa")
                .uploadPicture("src/test/resources/123.jpg")
                .setState("NCR")
                .setCity("Delhi")
                .submit();

        // Проверка ответа
        registrationFormPage.checkVisible();
        registrationFormPage.checkResult("Student Name", "Test Testov")
                .checkResult("Student Email", "testov@test.ru")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "9151302211")
                .checkResult("Date of Birth", "07 May,1986")
                .checkResult("Subjects", "Arts")
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "123.jpg")
                .checkResult("Address", "Bashkortostan, Ufa")
                .checkResult("State and City", "NCR Delhi");
    }
}