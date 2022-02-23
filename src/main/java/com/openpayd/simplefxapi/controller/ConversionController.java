package com.openpayd.simplefxapi.controller;

import com.openpayd.simplefxapi.entity.Conversion;
import com.openpayd.simplefxapi.model.conversion.ConversionRequest;
import com.openpayd.simplefxapi.model.conversion.ConversionResponse;
import com.openpayd.simplefxapi.model.conversionlist.ConversionListResponse;
import com.openpayd.simplefxapi.service.FxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

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
    public ConversionResponse postConversion(@Valid @RequestBody ConversionRequest conversionRequest) {
        return fxService.getConversionResponse(conversionRequest);
    }

    @GetMapping("/list")
    public ConversionListResponse getConversionList(@RequestParam(required = false) UUID transactionId,
                                                    @RequestParam(required = false) Date date, Pageable pageable) {
        return fxService.getConversionList(transactionId, date, pageable);
    }
}
