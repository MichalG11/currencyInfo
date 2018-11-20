package pl.parser.nbp.models.services;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pl.parser.nbp.models.ConfigModel;
import pl.parser.nbp.models.dto.OneYearFinancialParameter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DownloadDataService {

    private static DownloadDataService INSTANCE = new DownloadDataService();

    private DownloadDataService(){
    }

    public static DownloadDataService getInstance(){
        return INSTANCE;
    }

    public List<String> getListOfXmlForSelectedYear(String selectedYear) {

        if (selectedYear.equals("2018")) {
            selectedYear = "";
        }
        String urlDirFile = ConfigModel.URL_TO_DIR + selectedYear + ConfigModel.EXTENSION_TXT;
        Optional<String> contentDirOptional = readWebsite(urlDirFile);
        if (!contentDirOptional.isPresent()) {
            return null;
        }

        String[] tableOfXmlForSelectedYear = contentDirOptional.get().split("\n");
        List<String> listOfXmlForSelectedYear;

        listOfXmlForSelectedYear = Arrays.stream(tableOfXmlForSelectedYear)
                .filter(s -> s.contains(ConfigModel.CODE_OF_TABLE_XML))
                .map(s -> s.substring(s.indexOf(ConfigModel.CODE_OF_TABLE_XML),
                                    s.indexOf(ConfigModel.CODE_OF_TABLE_XML)+11))
                .collect(Collectors.toList());

        return listOfXmlForSelectedYear;
    }


    public Optional<OneYearFinancialParameter> getFinParForOneYear(String currencyCode, String selectedYear ,
                                                                   int indexOfStartDay, int indexOfEndDay) {
        double sumOfBuyingRate = 0;
        double sumOfSellingRate = 0;

        int quantityOfRates = indexOfEndDay - indexOfStartDay + 1;

        Double[] tableOfSellingRate = new Double[quantityOfRates];
        List<String> listOfXmlForSelectedYear = getListOfXmlForSelectedYear(selectedYear);

        for (int i = indexOfStartDay; i <= indexOfEndDay ; i++) {

            String xmlName = listOfXmlForSelectedYear.get(i);
            String url = ConfigModel.URL_TO_XML + xmlName + ConfigModel.EXTENSION_XML;

            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(url);
                doc.getDocumentElement().normalize();

                NodeList nList = doc.getElementsByTagName("pozycja");

                for (int i1 = 0; i1 < nList.getLength(); i1++) {
                    Node nNode = nList.item(i1);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;

                        if (eElement.getElementsByTagName("kod_waluty").item(0).getTextContent()
                                .equals(currencyCode)) {

                            sumOfBuyingRate += Double.parseDouble(eElement
                                    .getElementsByTagName("kurs_kupna")
                                    .item(0)
                                    .getTextContent()
                                    .replace(",", "."));

                            tableOfSellingRate[i-indexOfStartDay] = Double.parseDouble(eElement
                                    .getElementsByTagName("kurs_sprzedazy")
                                    .item(0)
                                    .getTextContent()
                                    .replace(",", "."));

                            sumOfSellingRate += tableOfSellingRate[i-indexOfStartDay];
                        }
                    }
                }
            } catch (Exception e) {
                return Optional.empty();
            }
        }
        OneYearFinancialParameter finParForOneYear = new OneYearFinancialParameter(sumOfBuyingRate,
                tableOfSellingRate, sumOfSellingRate, quantityOfRates);
        return Optional.of(finParForOneYear);
    }


    private Optional<String> readWebsite(String url){
        StringBuilder stringBuilder = new StringBuilder();

        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();

            InputStream inputStream = httpURLConnection.getInputStream();

            int response = 0;
            while ((response = inputStream.read()) != -1) {

                stringBuilder.append((char) response);
            }
        }catch (IOException e){
            return Optional.empty();
        }
        return Optional.of(stringBuilder.toString());
    }

}
