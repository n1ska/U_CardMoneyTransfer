import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;
import utils.DataHelper;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferMoneyTests {
    private DashboardPage sut;
    final int indexOfFirstCard = 0;
    final int indexOfSecondCard = 1;

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.login(DataHelper.getValidUser());
        sut = verificationPage.putPassCode(DataHelper.getValidVerificationPassCode());
    }

    @Test
    public void testValidTransfer_Success(){
        var cards = sut.getCards();
        var firstCard = cards[indexOfFirstCard];
        var secondCard = cards[indexOfSecondCard];

        var amountForTransfer = DataHelper.generateValidAmount(firstCard.getAmount());
        var expectedAmountForFirstCard = firstCard.getAmount() + amountForTransfer;
        var expectedAmountForSecondCard = secondCard.getAmount() - amountForTransfer;

        var transferPage = sut.selectCard(firstCard);
        var dashBoard = transferPage.transferFrom(secondCard, amountForTransfer);

        var updatedCards = dashBoard.getCards();
        var actualAmountForFirstCard = updatedCards[indexOfFirstCard].getAmount();
        var actualAmountForSecondCard = updatedCards[indexOfSecondCard].getAmount();

        assertEquals(expectedAmountForFirstCard, actualAmountForFirstCard);
        assertEquals(expectedAmountForSecondCard, actualAmountForSecondCard);
    }
}
