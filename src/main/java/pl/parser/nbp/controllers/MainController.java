package pl.parser.nbp.controllers;

import pl.parser.nbp.models.InputModel;
import pl.parser.nbp.models.services.InputService;
import pl.parser.nbp.views.MainView;

import java.util.Scanner;

public class MainController {

    private Scanner scanner;
    private MainView mainView;
    private InputService inputService;

    public MainController() {
        scanner = new Scanner(System.in);
        mainView = new MainView();
        inputService = new InputService();
    }


    public void start() {
        mainView.showWelcomeText();

        String input = scanner.nextLine();
        String[] inputTab = input.split(" ");

        if (inputTab.length != 3) {
            throw new IllegalArgumentException("Niepoprawna ilość danych!");
        }

        InputModel inputModel = inputService.getInputModel(inputTab);

        String currencyCode = inputModel.getCurrencyCode().toUpperCase();
        String startDate = inputModel.getStartDate();
        String endDate = inputModel.getEndDate();

        new ErrorController().startErrorControl(currencyCode, startDate, endDate);

        new DownloadController().startDownloadData(currencyCode, startDate, endDate);
    }
}