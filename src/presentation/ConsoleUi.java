package presentation;

import dto.SavedConversionDto;
import models.CurrencyConverter;
import utils.constants.Currency;
import utils.constants.UiPrompts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUi {
    private final Scanner scanner;
    private final CurrencyConverter converter;

    public ConsoleUi(Scanner scanner, CurrencyConverter converter){
        this.scanner= scanner;
        this.converter = converter;
    }

    public void displayMenu(){
        System.out.println(UiPrompts.MENU);
    }

    public void convertCurrency(Currency baseCurrency, Currency targetCurrency) throws IOException, InterruptedException {
        System.out.println(UiPrompts.INPUT_REQUEST);

        double valueToConvert = scanner.nextDouble();
        double convertedValue = converter.convertValue(valueToConvert, baseCurrency, targetCurrency);

        System.out.println(UiPrompts.RESULT_MESSAGE + convertedValue);
        scanner.nextLine();
        System.out.println(UiPrompts.CONTINUE_REQUEST);
        scanner.nextLine();
    }

    public void displayConversionHistory() throws IOException {
        ArrayList<SavedConversionDto> conversions = converter.readConversions();
        for (SavedConversionDto conversion : conversions){
            System.out.println(conversion.conversionTime() + " - From " +
                    conversion.valueToConvert() + " " + conversion.baseCurrencyCode() +
                    " to " + conversion.conversionResult() + " " + conversion.targetCurrencyCode() + ".");
        }
        scanner.nextLine();

        System.out.println(UiPrompts.CONTINUE_REQUEST);
        scanner.nextLine();
    }

    public void displayExitMessage(){
        System.out.println(UiPrompts.EXIT_MESSAGE);
    }

    public void displayInvalidOptionMessage(){
        System.out.println(UiPrompts.INVALID_OPTION);
        scanner.nextLine();
        System.out.println(UiPrompts.CONTINUE_REQUEST);
        scanner.nextLine();    }
}
