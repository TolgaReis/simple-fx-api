package com.openpayd.simplefxapi.service;

import com.openpayd.simplefxapi.configuration.CurrencyApiSettings;
import com.openpayd.simplefxapi.model.ExchangeRateResponse;
import com.openpayd.simplefxapi.model.LatestExchangeRates;
import com.openpayd.simplefxapi.repository.CurrencyInMemoryRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class FxService {
    private RestTemplate restTemplate;
    private CurrencyInMemoryRepository currencyInMemoryRepository;
    private CurrencyApiSettings currencyApiSettings;

    public FxService(RestTemplate restTemplate, CurrencyInMemoryRepository currencyInMemoryRepository, CurrencyApiSettings currencyApiSettings) {
        this.restTemplate = restTemplate;
        this.currencyInMemoryRepository = currencyInMemoryRepository;
        this.currencyApiSettings = currencyApiSettings;
    }

    public ExchangeRateResponse getExchangeRate(String baseCurrency, String targetCurrency) {
        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
        if (!this.currencyInMemoryRepository.contains(baseCurrency)) {
            exchangeRateResponse.setExchangeRate(0);
            exchangeRateResponse.setResponseCode(1);
            exchangeRateResponse.setResponseMessage("Invalid base currency!");
        } else if (!this.currencyInMemoryRepository.contains(targetCurrency)) {
            exchangeRateResponse.setExchangeRate(0);
            exchangeRateResponse.setResponseCode(2);
            exchangeRateResponse.setResponseMessage("Invalid target currency!");
        } else {
            LatestExchangeRates latestExchangeRates = null;
            try {
                latestExchangeRates = getLatestExchangeRates(baseCurrency);
                if (latestExchangeRates.getData().containsKey(targetCurrency)) {
                    exchangeRateResponse.setExchangeRate(latestExchangeRates.getData().get(targetCurrency));
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
        }

        return exchangeRateResponse;
    }

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
