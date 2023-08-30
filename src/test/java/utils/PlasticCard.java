package utils;

import com.codeborne.selenide.SelenideElement;
import lombok.Value;

@Value
public class PlasticCard {
    String testId;
    String cardNo;
    int amount;
}
