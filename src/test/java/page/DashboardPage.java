package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import page.elements.PlasticCardElement;
import utils.PlasticCard;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private final ElementsCollection cards = $$(".list__item div");
    private final PlasticCardElement[] cardElements;

    public DashboardPage(){
        $("[data-test-id='dashboard']").shouldBe(Condition.exist);
        cardElements = getCardsElements();
    }

    private PlasticCardElement[] getCardsElements(){
        PlasticCardElement[] result = new PlasticCardElement[cards.size()];
        for(int i = 0; i < cards.size(); i++){
            result[i] = new PlasticCardElement(cards.get(i));
        }
        return result;
    }

    public int getBalance(PlasticCard card) throws Exception {
        var cardElement = getCardOrNull(card);

        if (cardElement == null){
            throw new Exception("Card not found " + card.getCardNo());
        }

        return cardElement.getBalance();
    }

    public TransferPage selectCard(PlasticCard card) throws Exception {
        var cardElement = getCardOrNull(card);

        if (cardElement == null){
            throw new Exception("Card not found " + card.getCardNo());
        }

        cardElement.clickTransferButton();
        return new TransferPage();
    }

    private PlasticCardElement getCardOrNull(PlasticCard card){
        for (PlasticCardElement element : cardElements){
            if (card.getCardNo().equals(element.getCardNo())){
                return element;
            }
        }
        return null;
    }
}
