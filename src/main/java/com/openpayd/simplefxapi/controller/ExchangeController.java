package com.openpayd.simplefxapi.controller;

import com.openpayd.simplefxapi.model.ExchangeRateRequest;
import com.openpayd.simplefxapi.model.ExchangeRateResponse;
import com.openpayd.simplefxapi.service.FxService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/exchange",
                produces = "application/json")
@CrossOrigin(origins = "*")
public class ExchangeController {
    private FxService fxService;

    public ExchangeController(FxService fxService) {
        this.fxService = fxService;
    }

    @GetMapping("/rate")
    private ExchangeRateResponse getExchangeRate(@RequestParam("base") String baseCurrency,
                                                 @RequestParam("target") String targetCurrency) {
        return fxService.getExchangeRate(baseCurrency.toUpperCase(), targetCurrency.toUpperCase());
    }
}
