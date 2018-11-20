package pl.parser.nbp.models.services;

import pl.parser.nbp.models.InputModel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class InputService {

    public boolean checkFormatOfDate(String startDate, String endDate) {
        return (Pattern.matches("20[01][0-9]-[01][0-9]-[0123][0-9]", startDate) &&
                Pattern.matches("20[01][0-9]-[01][0-9]-[0123][0-9]", endDate));
    }

    public boolean checkSequenceOfDates(String startDate, String endDate) {
        return (Integer.parseInt(endDate.replace("-", "")) - Integer.parseInt(startDate.replace("-", ""))) >= 0;
    }

    public boolean checkFormatOfCurrency(String currencyCode) {
        return (currencyCode.equals("EUR") || currencyCode.equals("USD") || currencyCode.equals("CHF") ||
                currencyCode.equals("GBP"));
    }

    public List<Integer> getListOfYears(String startDate, String endDate) {
        int quantityOfYears = Integer.parseInt(endDate.substring(0,4)) - Integer.parseInt(startDate.substring(0,4)) + 1;
        List<Integer> listOfYears = new ArrayList<>();
        for (int i = 0; i < quantityOfYears; i++) {
            listOfYears.add(Integer.parseInt(startDate.substring(0,4)) + i);
        }
        return listOfYears;
    }

    public InputModel getInputModel(String[] inputTab) {
        return new InputModel(inputTab[0], inputTab[1], inputTab[2]);
    }

}
