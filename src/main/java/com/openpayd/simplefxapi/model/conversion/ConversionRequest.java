package com.openpayd.simplefxapi.model.conversion;

import lombok.Data;
import lombok.ToString;

/**
 * Request object for conversion operation.
 */
@Data
@ToString
public class ConversionRequest {
    private Double baseAmount;
    private String baseCurrency;
    private String targetCurrency;
}
