package com.openpayd.simplefxapi.controller;

import com.openpayd.simplefxapi.model.exchangerate.ExchangeRateResponse;
import com.openpayd.simplefxapi.service.FxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Contains API endpoints used to calculate exchange rates.
 */
@RestController
@RequestMapping(path = "/api/v1/exchange",
                produces = "application/json")
@CrossOrigin(origins = "*")
public class ExchangeController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private FxService fxService;

    public ExchangeController(FxService fxService) {
        this.fxService = fxService;
    }

    /**
     * Gets the rate of the target currency to the base currency.
     * @param baseCurrency The base currency to be used in obtaining the rate.
     * @param targetCurrency The target currency to be used in obtaining the odds.
     * @return  An object consisting of the return code, the return message, and the rate.
     */
    @GetMapping("/rate")
    public ExchangeRateResponse getExchangeRate(@RequestParam("base") String baseCurrency,
                                                 @RequestParam("target") String targetCurrency) {
        logger.debug("Get exchange rate request.");
        ExchangeRateResponse exchangeRateResponse = fxService.getExchangeRate(baseCurrency.toUpperCase(), targetCurrency.toUpperCase());
        return exchangeRateResponse;
    }
}
