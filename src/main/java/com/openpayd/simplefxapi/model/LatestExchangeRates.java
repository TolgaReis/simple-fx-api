package com.openpayd.simplefxapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class LatestExchangeRates {
    private Map<String, ?> query;
    private Map<String, Double> data;
}
