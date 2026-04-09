package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.PricesPage;
import testbase.TestBaseJenk;
import testdata.TestData;

@Feature("Форма обратной связи на странице цен")
@Owner("QA_Engineer")
public class VitalpartnersTests extends TestBaseJenk {
    PricesPage pricesPage = new PricesPage();

    @Test
    @Tag("Positive")
    @Story("Успешная отправка и возврат")
    @DisplayName("Успешное заполнение всех полей и проверка очистки формы после возврата")
    void fullSuccessFlowWithReturnTest() {
        TestData data = new TestData();

        pricesPage.openPage()
                .setName(data.fullName)
                .setCompany(data.company)
                .setSearchSubject(data.searchSubject)
                .setPhone(data.phone)
                .setEmail(data.email)
                .submitForm();

        pricesPage.checkSuccessResult("Запрос отправлен")
                .returnToForm();

        pricesPage.checkFieldsAreEmpty();
    }

    @Test
    @Tag("Negative")
    @DisplayName("Ошибка при пустом поле 'Кого нужно найти'")
    void negativeEmptySearchSubjectTest() {
        TestData data = new TestData();

        pricesPage.openPage()
                .setName(data.fullName)
                .setCompany(data.company)
                .setPhone(data.phone)
                .setEmail(data.email)
                .submitForm();

        pricesPage.checkSearchForFieldIsRequired()
                .checkSuccessNotVisible();
    }

    @Test
    @Tag("Negative")
    @DisplayName("Ошибка: Некорректный формат Email (без @)")
    void negativeInvalidEmailFormatTest() {
        TestData data = new TestData();

        pricesPage.openPage()
                .setName(data.fullName)
                .setEmail("wrong_format_mail.com") // Нет собаки
                .submitForm();

        pricesPage.checkEmailIsInvalid();
        pricesPage.checkSuccessNotVisible();
    }

    @Test
    @Tag("Negative")
    @DisplayName("Ошибка: Пустое поле Email")
    void negativeEmptyEmailTest() {
        TestData data = new TestData();

        pricesPage.openPage()
                .setName(data.fullName)
                .setSearchSubject(data.searchSubject)
                .setPhone(data.phone)
                // Пропускаю setEmail
                .submitForm();

        pricesPage.checkEmailIsRequired()
                .checkSuccessNotVisible();
    }

    @Test
    @Tag("Negative")
    @Disabled("Баг: отсутствует валидация телефона")
    @DisplayName("Ошибка при вводе имени в поле телефона")
    void negativePhoneWithLettersTest() {
        TestData data = new TestData();

        pricesPage.openPage()
                .setName(data.fullName)
                .setPhone(data.fullName) // имя вместо цифр
                .setEmail(data.email)
                .setSearchSubject(data.searchSubject)
                .submitForm();

        pricesPage.checkErrorIsVisible();
    }

    @Test
    @Tag("Negative")
    @DisplayName("Ошибка: Пустое поле 'Как вас зовут'")
    void negativeEmptyNameTest() {
        TestData data = new TestData();

        pricesPage.openPage()
                .setPhone(data.phone)
                .setEmail(data.email)
                .submitForm();

        pricesPage.checkNameIsRequired()
                .checkSuccessNotVisible();
    }

    @Test
    @Tag("Negative")
    @DisplayName("Ошибка при отправке абсолютно пустой формы")
    void negativeEmptyFormTest() {
        pricesPage.openPage()
                .submitForm();

        pricesPage.checkNameIsRequired()
                .checkSearchForFieldIsRequired()
                .checkSuccessNotVisible();
    }
}