package com.openpayd.simplefxapi.controller;

import com.openpayd.simplefxapi.model.conversion.ConversionRequest;
import com.openpayd.simplefxapi.model.conversion.ConversionResponse;
import com.openpayd.simplefxapi.model.conversion.impl.ConversionSuccess;
import com.openpayd.simplefxapi.model.conversionlist.ConversionListResponse;
import com.openpayd.simplefxapi.service.FxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Controller includes APIs related to conversion operations.
 */
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

    /**
     * Performs the conversion and returns the result.
     * @param conversionRequest Base amount, base currency and target currency.
     * @return Response object generated according to successful and unsuccessful cases.
     */
    @PostMapping("")
    public ResponseEntity<?> postConversion(@RequestBody(required = false) ConversionRequest conversionRequest) {
        ConversionResponse conversionResponse = fxService.getConversionResponse(conversionRequest);
        if (conversionResponse.getClass().equals(ConversionSuccess.class)) {
            return ResponseEntity.ok().body(conversionResponse);
        } else {
            return ResponseEntity.badRequest().body(conversionResponse);
        }
    }

    /**
     * It obtains the information of previously made transactions according to the date or transactionId filters.
     * @param transactionId Transaction ID of the transaction performed.
     * @param date The start date from which the following transactions are requested to be listed.
     * @param pageable It allows the transaction list to be paginated with page and size.
     * @return Response object generated from factory for successful and unsuccessful cases.
     */
    @GetMapping("/list")
    public ResponseEntity<?> getConversionList(@RequestParam(required = false) UUID transactionId,
                                               @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime date,
                                               Pageable pageable) {
        ConversionListResponse conversionListResponse = fxService.getConversionList(transactionId, date, pageable);
        return ResponseEntity.ok().body(conversionListResponse);
    }
}
