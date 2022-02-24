package com.openpayd.simplefxapi.model.exchangerate;

import com.openpayd.simplefxapi.model.exchangerate.impl.ExchangeRateFailed;
import com.openpayd.simplefxapi.model.exchangerate.impl.ExchangeRateSuccess;

public class ExchangeResponseFactory {
    public static ExchangeRateResponse getExchangeRateResponse(String responseType) {
        if (responseType.equals("success")) {
            return new ExchangeRateSuccess();
        } else if (responseType.equals("badrequest")) {
            return new ExchangeRateFailed();
        } else {
            return null;
        }
    }
}
