package com.openpayd.simplefxapi.service;

import com.openpayd.simplefxapi.configuration.CurrencyApiSettings;
import com.openpayd.simplefxapi.model.ExchangeRateResponse;
import com.openpayd.simplefxapi.model.LatestExchangeRates;
import com.openpayd.simplefxapi.repository.CurrencyInMemoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service class where necessary logical operations are done in APIs.
 */
@Service
public class FxService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private RestTemplate restTemplate;
    private CurrencyInMemoryRepository currencyInMemoryRepository;
    private CurrencyApiSettings currencyApiSettings;

    public FxService(RestTemplate restTemplate,
                     CurrencyInMemoryRepository currencyInMemoryRepository,
                     CurrencyApiSettings currencyApiSettings) {
        this.restTemplate = restTemplate;
        this.currencyInMemoryRepository = currencyInMemoryRepository;
        this.currencyApiSettings = currencyApiSettings;
    }

    /**
     * Gets the rate of the target currency to the base currency.
     * @param baseCurrency The base currency to be used in obtaining the rate.
     * @param targetCurrency The target currency to be used in obtaining the odds.
     * @return  An object consisting of the return code, the return message, and the rate.
     */
    public ExchangeRateResponse getExchangeRate(String baseCurrency, String targetCurrency) {
        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
        if (!this.currencyInMemoryRepository.contains(baseCurrency)) {
            exchangeRateResponse.setExchangeRate(0);
            exchangeRateResponse.setResponseCode(1);
            exchangeRateResponse.setResponseMessage("Invalid base currency!");
            logger.error("Base currency is invalid!");
        } else if (!this.currencyInMemoryRepository.contains(targetCurrency)) {
            exchangeRateResponse.setExchangeRate(0);
            exchangeRateResponse.setResponseCode(2);
            exchangeRateResponse.setResponseMessage("Invalid target currency!");
            logger.error("Target currency is invalid!");
        } else {
            LatestExchangeRates latestExchangeRates = getLatestExchangeRates(baseCurrency);
            exchangeRateResponse.setExchangeRate(latestExchangeRates.getData().get(targetCurrency));
            exchangeRateResponse.setResponseCode(0);
            exchangeRateResponse.setResponseMessage("Successful!");
            logger.info("Exchange rate retrieved successfully!");
        }

        return exchangeRateResponse;
    }

    /**
     * From the currency API, the rates of other currencies against the base currency are obtained.
     * @param baseCurrency The base currency from which the rates will be derived.
     * @return The response object returned from the currency API.
     */
    private LatestExchangeRates getLatestExchangeRates(String baseCurrency) {
        LatestExchangeRates latestExchangeRates = restTemplate.exchange(currencyApiSettings.getUrl(),
                                                                        HttpMethod.GET,
                                                                        HttpEntity.EMPTY,
                                                                        LatestExchangeRates.class,
                                                                        currencyApiSettings.getKey(),
                                                                        baseCurrency).getBody();
        return latestExchangeRates;
    }
}
