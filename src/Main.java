import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.CurrencyConverter;
import presentation.ConsoleUi;
import service.ConversionRateService;
import utils.constants.Currency;
import utils.constants.UiPrompts;

import java.net.http.HttpClient;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();
        Scanner scanner = new Scanner(System.in);

        ConversionRateService conversionRateService = new ConversionRateService(gson, client);
        CurrencyConverter currencyConverter = new CurrencyConverter(conversionRateService, gson);
        ConsoleUi consoleUi = new ConsoleUi(scanner, currencyConverter);

        int option = 0;
        final int exitOption = 10;

        while (option != exitOption){
            try{
                consoleUi.displayMenu();
                option = scanner.nextInt();

                switch (option){
                    case 1:
                        consoleUi.convertCurrency(Currency.UnitedStatesDollar, Currency.ArgentinePeso);
                        break;
                    case 2:
                        consoleUi.convertCurrency(Currency.ArgentinePeso, Currency.UnitedStatesDollar);
                        break;
                    case 3:
                        consoleUi.convertCurrency(Currency.UnitedStatesDollar, Currency.BrazilianReal);
                        break;
                    case 4:
                        consoleUi.convertCurrency(Currency.BrazilianReal, Currency.UnitedStatesDollar);
                        break;
                    case 5:
                        consoleUi.convertCurrency(Currency.UnitedStatesDollar, Currency.ColombianPeso);
                        break;
                    case 6:
                        consoleUi.convertCurrency(Currency.ColombianPeso, Currency.UnitedStatesDollar);
                        break;
                    case 7:
                        consoleUi.convertCurrency(Currency.UnitedStatesDollar, Currency.ChineseRenminbi);
                        break;
                    case 8:
                        consoleUi.convertCurrency(Currency.ChineseRenminbi, Currency.UnitedStatesDollar);
                        break;
                    case 9:
                        consoleUi.displayConversionHistory();
                        break;
                    case exitOption:
                        consoleUi.displayExitMessage();
                        break;
                    default:
                        consoleUi.displayInvalidInputMessage();
                        break;
                }
            } catch (InputMismatchException e){
                consoleUi.displayInvalidInputMessage();
            } catch (Exception e){
                System.out.println(Arrays.toString(e.getStackTrace()));
                scanner.nextLine();
                System.out.println(UiPrompts.CONTINUE_REQUEST);
                scanner.nextLine();
            }

        }


    }
}