package page;

import com.codeborne.selenide.SelenideElement;
import utils.UserCredential;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginInput = $("[data-test-id='login'] input");
    private final SelenideElement passwordInput = $("[data-test-id='password'] input");
    private final SelenideElement button = $("[data-test-id='action-login']");

    public TwoFactorPage login(UserCredential credential) {
        loginInput.setValue(credential.getLogin());
        passwordInput.setValue(credential.getPassword());
        button.click();

        return new TwoFactorPage();
    }
}
