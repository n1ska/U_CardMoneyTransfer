import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;
import utils.DataHelper;
import utils.PlasticCard;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferMoneyTests {
    private DashboardPage sut;
    private final PlasticCard firstCard = DataHelper.getFirstCard();
    private final PlasticCard secondCard = DataHelper.getSecondCard();


    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.login(DataHelper.getValidUser());
        sut = verificationPage.putPassCode(DataHelper.getValidVerificationPassCode());
    }

    @Test
    public void testValidTransfer_Success() throws Exception {
        var balanceOfFirstCard = sut.getBalance(firstCard);
        var balanceOfSecondCard = sut.getBalance(secondCard);

        var amountForTransfer = DataHelper.generateValidAmount(balanceOfFirstCard);
        var expectedAmountForFirstCard = balanceOfFirstCard + amountForTransfer;
        var expectedAmountForSecondCard = balanceOfSecondCard - amountForTransfer;

        var transferPage = sut.selectCard(firstCard);
        var dashBoard = transferPage.transferFrom(secondCard, amountForTransfer);

        var actualAmountForFirstCard = dashBoard.getBalance(firstCard);
        var actualAmountForSecondCard = dashBoard.getBalance(secondCard);

        assertEquals(expectedAmountForFirstCard, actualAmountForFirstCard);
        assertEquals(expectedAmountForSecondCard, actualAmountForSecondCard);
    }
}
