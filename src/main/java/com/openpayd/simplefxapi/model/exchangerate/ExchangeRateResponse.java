package com.openpayd.simplefxapi.model.exchangerate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The response object required to perform the exchange rate operation.
 */
@Getter
@Setter
@ToString
@Builder
public class ExchangeRateResponse {
    private double exchangeRate;
    private int responseCode;
    private String responseMessage;
}
