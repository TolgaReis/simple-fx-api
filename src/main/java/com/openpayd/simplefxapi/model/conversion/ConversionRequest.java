package com.openpayd.simplefxapi.model.conversion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversionRequest {
    private Double sourceAmount;
    private String baseCurrency;
    private String targetCurrency;
}
