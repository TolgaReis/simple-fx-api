package com.openpayd.simplefxapi.controller;

import com.openpayd.simplefxapi.model.conversion.ConversionRequest;
import com.openpayd.simplefxapi.model.conversion.ConversionResponse;
import com.openpayd.simplefxapi.service.FxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/conversion",
                produces = "application/json")
@CrossOrigin(origins = "*")
public class ConversionController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    FxService fxService;

    public ConversionController(FxService fxService) {
        this.fxService = fxService;
    }

    @PostMapping("")
    public ConversionResponse postConversion(@RequestBody ConversionRequest conversionRequest) {
        return fxService.getConversionResponse(conversionRequest);
    }
}
