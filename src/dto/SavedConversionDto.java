package dto;

public record SavedConversionDto(
    String conversionTime,
    String baseCurrencyCode,
    String targetCurrencyCode,
    double valueToConvert,
    double conversionResult
    ) {
}
