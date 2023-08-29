package page.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import page.TransferPage;
import utils.DataHelper;

public class PlasticCardElement {
    SelenideElement element;
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final int firstSymbolOfFourCardChars = 15;

    public PlasticCardElement(SelenideElement element) {
        this.element = element;
    }

    public String getCardNo(){
        return DataHelper.getFirstPartOfCard() + element.text().substring(firstSymbolOfFourCardChars, firstSymbolOfFourCardChars + 4);
    }

    public int getBalance() {
        return extractBalance(element.text());
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage clickTransferButton(){
        element.find(By.cssSelector("[data-test-id='action-deposit']")).click();
        return new TransferPage();
    }
}
