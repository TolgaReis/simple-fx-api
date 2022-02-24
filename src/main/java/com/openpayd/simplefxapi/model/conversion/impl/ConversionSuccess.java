package com.openpayd.simplefxapi.model.conversion.impl;

import com.openpayd.simplefxapi.model.conversion.ConversionResponse;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Successful response object for conversion operation.
 */
@Data
@ToString
@Builder
public class ConversionSuccess implements ConversionResponse {
    private Double targetAmount;
    private UUID transactionId;
    private ZonedDateTime timestamp;
}
