package pl.parser.nbp.models.services;

import java.util.Optional;

public class XmlIndexService {
    private DownloadDataService downloadDataService = DownloadDataService.getInstance();


    public int getXmlIndexForSelectedDate(String selectedDate) {

        String shortSelectedDate = selectedDate.substring(2).replace("-", "");
        Optional<String> xmlNameForSelectedDateOptional = downloadDataService
                .getListOfXmlForSelectedYear(selectedDate.substring(0, 4))
                .stream()
                .filter(s -> s.endsWith(shortSelectedDate))
                .map(s -> s.substring(1, 4))
                .findAny();

        if (!xmlNameForSelectedDateOptional.isPresent()) {
            return -1;
        }
        return (Integer.parseInt(xmlNameForSelectedDateOptional.get())) - 1;
    }


    public int getLastXmlIndexForSelectedYear(String selectedYear) {
        return downloadDataService.getListOfXmlForSelectedYear(selectedYear).size() - 1;
    }

}
