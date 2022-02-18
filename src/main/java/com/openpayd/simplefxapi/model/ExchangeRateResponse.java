package com.openpayd.simplefxapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateResponse {
    private double exchangeRate;
    private int responseCode;
    private String responseMessage;
}
