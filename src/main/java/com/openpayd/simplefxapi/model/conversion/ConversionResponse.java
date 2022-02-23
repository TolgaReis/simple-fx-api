package com.openpayd.simplefxapi.model.conversion;

<<<<<<< HEAD
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
=======
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversionResponse {
    private Double targetAmount;
    private String transactionId;
    private Integer responseCode;
>>>>>>> a2f6542f3363f5ede0fa0acc5b7d6c04c0a61dfd
    private String responseMessage;
}
