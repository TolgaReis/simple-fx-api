package com.openpayd.simplefxapi.model.exchangerate.impl;

import com.openpayd.simplefxapi.enums.ErrorTypes;
import com.openpayd.simplefxapi.model.exchangerate.ExchangeRateResponse;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ExchangeRateFailed implements ExchangeRateResponse {
    private ErrorTypes errorCode;
    private String message;
}
