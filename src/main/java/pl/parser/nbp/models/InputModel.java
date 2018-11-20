package pl.parser.nbp.models;

public class InputModel {

    private String currencyCode;
    private String startDate;
    private String endDate;

    public InputModel(String currencyCode, String startDate, String endDate) {
        this.currencyCode = currencyCode;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

}
