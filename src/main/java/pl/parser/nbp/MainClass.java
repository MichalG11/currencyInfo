package pl.parser.nbp;

import pl.parser.nbp.controllers.MainController;

public class MainClass {
    public static void main(String[] args) {

        try {

            new MainController().start();

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }

    }
}