package com.openpayd.simplefxapi.repository;

import com.openpayd.simplefxapi.configuration.CurrencyApiSettings;
import com.openpayd.simplefxapi.model.LatestExchangeRates;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * In memory repository to save currency names.
 */
@Repository
public class CurrencyInMemoryRepository {
    private CurrencyApiSettings currencyApiSettings;
    private RestTemplate restTemplate;
    private List<String> currencies;

    /**
     * Initializes currency repository by fetching from free currency API.
     * @param currencyApiSettings Includes information to make request to currency API.
     * @param restTemplate Client object to make request.
     */
    public CurrencyInMemoryRepository(CurrencyApiSettings currencyApiSettings, RestTemplate restTemplate) {
        currencies = new ArrayList<>();
        LatestExchangeRates latestExchangeRates = restTemplate.exchange(currencyApiSettings.getUrl(),
                                                                            HttpMethod.GET,
                                                                            HttpEntity.EMPTY,
                                                                            LatestExchangeRates.class,
                                                                            currencyApiSettings.getKey(),
                                                                            "USD").getBody();
        currencies.add("USD");
        currencies.addAll(latestExchangeRates.getData().keySet().stream().toList());
    }

    /**
     * Checks whether the given currency is available or not.
     * @param currency Currency to be checked.
     * @return true if it is available otherwise false.
     */
    public boolean contains(String currency) {
        return currencies.contains(currency);
    }
}
