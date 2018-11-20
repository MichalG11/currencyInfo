package pl.parser.nbp.controllers;

import pl.parser.nbp.models.dto.OneYearFinancialParameter;
import pl.parser.nbp.models.services.*;
import pl.parser.nbp.views.MainView;

import java.util.List;

public class DownloadController {
    private MainView mainView;
    private FinancialService financialService;
    private AverageService averageService;
    private StandardDeviationService standardDeviationService;

    public DownloadController(){
        mainView = new MainView();
        financialService = new FinancialService();
        averageService = new AverageService();
        standardDeviationService = new StandardDeviationService();
    }


    public void startDownloadData(String currencyCode, String startDate, String endDate) {

        List<OneYearFinancialParameter> listOfOneYearFinParameters;
        double averageForBuyingRates;
        double standardDeviationForSellingRates;

        listOfOneYearFinParameters = financialService.getListOfOneYearFinParameters(currencyCode, startDate, endDate);

        averageForBuyingRates = averageService.getAverageForBuyingRate(listOfOneYearFinParameters);

        standardDeviationForSellingRates = standardDeviationService
                .getStandardDeviationForSellingRate(listOfOneYearFinParameters);

        mainView.showResults(averageForBuyingRates, standardDeviationForSellingRates);
    }

}