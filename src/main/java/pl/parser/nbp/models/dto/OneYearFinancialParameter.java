package pl.parser.nbp.models.dto;

import java.util.Arrays;
import java.util.Objects;

public class OneYearFinancialParameter {

    private double sumOfBuyingRate;
    private Double[] tableOfSellingRate;
    private double sumOfSellingRate;
    private double quantityOfRates;

    public OneYearFinancialParameter(double sumOfBuyingRate, Double[] tableOfSellingRate, double sumOfSellingRate,
                                     double quantityOfRates) {
        this.sumOfBuyingRate = sumOfBuyingRate;
        this.tableOfSellingRate = tableOfSellingRate;
        this.sumOfSellingRate = sumOfSellingRate;
        this.quantityOfRates = quantityOfRates;
    }

    public double getSumOfBuyingRate() {
        return sumOfBuyingRate;
    }

    public Double[] getTableOfSellingRate() {
        return tableOfSellingRate;
    }

    public double getSumOfSellingRate() {
        return sumOfSellingRate;
    }

    public double getQuantityOfRates() {
        return quantityOfRates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneYearFinancialParameter that = (OneYearFinancialParameter) o;
        return Double.compare(that.sumOfBuyingRate, sumOfBuyingRate) == 0 &&
                Double.compare(that.sumOfSellingRate, sumOfSellingRate) == 0 &&
                Double.compare(that.quantityOfRates, quantityOfRates) == 0 &&
                Arrays.equals(tableOfSellingRate, that.tableOfSellingRate);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(sumOfBuyingRate, sumOfSellingRate, quantityOfRates);
        result = 31 * result + Arrays.hashCode(tableOfSellingRate);
        return result;
    }

    @Override
    public String toString() {
        return "OneYearFinancialParameter{" +
                "sumOfBuyingRate=" + sumOfBuyingRate +
                ", tableOfSellingRate=" + Arrays.toString(tableOfSellingRate) +
                ", sumOfSellingRate=" + sumOfSellingRate +
                ", quantityOfRates=" + quantityOfRates +
                '}';
    }
}
