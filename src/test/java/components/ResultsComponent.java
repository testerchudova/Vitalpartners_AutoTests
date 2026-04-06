package components;

import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ResultsComponent {

        private final String
            successMessageText = ".vp-hero-form-message-text",
            returnButton = ".vp-hero-form-message-rtn button";

    @Step("Проверить, что отображается сообщение: {value}")
    public ResultsComponent checkVisibleResult(String value) {
        $(successMessageText)
                .shouldBe(visible)
                .shouldHave(text(value));
        return this;
    }

    @Step("Нажать кнопку 'Вернуться'")
    public void clickReturnButton() {
        $(returnButton).click();
    }
}