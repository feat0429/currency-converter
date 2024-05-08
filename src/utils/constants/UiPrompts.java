package utils.constants;

public final class UiPrompts {
    public static final String INPUT_REQUEST = "Enter the value that you would like to convert:";
    public static final String INVALID_OPTION = "Invalid option. Please enter a valid option displayed in the menu.";
    public static final String RESULT_MESSAGE = "The conversion result is: ";
    public static final String CONTINUE_REQUEST = "\nPress Enter key to continue.";
    public static final String EXIT_MESSAGE = "Thank you to use our service :]";


    public static final String MENU = """
            *****************************************************************************
            Welcome to the Currency Converter :]
            
                1) Dollar =>> Argentine Peso
                2) Argentine Peso =>> Dollar
                3) Dollar =>> Brazilian Real
                4) Brazilian Real =>> Dollar
                5) Dollar =>> Colombian Peso
                6) Colombian Peso =>> Dollar
                7) Dollar =>> Chinese Renminbi
                8) Chinese Renminbi =>> Dollar
                9) Get conversions history
                10) Exit
            *****************************************************************************
                Choose a valid option:
            """;

    private UiPrompts(){

    }
}
