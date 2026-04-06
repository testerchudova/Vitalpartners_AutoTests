package pages;

import com.codeborne.selenide.SelenideElement;
import components.ResultsComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PricesPage {
    private final ResultsComponent resultsComponent = new ResultsComponent();

    private final SelenideElement
            nameInput = $("input[name='name']"),
            companyInput = $("input[name='company']"),
            searchForInput = $("input[name='searchfor']"),
            phoneInput = $("input[name='phone']"),
            emailInput = $("input[name='email']"),
            submitButton = $("form .vp-form-submit button"),
            errorMessage = $(".vp-hero-form-message.-error");

    @Step("Открыть страницу цен")
    public PricesPage openPage() {
        open("/prices/");
        return this;
    }

    @Step("Ввести имя: {value}")
    public PricesPage setName(String value) {
        nameInput.setValue(value);
        return this;
    }

    @Step("Ввести название компании: {value}")
    public PricesPage setCompany(String value) {
        companyInput.setValue(value);
        return this;
    }

    @Step("Ввести кого нужно найти: {value}")
    public PricesPage setSearchSubject(String value) {
        searchForInput.setValue(value);
        return this;
    }

    @Step("Ввести номер телефона: {value}")
    public PricesPage setPhone(String value) {
        phoneInput.setValue(value);
        return this;
    }

    @Step("Ввести email: {value}")
    public PricesPage setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    @Step("Нажать кнопку 'Отправить'")
    public void submitForm() {
        submitButton.scrollTo();
        executeJavaScript("arguments[0].click();", submitButton);
    }

    @Step("Проверить, что поле 'Кого нужно найти' является обязательным")
    public PricesPage checkSearchForFieldIsRequired() {
        searchForInput.shouldHave(attribute("required"));
        return this;
    }

    @Step("Проверить, что сообщение об успехе НЕ отображается")
    public void checkSuccessNotVisible() {
        $(".vp-hero-form-message-text").shouldNotBe(visible);
    }

    @Step("Проверить успешный результат: {expectedText}")
    public PricesPage checkSuccessResult(String expectedText) {
        resultsComponent.checkVisibleResult(expectedText);
        return this;
    }

    @Step("Нажать кнопку 'Вернуться'")
    public PricesPage returnToForm() {
        resultsComponent.clickReturnButton();
        return this;
    }

    @Step("Проверить, что все поля формы пусты")
    public PricesPage checkFieldsAreEmpty() {
        nameInput.shouldBe(empty);
        companyInput.shouldBe(empty);
        searchForInput.shouldBe(empty);
        phoneInput.shouldBe(empty);
        emailInput.shouldBe(empty);
        return this;
    }

    @Step("Проверить, что отображается сообщение об ошибке")
    public void checkErrorIsVisible() {
        errorMessage.shouldBe(visible);
    }

    @Step("Проверить, что поле Имя помечено как обязательное")
    public PricesPage checkNameIsRequired() {
        nameInput.shouldHave(attribute("required"));
        return this;
    }

    @Step("Проверить, что поле Email помечено как обязательное")
    public PricesPage checkEmailIsRequired() {
        emailInput.shouldHave(attribute("required"));
        return this;
    }

    @Step("Проверить, что браузер видит ошибку в формате Email")
    public void checkEmailIsInvalid() {

        Boolean isValid = executeJavaScript("return arguments[0].checkValidity();", emailInput);

        if (Boolean.TRUE.equals(isValid)) {
            throw new AssertionError("Email должен быть невалидным, но браузер считает его верным!");
        }
    }
}