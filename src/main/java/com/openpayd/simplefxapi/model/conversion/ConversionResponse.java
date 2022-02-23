package com.openpayd.simplefxapi.model.conversion;

import lombok.*;

import java.util.UUID;

@Data
@ToString
@Builder
public class ConversionResponse {
    private Double targetAmount;
    private UUID transactionId;
    private Integer responseCode;
    private String responseMessage;
}
