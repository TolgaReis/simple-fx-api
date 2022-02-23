package com.openpayd.simplefxapi.model.conversion;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConversionRequest {
    private Double baseAmount;
    private String baseCurrency;
    private String targetCurrency;
}
