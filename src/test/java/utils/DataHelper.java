package utils;

import java.util.Random;

public class DataHelper {

    private final static String validPassCode = "12345";
    private final static String firstPartOfAllCards = "5559 0000 0000 ";

    private DataHelper() {
    }

    public static String getFirstPartOfCard(){
        return firstPartOfAllCards;
    }

    public static int generateValidAmount(int amount){
        return new Random().nextInt(Math.abs(amount)) + 1;
    }

    public static UserCredential getValidUser() {
        return new UserCredential("vasya", "qwerty123");
    }

    public static String getValidVerificationPassCode() {
        return validPassCode;
    }


}
