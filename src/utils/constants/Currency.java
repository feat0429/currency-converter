package utils.constants;

public enum Currency {
    UnitedStatesDollar("USD"),
    ArgentinePeso("ARS"),
    BrazilianReal("BRL"),
    ColombianPeso("COP"),
    ChineseRenminbi("CNY");

    public final String code;

    private Currency(String code){
        this.code = code;
    }
}
