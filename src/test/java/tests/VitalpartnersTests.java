package tests;

import io.qameta.allure.Allure;
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

        Allure.step("Заполнить форму всеми валидными данными", () -> {
            pricesPage.openPage()
                    .setName(data.fullName)
                    .setCompany(data.company)
                    .setSearchSubject(data.searchSubject)
                    .setPhone(data.phone)
                    .setEmail(data.email);
        });

        Allure.step("Отправить форму", pricesPage::submitForm);

        Allure.step("Проверить успех и нажать 'Вернуться'", () -> {
            pricesPage.checkSuccessResult("Запрос отправлен")
                    .returnToForm();
        });

        Allure.step("Проверить, что форма очищена и готова к новому вводу", () -> {
            pricesPage.checkFieldsAreEmpty();
        });
    }

    @Test
    @Tag("Negative")
    @DisplayName("Ошибка при пустом поле 'Кого нужно найти'")
    void negativeEmptySearchSubjectTest() {
        TestData data = new TestData();

        Allure.step("Заполнить все поля, кроме 'Кого нужно найти'", () -> {
            pricesPage.openPage()
                    .setName(data.fullName)
                    .setCompany(data.company)
                    .setPhone(data.phone)
                    .setEmail(data.email);
        });

        Allure.step("Нажать кнопку отправить", pricesPage::submitForm);

        Allure.step("Проверить валидацию поля", () -> {
            pricesPage
                    .checkSearchForFieldIsRequired()
                    .checkSuccessNotVisible();
        });
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

        Allure.step("Проверить, что браузер заблокировал отправку", () -> {
            pricesPage.checkEmailIsInvalid();
            pricesPage.checkSuccessNotVisible();
        });
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

        Allure.step("Ввести текст в поле телефона", () -> {
            pricesPage.openPage()
                    .setName(data.fullName)
                    .setPhone(data.fullName) // Передаем имя вместо цифр
                    .setEmail(data.email)
                    .setSearchSubject(data.searchSubject);
        });

        Allure.step("Отправить форму и проверить ошибку", () -> {
            pricesPage.submitForm();
            pricesPage.checkErrorIsVisible();
        });
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
        Allure.step("Открыть страницу и сразу нажать отправить", () -> {
            pricesPage.openPage()
                    .submitForm();
        });

        Allure.step("Проверить, что поля помечены как обязательные и форма не ушла", () -> {
            pricesPage.checkNameIsRequired()
                    .checkSearchForFieldIsRequired()
                    .checkSuccessNotVisible();
        });
    }
}