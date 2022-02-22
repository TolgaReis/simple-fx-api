package com.openpayd.simplefxapi.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The response object returned after the exchange rate operation.
 */
@Getter
@Setter
public class ExchangeRateRequest {
    private String baseCurrency;
    private String targetCurrency;
}
