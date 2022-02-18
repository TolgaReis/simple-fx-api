package com.openpayd.simplefxapi.service;

import com.openpayd.simplefxapi.model.ExchangeRateRequest;
import com.openpayd.simplefxapi.model.ExchangeRateResponse;
import com.openpayd.simplefxapi.model.LatestExchangeRates;
import com.openpayd.simplefxapi.repository.CurrencyInMemoryRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class FxService {
    private RestTemplate restTemplate;
    private CurrencyInMemoryRepository currencyInMemoryRepository;

    public FxService(RestTemplate restTemplate, CurrencyInMemoryRepository currencyInMemoryRepository) {
        this.restTemplate = restTemplate;
        this.currencyInMemoryRepository = currencyInMemoryRepository;
    }

    public ExchangeRateResponse getExchangeRate(ExchangeRateRequest exchangeRateRequest) {
        Objects.requireNonNull(exchangeRateRequest);
        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
        if (!this.currencyInMemoryRepository.contains(exchangeRateRequest.getBaseCurrency())) {
            exchangeRateResponse.setExchangeRate(0);
            exchangeRateResponse.setResponseCode(1);
            exchangeRateResponse.setResponseMessage("Invalid base currency!");
        } else if (!this.currencyInMemoryRepository.contains(exchangeRateRequest.getTargetCurrency())) {
            exchangeRateResponse.setExchangeRate(0);
            exchangeRateResponse.setResponseCode(2);
            exchangeRateResponse.setResponseMessage("Invalid target currency!");
        }
        
        LatestExchangeRates latestExchangeRates = null;
        try {
            latestExchangeRates = getLatestExchangeRates(exchangeRateRequest);
            if (latestExchangeRates.getData().containsKey(exchangeRateRequest.getTargetCurrency().toUpperCase())) {
                exchangeRateResponse.setExchangeRate(latestExchangeRates.getData().get(exchangeRateRequest.getTargetCurrency().toUpperCase()));
                exchangeRateResponse.setResponseCode(0);
                exchangeRateResponse.setResponseMessage("Successful!");
            } else {
                /**
                 * FIXME: Note that response code 2 is used as error code for invalid target currency.
                 */
                exchangeRateResponse.setExchangeRate(0);
                exchangeRateResponse.setResponseCode(2);
                exchangeRateResponse.setResponseMessage("Invalid target currency!");
            }
        } catch (HttpClientErrorException exception) {
            /**
             * FIXME: Handle error that base_currency is invalid.
             */
            System.out.println(exception.getResponseBodyAsString());
            //e.printStackTrace();
        }
        return exchangeRateResponse;
    }

    private LatestExchangeRates getLatestExchangeRates(ExchangeRateRequest exchangeRateRequest) {
        LatestExchangeRates latestExchangeRates = restTemplate.exchange("https://freecurrencyapi.net/api/v2/latest?apikey={apikey}&base_currency={base_currency}", HttpMethod.GET, HttpEntity.EMPTY,LatestExchangeRates.class,"bd9139f0-90db-11ec-86aa-51d7273f33ea",exchangeRateRequest.getBaseCurrency().toUpperCase()).getBody();
        return latestExchangeRates;
    }

    //private ExchangeRateResponse validateRequest(ExchangeRateRequest exchangeRateRequest) {

    //}
}
