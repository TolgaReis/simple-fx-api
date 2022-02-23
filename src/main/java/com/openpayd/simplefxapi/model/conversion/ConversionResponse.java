package com.openpayd.simplefxapi.model.conversion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversionResponse {
    private Double targetAmount;
    private String transactionId;
    private Integer responseCode;
    private String responseMessage;
}
