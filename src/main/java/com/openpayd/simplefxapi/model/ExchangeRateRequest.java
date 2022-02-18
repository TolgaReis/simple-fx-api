package com.openpayd.simplefxapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExchangeRateRequest {
    private String sourceForeign;
    private String targetForeign;
}
