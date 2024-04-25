import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.SavedConversionDto;
import models.CurrencyConverter;
import service.ConversionRateService;
import utils.constants.CurrencyCode;
import utils.constants.UiPrompts;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        ConversionRateService conversionRateService = new ConversionRateService(gson, client);
        CurrencyConverter currencyConverter = new CurrencyConverter(conversionRateService, gson);

        double valueToConvert;
        double convertedValue;
        int option = 0;
        final int exitOption = 8;
        Scanner scanner = new Scanner(System.in);

        while (option != exitOption){
            System.out.println(UiPrompts.MENU);
            option = scanner.nextInt();

            try{
                switch (option){
                    case 1:
                        System.out.println(UiPrompts.INPUT_REQUEST);

                        valueToConvert = scanner.nextDouble();
                        convertedValue = currencyConverter.convertValue(valueToConvert, CurrencyCode.USD,CurrencyCode.ARS);

                        System.out.println(UiPrompts.RESULT_MESSAGE + convertedValue);
                        scanner.nextLine();
                        System.out.println(UiPrompts.CONTINUE_REQUEST);
                        scanner.nextLine();
                        break;
                    case 2:
                        System.out.println(UiPrompts.INPUT_REQUEST);

                        valueToConvert = scanner.nextDouble();
                        convertedValue = currencyConverter.convertValue(valueToConvert, CurrencyCode.ARS,CurrencyCode.USD);

                        System.out.println(UiPrompts.RESULT_MESSAGE + convertedValue);
                        scanner.nextLine();

                        System.out.println(UiPrompts.CONTINUE_REQUEST);
                        scanner.nextLine();
                        break;
                    case 3:
                        System.out.println(UiPrompts.INPUT_REQUEST);

                        valueToConvert = scanner.nextDouble();
                        convertedValue = currencyConverter.convertValue(valueToConvert, CurrencyCode.USD,CurrencyCode.BRL);

                        System.out.println(UiPrompts.RESULT_MESSAGE + convertedValue);
                        scanner.nextLine();

                        System.out.println(UiPrompts.CONTINUE_REQUEST);
                        scanner.nextLine();
                        break;
                    case 4:
                        System.out.println(UiPrompts.INPUT_REQUEST);

                        valueToConvert = scanner.nextDouble();
                        convertedValue = currencyConverter.convertValue(valueToConvert, CurrencyCode.BRL,CurrencyCode.USD);

                        System.out.println(UiPrompts.RESULT_MESSAGE + convertedValue);
                        scanner.nextLine();

                        System.out.println(UiPrompts.CONTINUE_REQUEST);
                        scanner.nextLine();
                        break;
                    case 5:
                        System.out.println(UiPrompts.INPUT_REQUEST);

                        valueToConvert = scanner.nextDouble();
                        convertedValue = currencyConverter.convertValue(valueToConvert, CurrencyCode.USD,CurrencyCode.COP);

                        System.out.println(UiPrompts.RESULT_MESSAGE + convertedValue);
                        scanner.nextLine();

                        System.out.println(UiPrompts.CONTINUE_REQUEST);
                        scanner.nextLine();
                        break;
                    case 6:
                        System.out.println(UiPrompts.INPUT_REQUEST);

                        valueToConvert = scanner.nextDouble();
                        convertedValue = currencyConverter.convertValue(valueToConvert, CurrencyCode.COP,CurrencyCode.USD);

                        System.out.println(UiPrompts.RESULT_MESSAGE + convertedValue);
                        scanner.nextLine();

                        System.out.println(UiPrompts.CONTINUE_REQUEST);
                        scanner.nextLine();
                        break;
                    case 7:
                        ArrayList<SavedConversionDto> conversions = currencyConverter.readConversions();
                        for (SavedConversionDto conversion : conversions){
                            System.out.println(conversion.conversionTime() + " - From " +
                                    conversion.valueToConvert() + " " + conversion.baseCurrencyCode() +
                                    " to " + conversion.conversionResult() + " " + conversion.targetCurrencyCode() + ".");
                        }
                        scanner.nextLine();

                        System.out.println(UiPrompts.CONTINUE_REQUEST);
                        scanner.nextLine();
                        break;
                    case exitOption:
                        System.out.println(UiPrompts.EXIT_MESSAGE);
                        break;
                    default:
                        System.out.println(UiPrompts.INVALID_OPTION);
                        scanner.nextLine();
                        System.out.println(UiPrompts.CONTINUE_REQUEST);
                        scanner.nextLine();
                        break;
                }
            } catch (Exception e){
                System.out.println(Arrays.toString(e.getStackTrace()));
            }

        }


    }
}