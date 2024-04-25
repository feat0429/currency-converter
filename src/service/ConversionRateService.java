package service;

import exceptions.ApiKeyNotFoundException;
import com.google.gson.*;
import dto.ConversionRateDto;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class ConversionRateService {
    private final HttpClient client;
    private String apiKey = null;
    private final Gson gson;

    public ConversionRateService(Gson gson, HttpClient client) {
        this.client = client;
        this.gson = gson;

        try {
            this.apiKey = readApiKey();
        } catch (IOException e) {
            System.out.println("Unable to read API key.");
        }
    }

    public ConversionRateDto getConversionRate(String baseCurrencyCode, String targetCurrencyCode) throws IOException, InterruptedException {
        URI url = URI.create("https://v6.exchangerate-api.com/v6/"+ this.apiKey +"/pair/"+ baseCurrencyCode +"/" + targetCurrencyCode);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();
        HttpResponse<String> response = client
            .send(request, HttpResponse.BodyHandlers.ofString());

        return gson.fromJson(response.body(), ConversionRateDto.class);
    }

    private static String readApiKey() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String apiKey = properties.getProperty("api_key");

        if(apiKey == null){
            throw new ApiKeyNotFoundException("API key was not found. Add the proper 'api_key' variable to the 'config.properties' file.");
        }

        return apiKey;
    }
}
