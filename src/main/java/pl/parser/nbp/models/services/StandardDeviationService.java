package pl.parser.nbp.models.services;

import pl.parser.nbp.models.ConfigModel;
import pl.parser.nbp.models.dto.OneYearFinancialParameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StandardDeviationService {
    private AverageService averageService;

    public StandardDeviationService() {
        averageService = new AverageService();
    }


    public double getStandardDeviationForSellingRate(List<OneYearFinancialParameter> listOfFinParForOneYear) {
        double totalQuantityOfRates = averageService.getTotalQuantityOfRates(listOfFinParForOneYear);
        double averageValueForSelling = averageService.getAverageForSellingRate(listOfFinParForOneYear);
        int totalListOfFinParSize = listOfFinParForOneYear.size();
        List<Double> totalListOfSellingRates = new ArrayList<>();

        for (int i = 0; i < totalListOfFinParSize; i++) {
            totalListOfSellingRates.addAll(Arrays.asList(listOfFinParForOneYear.get(i).getTableOfSellingRate()));
        }
        double counter = 0;
        for (int i = 0; i < totalQuantityOfRates; i++) {
            counter += (totalListOfSellingRates.get(i) - averageValueForSelling) *
                    (totalListOfSellingRates.get(i) - averageValueForSelling);
        }
        double standardDeviationForSelling = Math.sqrt(counter / totalQuantityOfRates)*ConfigModel.RESULT_PRECISION;
        standardDeviationForSelling = Math.round(standardDeviationForSelling);
        standardDeviationForSelling /= ConfigModel.RESULT_PRECISION;
        return standardDeviationForSelling;
    }

}