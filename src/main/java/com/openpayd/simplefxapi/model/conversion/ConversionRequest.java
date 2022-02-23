package com.openpayd.simplefxapi.model.conversion;

import lombok.Getter;
import lombok.Setter;
<<<<<<< HEAD
import lombok.ToString;

@Getter
@Setter
@ToString
public class ConversionRequest {
    private Double baseAmount;
=======

@Getter
@Setter
public class ConversionRequest {
    private Double sourceAmount;
>>>>>>> a2f6542f3363f5ede0fa0acc5b7d6c04c0a61dfd
    private String baseCurrency;
    private String targetCurrency;
}
