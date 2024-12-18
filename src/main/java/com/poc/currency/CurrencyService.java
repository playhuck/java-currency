package com.poc.currency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

@Service
public class CurrencyService {

    @Value("${env.currency}")
    private String key;

    public static org.json.simple.JSONArray getRequest(String url) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        try {
            String result = restTemplate.getForObject(url, String.class);

            System.out.println(result);

            JSONParser jsonParser = new JSONParser();
            return (JSONArray) jsonParser.parse(result);
        } catch (ParseException | ResourceAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public CurrencyModel[] getExchangeRate(){

        final String authKey = key;
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());

        String urlBuilder = "https://www.koreaexim.go.kr/site/program/financial/exchangeJSON?" +
                "authkey=" + authKey + "&" +
                "searchdate=" + date + "&" +
                "data=" + CurrencyDataTypeCode.EXCHANGE_RATE.code;

        JSONArray jsonArray = getRequest(urlBuilder);

        return this.getMappingJson(jsonArray, CurrencyModel[].class);

    }

    enum CurrencyDataTypeCode {

        EXCHANGE_RATE("AP01"),
        LOAN_RATE("AP02"),
        GLOBAL_INTEREST_RATE("AP03");

        final String code;

        CurrencyDataTypeCode(String code) {
            this.code = code;
        }
    }

    public <T> T[] getMappingJson(
            JSONArray json,
            Class<T[]> gameResClass) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                false
        );

        try {
            return objectMapper.readValue(json.toJSONString(), gameResClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
