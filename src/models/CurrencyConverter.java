package models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.ConversionRateDto;
import dto.SavedConversionDto;
import service.ConversionRateService;
import utils.constants.CurrencyCode;

import java.io.*;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.ArrayList;

public class CurrencyConverter {
    private final Gson gson;
    private final String fileName = "conversions.txt";
    private final ConversionRateService conversionRateService;

    public CurrencyConverter(ConversionRateService service, Gson gson){
        this.gson = gson;
        this.conversionRateService = service;
    }

    public double convertValue(double valueToConvert, CurrencyCode baseCurrencyCode, CurrencyCode targetCurrencyCode) throws IOException, InterruptedException {
        ConversionRateDto conversionRateDto = conversionRateService.getConversionRate(baseCurrencyCode.name(), targetCurrencyCode.name());
        double conversionResult = valueToConvert * conversionRateDto.conversionRate();

        writeConversion(new SavedConversionDto(
                Instant.now().toString(),
                baseCurrencyCode,
                targetCurrencyCode,
                valueToConvert,
                conversionResult
        ));


        return conversionResult;
    }

    private void writeConversion(SavedConversionDto conversionDto) throws IOException {
        ArrayList<SavedConversionDto> storedConversions = readConversions();
        storedConversions.add(conversionDto);

        FileWriter writer = new FileWriter(this.fileName);
        writer.write(gson.toJson(storedConversions));

        writer.close();
    }

    public ArrayList<SavedConversionDto> readConversions() throws IOException {
        Reader reader = new FileReader(fileName);
        Type conversionsType = new TypeToken<ArrayList<SavedConversionDto>>(){}
                .getType();

        ArrayList<SavedConversionDto> conversions = gson.fromJson(reader, conversionsType);

        reader.close();
        return conversions == null ? new ArrayList<>() : conversions;
    }
}
