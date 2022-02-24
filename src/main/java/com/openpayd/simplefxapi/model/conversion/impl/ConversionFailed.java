package com.openpayd.simplefxapi.model.conversion.impl;

import com.openpayd.simplefxapi.enums.ErrorTypes;
import com.openpayd.simplefxapi.model.conversion.ConversionResponse;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class ConversionFailed implements ConversionResponse {
    private ErrorTypes errorCode;
    private String message;
}
