package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import utils.PlasticCard;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private static final SelenideElement amountInput = $("[data-test-id='amount'] input");
    private static final SelenideElement fromInput = $("[data-test-id='from'] input");
    private static final SelenideElement toInput = $("[data-test-id='to'] input");
    private static final SelenideElement buttonTransferAction = $("[data-test-id='action-transfer']");
    private static final SelenideElement errorMessage = $("[data-test-id='error-notification']");

    public DashboardPage transferFrom(PlasticCard card, int amount){
        fromInput.setValue(card.getCardNo());
        amountInput.setValue(String.valueOf(amount));
        buttonTransferAction.click();

        errorMessage.shouldBe(Condition.hidden);

        return new DashboardPage();
    }

    public void checkVisibleErrorMessage(){
        errorMessage.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }
}
