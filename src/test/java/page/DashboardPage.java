package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import utils.DataHelper;
import utils.PlasticCard;

import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final int firstSymbolOfFourCardChars = 15;
    private final ElementsCollection cards = $$(".list__item div");

    public DashboardPage(){
        $("[data-test-id='dashboard']").shouldBe(Condition.exist);
    }

    public PlasticCard[] getCards(){
        PlasticCard[] result = new PlasticCard[cards.size()];

        for(int i = 0; i < cards.size(); i++){
            var cardItemCaption = cards.get(i).getText();
            var amountOfCard = extractBalance(cardItemCaption);
            var cardNo = extractCardNo(cardItemCaption);
            var elementId = cards.get(i).getAttribute("data-test-id");

            result[i] = new PlasticCard(elementId, cardNo, amountOfCard);
        }
        return result;
    }

    private String extractCardNo(String text){
        return DataHelper.getFirstPartOfCard() + text.substring(firstSymbolOfFourCardChars, firstSymbolOfFourCardChars + 4);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public TransferPage selectCard(PlasticCard card) {
        $("[data-test-id='" + card.getTestId() + "'] button").click();
        return new TransferPage();
    }
}
