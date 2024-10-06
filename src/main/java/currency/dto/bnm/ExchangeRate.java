package currency.dto.bnm;

public class ExchangeRate {

        public static String findExchangeRate(ValCurs valCurs, String charCode) {
            for (Valute valute : valCurs.getValutes()) {
                if (valute.getCharCode().equalsIgnoreCase(charCode)) {
                    return "The exchange rate for " + charCode + " is: " + valute.getValue();
                }
            }
            return "Currency " + charCode + "is not found.";
        }
    }
