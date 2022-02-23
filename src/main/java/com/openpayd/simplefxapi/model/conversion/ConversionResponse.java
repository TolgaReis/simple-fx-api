package com.openpayd.simplefxapi.model.conversion;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
public class ConversionResponse {
    private double targetAmount;
    private UUID transactionId;
    private int responseCode;
    private String responseMessage;
}
