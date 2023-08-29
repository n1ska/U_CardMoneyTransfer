package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TwoFactorPage {

    private final SelenideElement passCodeInput = $("[data-test-id='code'] input");
    private final SelenideElement button = $("[data-test-id='action-verify']");

    public TwoFactorPage() {
        $("p").shouldBe(Condition.exactText(" Необходимо подтверждение"));
    }

    public DashboardPage putPassCode(String passCode) {
        passCodeInput.setValue(passCode);
        button.click();

        return new DashboardPage();
    }
}
