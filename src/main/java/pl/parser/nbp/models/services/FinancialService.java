package pl.parser.nbp.models.services;

import pl.parser.nbp.models.dto.OneYearFinancialParameter;

import java.util.ArrayList;
import java.util.List;

public class FinancialService {
    private InputService inputService;
    private DownloadDataService downloadDataService = DownloadDataService.getInstance();
    private XmlIndexService xmlIndexService;

    public FinancialService() {
        inputService = new InputService();
        xmlIndexService = new XmlIndexService();
    }


    public List<OneYearFinancialParameter> getListOfOneYearFinParameters(String currencyCode, String startDate,
                                                                         String endDate) {

        List<OneYearFinancialParameter> listOfOneYearFinParameters = new ArrayList<>();
        List<Integer> listOfYears = inputService.getListOfYears(startDate, endDate);
        int length = listOfYears.size();

        if (length == 1) {
            listOfOneYearFinParameters.add(getFinParForOnlyOneYear(currencyCode, startDate, endDate));
        }

        if (length == 2) {
            listOfOneYearFinParameters.add(getFinParForOnlyFirstYear(currencyCode, startDate));
            listOfOneYearFinParameters.add(getFinParForOnlyLastYear(currencyCode, endDate));
        }

        if (length >= 3) {
            listOfOneYearFinParameters.add(getFinParForOnlyFirstYear(currencyCode, startDate));

            for (int i = 1; i < length-1; i++) {
                String middleYear = listOfYears.get(i).toString();
                listOfOneYearFinParameters.add(getFinParForOnlyOneMiddleYear(currencyCode, middleYear));
            }

            listOfOneYearFinParameters.add(getFinParForOnlyLastYear(currencyCode, endDate));
        }

        return listOfOneYearFinParameters;
    }

    private OneYearFinancialParameter getFinParForOnlyOneYear(String currencyCode, String startDate, String endDate) {
        int startIndex = xmlIndexService.getXmlIndexForSelectedDate(startDate);
        int endIndex = xmlIndexService.getXmlIndexForSelectedDate(endDate);
        String year = startDate.substring(0,4);

        OneYearFinancialParameter finParForOnlyOneYear = downloadDataService.getFinParForOneYear(currencyCode, year,
                startIndex, endIndex).get();

        return finParForOnlyOneYear;
    }

    private OneYearFinancialParameter getFinParForOnlyFirstYear(String currencyCode, String startDate) {
        int startIndex = xmlIndexService.getXmlIndexForSelectedDate(startDate);
        String year = startDate.substring(0,4);
        int endIndex = xmlIndexService.getLastXmlIndexForSelectedYear(year);

        OneYearFinancialParameter finParForOnlyFirstYear = downloadDataService.getFinParForOneYear(currencyCode, year,
                startIndex, endIndex).get();

        return finParForOnlyFirstYear;
    }

    private OneYearFinancialParameter getFinParForOnlyLastYear(String currencyCode, String endDate) {
        int startIndex = 0;
        String year = endDate.substring(0,4);
        int endIndex = xmlIndexService.getXmlIndexForSelectedDate(endDate);

        OneYearFinancialParameter finParForOnlyLastYear = downloadDataService.getFinParForOneYear(currencyCode, year,
                startIndex, endIndex).get();

        return finParForOnlyLastYear;
    }

    private OneYearFinancialParameter getFinParForOnlyOneMiddleYear(String currencyCode, String middleYear) {
        int startIndex = 0;
        int endIndex = xmlIndexService.getLastXmlIndexForSelectedYear(middleYear);

        OneYearFinancialParameter finParForOnlyOneMiddleYear = downloadDataService.getFinParForOneYear(currencyCode,
                middleYear, startIndex, endIndex).get();

        return finParForOnlyOneMiddleYear;
    }

}
