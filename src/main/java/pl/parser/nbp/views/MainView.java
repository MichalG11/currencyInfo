package pl.parser.nbp.views;

public class MainView {

    public void showWelcomeText() {
        System.out.println("Podaj walutę (EUR, USD, CHF lub GBP oraz daty graniczne:");
        System.out.println("Na przykład: eur 2013-01-28 2013-01-31");
    }

    public void showResults(double average, double standardDeviation) {
        System.out.println(average);
        System.out.println(standardDeviation);
    }
}
