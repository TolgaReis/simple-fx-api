package com.openpayd.simplefxapi.service;

import com.openpayd.simplefxapi.model.ExchangeRateRequest;
import com.openpayd.simplefxapi.model.ExchangeRateResponse;
import org.springframework.stereotype.Service;

@Service
public class FxService {
    public ExchangeRateResponse getExchangeRate(ExchangeRateRequest exchangeRateRequest) {
        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();
        exchangeRateResponse.setExchangeRate(1.45);
        exchangeRateResponse.setResponseCode(0);
        exchangeRateResponse.setResponseMessage("Successful!");
        return exchangeRateResponse;
    }
}
