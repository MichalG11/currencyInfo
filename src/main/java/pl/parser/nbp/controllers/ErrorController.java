package pl.parser.nbp.controllers;

import pl.parser.nbp.models.services.*;

public class ErrorController {
    private DownloadDataService downloadDataService = DownloadDataService.getInstance();
    private InputService inputService;
    private XmlIndexService xmlIndexService;

    public ErrorController(){
        inputService = new InputService();
        xmlIndexService = new XmlIndexService();
    }


    public void startErrorControl(String currencyCode, String startDate, String endDate) {

        if (!inputService.checkFormatOfDate(startDate, endDate)) {
            throw new IllegalArgumentException("Data nie jest w formacie: YYYY-MM-DD");
        }

        if (!inputService.checkFormatOfCurrency(currencyCode)) {
            throw new IllegalArgumentException("Niepoprawna waluta!");
        }

        if(!inputService.checkSequenceOfDates(startDate, endDate)) {
            throw new IllegalArgumentException("Niepoprawna kolejnosc dat!");
        }

        if (downloadDataService.getListOfXmlForSelectedYear(startDate.substring(0, 4)) == null ||
                downloadDataService.getListOfXmlForSelectedYear(endDate.substring(0, 4)) == null) {
            throw new IllegalArgumentException("Brak danych dla zadanego roku!");
        }

        if (xmlIndexService.getXmlIndexForSelectedDate(startDate) == -1 ||
                xmlIndexService.getXmlIndexForSelectedDate(endDate) == -1) {
            throw new IllegalArgumentException("Daty graniczne musza przypadac na dzien roboczy!");
        }
    }

}