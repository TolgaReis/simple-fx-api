package com.openpayd.simplefxapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * The client object used for the response returned from the currency API.
 */
@Getter
@Setter
public class LatestExchangeRates {
    private Map<String, ?> query;
    private Map<String, Double> data;
}
