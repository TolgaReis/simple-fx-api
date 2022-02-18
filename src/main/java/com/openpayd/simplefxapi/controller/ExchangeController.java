package com.openpayd.simplefxapi.controller;

import com.openpayd.simplefxapi.model.ExchangeRateRequest;
import com.openpayd.simplefxapi.model.ExchangeRateResponse;
import com.openpayd.simplefxapi.service.FxService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exchange/")
public class ExchangeController {
    private FxService fxService;

    public ExchangeController(FxService fxService) {
        this.fxService = fxService;
    }

    @GetMapping("rate")
    @ResponseBody
    private ExchangeRateResponse getExchangeRate(@RequestBody ExchangeRateRequest exchangeRateRequest) {
        return fxService.getExchangeRate(exchangeRateRequest);
    }
}
