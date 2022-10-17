package com.demoqa.tests;

import com.demoqa.pages.DemoqaRegistrationForm;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;


public class DemoqaFormTest extends TestBase{
    DemoqaRegistrationForm registrationFormPage = new DemoqaRegistrationForm();
    Faker faker = new Faker();

    String firstName,
            lastName,
            userEmail,
            userNumber,
            day,
            month,
            year,
            state = "NCR",
            city = "Delhi",
            gender = "Male",
            subject = "Arts",
            currentAddress;

    @BeforeEach
    void prepareTestData() {
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        userEmail = faker.internet().emailAddress();
        userNumber = faker.phoneNumber().subscriberNumber(10);
        day = faker.number().numberBetween(10, 30) + "";
        month = "July";
        year = faker.number().numberBetween(1980, 1999) + "";
        currentAddress = faker.address().fullAddress();
    }

    @Test
    // Заполнение формы данными
    void practiceFormTest() {
        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setNumber(userNumber)
                .setBirthDate(day, month, year)
                .setSubject(subject)
                .setHobbie()
                .setAddress(currentAddress)
                .uploadPicture("src/test/resources/123.jpg")
                .setState(state)
                .setCity(city)
                .submit();

        // Проверка ответа
        String expectedName = format("%s %s", firstName, lastName);
        String expectedBirthDate = format("%s %s,%s", day, month, year);
        String expectedStateCity = format("%s %s", state, city);

        registrationFormPage.checkVisible();
        registrationFormPage.checkResult("Student Name", expectedName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", expectedBirthDate)
                .checkResult("Subjects", subject)
                .checkResult("Hobbies", "Sports")
                .checkResult("Picture", "123.jpg")
                .checkResult("Address", currentAddress)
                .checkResult("State and City", expectedStateCity);
    }
}