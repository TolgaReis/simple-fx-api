package com.openpayd.simplefxapi.controller;

import com.openpayd.simplefxapi.model.conversion.ConversionRequest;
import com.openpayd.simplefxapi.model.conversion.ConversionResponse;
<<<<<<< HEAD
import com.openpayd.simplefxapi.service.FxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
=======
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> a2f6542f3363f5ede0fa0acc5b7d6c04c0a61dfd

@RestController
@RequestMapping(path = "/api/v1/conversion",
                produces = "application/json")
@CrossOrigin(origins = "*")
public class ConversionController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

<<<<<<< HEAD
    FxService fxService;

    public ConversionController(FxService fxService) {
        this.fxService = fxService;
    }

    @PostMapping("")
    public ConversionResponse postConversion(@RequestBody ConversionRequest conversionRequest) {
        return fxService.getConversionResponse(conversionRequest);
    }
=======
>>>>>>> a2f6542f3363f5ede0fa0acc5b7d6c04c0a61dfd
}
