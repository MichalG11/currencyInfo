package pl.parser.nbp.models.services;

import pl.parser.nbp.models.ConfigModel;
import pl.parser.nbp.models.dto.OneYearFinancialParameter;

import java.util.List;

public class AverageService {

    public double getAverageForBuyingRate(List<OneYearFinancialParameter> listOfOneYearFinParameters) {
        double totalSumOfBuyingRate = getTotalSumOfBuyingRates(listOfOneYearFinParameters);
        double totalQuantityOfRates = getTotalQuantityOfRates(listOfOneYearFinParameters);

        double averageValueForBuying = (totalSumOfBuyingRate / totalQuantityOfRates) * ConfigModel.RESULT_PRECISION;
        averageValueForBuying = Math.round(averageValueForBuying);
        averageValueForBuying /= ConfigModel.RESULT_PRECISION;
        return averageValueForBuying;
    }

    public double getAverageForSellingRate(List<OneYearFinancialParameter> listOfOneYearFinParameters) {
        double totalSumOfSellingRate = getTotalSumOfSellingRates(listOfOneYearFinParameters);
        double totalQuantityOfRates = getTotalQuantityOfRates(listOfOneYearFinParameters);

        return totalSumOfSellingRate / totalQuantityOfRates;
    }

    public double getTotalQuantityOfRates(List<OneYearFinancialParameter> listOfOneYearFinParameters){
        double totalQuantityOfRates = 0;

        for (int i = 0; i < listOfOneYearFinParameters.size(); i++) {
            totalQuantityOfRates += listOfOneYearFinParameters.get(i).getQuantityOfRates();
        }
        return totalQuantityOfRates;
    }

    public double getTotalSumOfBuyingRates(List<OneYearFinancialParameter> listOfOneYearFinParameters){
        double totalSumOfBuyingRates = 0;

        for (int i = 0; i < listOfOneYearFinParameters.size(); i++) {
            totalSumOfBuyingRates += listOfOneYearFinParameters.get(i).getSumOfBuyingRate();
        }
        return totalSumOfBuyingRates;
    }

    public double getTotalSumOfSellingRates(List<OneYearFinancialParameter> listOfOneYearFinParameters){
        double totalSumOfSellingRates = 0;

        for (int i = 0; i < listOfOneYearFinParameters.size(); i++) {
            totalSumOfSellingRates += listOfOneYearFinParameters.get(i).getSumOfSellingRate();
        }
        return totalSumOfSellingRates;
    }

}
