package com.openpayd.simplefxapi.service;

import com.openpayd.simplefxapi.configuration.CurrencyApiSettings;
<<<<<<< Updated upstream
import com.openpayd.simplefxapi.model.ExchangeRateResponse;
=======
import com.openpayd.simplefxapi.entity.Conversion;
import com.openpayd.simplefxapi.model.conversion.ConversionRequest;
import com.openpayd.simplefxapi.model.conversion.ConversionResponse;
import com.openpayd.simplefxapi.model.exchangerate.ExchangeRateResponse;
>>>>>>> Stashed changes
import com.openpayd.simplefxapi.model.LatestExchangeRates;
import com.openpayd.simplefxapi.repository.ConversionRepository;
import com.openpayd.simplefxapi.repository.CurrencyInMemoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final double MAX_AMOUNT = 999999999999999.9999;

    private RestTemplate restTemplate;
    private CurrencyInMemoryRepository currencyInMemoryRepository;
    private CurrencyApiSettings currencyApiSettings;
    private ConversionRepository conversionRepository;

    public FxService(RestTemplate restTemplate,
                     CurrencyInMemoryRepository currencyInMemoryRepository,
                     CurrencyApiSettings currencyApiSettings,
                     ConversionRepository conversionRepository) {
        this.restTemplate = restTemplate;
        this.currencyInMemoryRepository = currencyInMemoryRepository;
        this.currencyApiSettings = currencyApiSettings;
        this.conversionRepository = conversionRepository;
    }

    /**
     * Gets the rate of the target currency to the base currency.
     * @param baseCurrency The base currency to be used in obtaining the rate.
     * @param targetCurrency The target currency to be used in obtaining the odds.
     * @return  An object consisting of the return code, the return message, and the rate.
     */
    public ExchangeRateResponse getExchangeRateResponse(String baseCurrency, String targetCurrency) {
        ExchangeRateResponse exchangeRateResponse;
        if (!this.currencyInMemoryRepository.contains(baseCurrency)) {
            exchangeRateResponse = ExchangeRateResponse.builder().
                                                        exchangeRate(-1).
                                                        responseCode(1).
                                                        responseMessage("Invalid base currency!").
                                                        build();
            logger.error("Base currency is invalid!");
        } else if (!this.currencyInMemoryRepository.contains(targetCurrency)) {
            exchangeRateResponse = ExchangeRateResponse.builder().
                                                        exchangeRate(-1).
                                                        responseCode(2).
                                                        responseMessage("Invalid target currency!").
                                                        build();
            logger.error("Target currency is invalid!");
        } else {
            exchangeRateResponse = ExchangeRateResponse.builder().
                                                        exchangeRate(getExchangeRate(baseCurrency, targetCurrency)).
                                                        responseCode(0).
                                                        responseMessage("Successful!").
                                                        build();
            logger.info("Exchange rate retrieved successfully!");
        }

        return exchangeRateResponse;
    }

    public ConversionResponse getConversionResponse(ConversionRequest conversionRequest) {
        ConversionResponse conversionResponse;
        if (!this.currencyInMemoryRepository.contains(conversionRequest.getBaseCurrency())) {
            conversionResponse = ConversionResponse.builder().
                                                    targetAmount(-1).
                                                    transactionId(null).
                                                    responseCode(1).
                                                    responseMessage("Invalid base currency!").
                                                    build();
            logger.error("Base currency is invalid!");
        } else if (!this.currencyInMemoryRepository.contains(conversionRequest.getTargetCurrency())) {
            conversionResponse = ConversionResponse.builder().
                                                    targetAmount(-1).
                                                    transactionId(null).
                                                    responseCode(2).
                                                    responseMessage("Invalid target currency!").
                                                    build();
            logger.error("Target currency is invalid!");
        } else {
            double exchangeRate = getExchangeRate(conversionRequest.getBaseCurrency(), conversionRequest.getTargetCurrency());
            double targetAmount = conversionRequest.getBaseAmount() * exchangeRate;
            if (targetAmount > MAX_AMOUNT) {
                conversionResponse = ConversionResponse.builder().
                                                        targetAmount(-1).
                                                        transactionId(null).
                                                        responseCode(3).
                                                        responseMessage("Target amount too long to save!").
                                                        build();
                logger.error("Target amount too long!");
            } else {
                Conversion conversion = Conversion.builder().
                        baseCurrency(conversionRequest.getBaseCurrency()).
                        targetCurrency(conversionRequest.getTargetCurrency()).
                        baseAmount(conversionRequest.getBaseAmount()).
                        targetAmount(targetAmount).
                        build();
                Conversion savedConversion = conversionRepository.saveAndFlush(conversion);
                conversionResponse = ConversionResponse.builder().
                        targetAmount(savedConversion.getTargetAmount()).
                        transactionId(savedConversion.getTransactionId()).
                        responseCode(0).
                        responseMessage("Successful!").
                        build();
                logger.info("Conversion done successfully!");
            }
        }

        return conversionResponse;
    }

    /**
     * From the currency API, the rates of other currencies against the base currency are obtained.
     * @param baseCurrency The base currency from which the rates will be derived.
     * @return The response object returned from the currency API.
     */
    private Double getExchangeRate(String baseCurrency, String targetCurrency) {
        LatestExchangeRates latestExchangeRates = restTemplate.exchange(currencyApiSettings.getUrl(),
                                                                        HttpMethod.GET,
                                                                        HttpEntity.EMPTY,
                                                                        LatestExchangeRates.class,
                                                                        currencyApiSettings.getKey(),
                                                                        baseCurrency).getBody();
        return latestExchangeRates.getData().get(targetCurrency);
    }
}
