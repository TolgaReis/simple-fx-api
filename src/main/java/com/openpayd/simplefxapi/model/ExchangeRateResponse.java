package com.openpayd.simplefxapi.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The response object required to perform the exchange rate operation.
 */
@Getter
@Setter
public class ExchangeRateResponse {
    private double exchangeRate;
    private int responseCode;
    private String responseMessage;
}
