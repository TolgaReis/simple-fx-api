package com.openpayd.simplefxapi.model.exchangerate.impl;

import com.openpayd.simplefxapi.model.exchangerate.ExchangeRateResponse;
import lombok.*;

import java.sql.Timestamp;

/**
 * Response object required to perform the exchange rate operation.
 */
@Data
@ToString
@Builder
public class ExchangeRateSuccess implements ExchangeRateResponse {
    private Double exchangeRate;
    private String baseCurrency;
    private String targetCurrency;
    private Timestamp timestamp;
}
