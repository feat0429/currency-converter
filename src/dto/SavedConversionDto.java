package dto;

import utils.constants.CurrencyCode;

public record SavedConversionDto(
    String conversionTime,
    CurrencyCode baseCurrencyCode,
    CurrencyCode targetCurrencyCode,
    double valueToConvert,
    double conversionResult
    ) {
}
