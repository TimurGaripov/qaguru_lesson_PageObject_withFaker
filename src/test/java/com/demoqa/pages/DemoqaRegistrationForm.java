package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponent;
import com.demoqa.pages.components.ResultsModal;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoqaRegistrationForm {

    private final static String TITLE_TEXT = "Student Registration Form";
    private CalendarComponent calendarComponent = new CalendarComponent();
    private ResultsModal resultsModal = new ResultsModal();
    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            numberInput = $("#userNumber"),
            birthInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbieInput = $("[for=hobbies-checkbox-1]"),
            adressInput = $("#currentAddress"),
            pictureInput = $("#uploadPicture"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            stateCityWrapper = $("#stateCity-wrapper"),
            submitButton = $("#submit");

    public DemoqaRegistrationForm openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    public DemoqaRegistrationForm setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public DemoqaRegistrationForm setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public DemoqaRegistrationForm setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public DemoqaRegistrationForm setGender(String value) {
        genderInput.$(byText(value)).click();
        return this;
    }

    public DemoqaRegistrationForm setNumber(String value) {
        numberInput.setValue(value);
        return this;
    }

    public DemoqaRegistrationForm setBirthDate(String day, String month, String year) {
        birthInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public DemoqaRegistrationForm setSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    public DemoqaRegistrationForm setHobbie() {
        hobbieInput.click();
        return this;
    }

    public DemoqaRegistrationForm setAddress(String value) {
        adressInput.setValue(value);
        return this;
    }

    public DemoqaRegistrationForm uploadPicture(String value) {
        pictureInput.uploadFile(new File(value));
        return this;
    }

    public DemoqaRegistrationForm setState(String value) {
        stateInput.click();
        stateCityWrapper.$(byText(value)).click();
        return this;
    }

    public DemoqaRegistrationForm setCity(String value) {
        cityInput.click();
        stateCityWrapper.$(byText(value)).click();
        return this;
    }

    public DemoqaRegistrationForm submit() {
        submitButton.click();
        return this;
    }
    // Проверка ответа
    public DemoqaRegistrationForm checkVisible() {
        resultsModal.checkVisible();
        return this;
    }

    public DemoqaRegistrationForm checkResult(String key, String value) {
        resultsModal.checkResult(key, value);
        return this;
    }
}
