package com.openpayd.simplefxapi.service;

import com.openpayd.simplefxapi.enums.ErrorTypes;
import com.openpayd.simplefxapi.model.conversion.ConversionRequest;
import com.openpayd.simplefxapi.model.conversion.impl.ConversionFailed;
import com.openpayd.simplefxapi.model.conversion.impl.ConversionSuccess;
import com.openpayd.simplefxapi.model.conversionlist.ConversionListResponse;
import com.openpayd.simplefxapi.model.exchangerate.ExchangeRateResponse;
import com.openpayd.simplefxapi.model.exchangerate.impl.ExchangeRateFailed;
import com.openpayd.simplefxapi.model.exchangerate.impl.ExchangeRateSuccess;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.ZonedDateTime;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class FxServiceTest {
    @Autowired
    private FxService fxService;

    @Test
    public void givenParametersDoesNotExist_thenFailedRetrieved() {
        String base = null;
        String target = null;
        ExchangeRateFailed exchangeRateFailed = ExchangeRateFailed.builder().
                errorCode(ErrorTypes.NOT_EXIST_PARAMETER).
                message("Required parameter does not exist!").
                build();
        Assert.assertEquals(exchangeRateFailed, fxService.getExchangeRateResponse(base, target));
    }

    @Test
    public void givenBaseCurrencyDoesNotExist_thenFailedRetrieved() {
        String base = "US";
        String target = "TRY";
        ExchangeRateFailed exchangeRateFailed = ExchangeRateFailed.builder().
                errorCode(ErrorTypes.INVALID_BASE_CURRENCY).
                message("Base currency is invalid!").
                build();
        Assert.assertEquals(exchangeRateFailed, fxService.getExchangeRateResponse(base, target));
    }

    @Test
    public void givenTargetCurrencyDoesNotExist_thenFailedRetrieved() {
        String base = "USD";
        String target = "TR";
        ExchangeRateFailed exchangeRateFailed = ExchangeRateFailed.builder().
                errorCode(ErrorTypes.INVALID_TARGET_CURRENCY).
                message("Target currency is invalid!").
                build();
        Assert.assertEquals(exchangeRateFailed, fxService.getExchangeRateResponse(base, target));
    }

    @Test
    public void givenAvailableParameters_thenSuccessRetrieved() {
        String base = "USD";
        String target = "TRY";
        ExchangeRateSuccess exchangeRateSuccess = ExchangeRateSuccess.builder().
                baseCurrency("USD").
                targetCurrency("TRY").
                build();
        ExchangeRateResponse exchangeRateSuccessResult = fxService.getExchangeRateResponse(base, target);
        Assert.assertEquals(base, ((ExchangeRateSuccess)exchangeRateSuccessResult).getBaseCurrency());
        Assert.assertEquals(target, ((ExchangeRateSuccess)exchangeRateSuccessResult).getTargetCurrency());
    }

    @Test
    public void givenRequestObjectDoesNotExist_thenFailedRetreived() {
        ConversionRequest conversionRequest = null;
        ConversionFailed conversionFailed = ConversionFailed.builder().
                errorCode(ErrorTypes.NOT_EXIST_PARAMETER).
                message("Required parameter does not exist!").
                build();
        Assert.assertEquals(conversionFailed, fxService.getConversionResponse(conversionRequest));
    }

    @Test
    public void givenBaseAmountDoesNotExist_thenFailedRetreived() {
        ConversionRequest conversionRequest = new ConversionRequest();
        conversionRequest.setBaseAmount(null);
        conversionRequest.setBaseCurrency("USD");
        conversionRequest.setTargetCurrency("TRY");
        ConversionFailed conversionFailed = ConversionFailed.builder().
                errorCode(ErrorTypes.NOT_EXIST_PARAMETER).
                message("Required parameter does not exist!").
                build();
        Assert.assertEquals(conversionFailed, fxService.getConversionResponse(conversionRequest));
    }

    @Test
    public void givenBaseCurrencyDoesNotExist_thenFailedRetreived() {
        ConversionRequest conversionRequest = new ConversionRequest();
        conversionRequest.setBaseAmount(1.0);
        conversionRequest.setBaseCurrency(null);
        conversionRequest.setTargetCurrency("TRY");
        ConversionFailed conversionFailed = ConversionFailed.builder().
                errorCode(ErrorTypes.NOT_EXIST_PARAMETER).
                message("Required parameter does not exist!").
                build();
        Assert.assertEquals(conversionFailed, fxService.getConversionResponse(conversionRequest));
    }

    @Test
    public void givenTargetCurrencyDoesNotExist_thenFailedRetreived() {
        ConversionRequest conversionRequest = new ConversionRequest();
        conversionRequest.setBaseAmount(1.0);
        conversionRequest.setBaseCurrency("USD");
        conversionRequest.setTargetCurrency(null);
        ConversionFailed conversionFailed = ConversionFailed.builder().
                errorCode(ErrorTypes.NOT_EXIST_PARAMETER).
                message("Required parameter does not exist!").
                build();
        Assert.assertEquals(conversionFailed, fxService.getConversionResponse(conversionRequest));
    }

    @Test
    public void givenBaseAmountInvalid_thenFailedRetreived() {
        ConversionRequest conversionRequest = new ConversionRequest();
        conversionRequest.setBaseAmount(-1.0);
        conversionRequest.setBaseCurrency("USD");
        conversionRequest.setTargetCurrency("TRY");
        ConversionFailed conversionFailed = ConversionFailed.builder().
                errorCode(ErrorTypes.INVALID_BASE_AMOUNT).
                message("Invalid base amount!").
                build();
        Assert.assertEquals(conversionFailed, fxService.getConversionResponse(conversionRequest));
    }

    @Test
    public void givenBaseCurrencyInvalid_thenFailedRetreived() {
        ConversionRequest conversionRequest = new ConversionRequest();
        conversionRequest.setBaseAmount(1.0);
        conversionRequest.setBaseCurrency("US");
        conversionRequest.setTargetCurrency("TRY");
        ConversionFailed conversionFailed = ConversionFailed.builder().
                errorCode(ErrorTypes.INVALID_BASE_CURRENCY).
                message("Base currency is invalid!").
                build();
        Assert.assertEquals(conversionFailed, fxService.getConversionResponse(conversionRequest));
    }

    @Test
    public void givenTargetCurrencyInvalid_thenFailedRetreived() {
        ConversionRequest conversionRequest = new ConversionRequest();
        conversionRequest.setBaseAmount(1.0);
        conversionRequest.setBaseCurrency("USD");
        conversionRequest.setTargetCurrency("TR");
        ConversionFailed conversionFailed = ConversionFailed.builder().
                errorCode(ErrorTypes.INVALID_TARGET_CURRENCY).
                message("Target currency is invalid!").
                build();
        Assert.assertEquals(conversionFailed, fxService.getConversionResponse(conversionRequest));
    }

    @Test
    public void givenTargetAmountTooLong_thenFailedRetreived() {
        ConversionRequest conversionRequest = new ConversionRequest();
        conversionRequest.setBaseAmount(9999999999999999999999999.9999);
        conversionRequest.setBaseCurrency("USD");
        conversionRequest.setTargetCurrency("TRY");
        ConversionFailed conversionFailed = ConversionFailed.builder().
                errorCode(ErrorTypes.TOO_LONG_TARGET_AMOUNT).
                message("Target amount is too long!").
                build();
        Assert.assertEquals(conversionFailed, fxService.getConversionResponse(conversionRequest));
    }

    @Test
    public void givenAvailableObject_thenSuccess() {
        ConversionRequest conversionRequest = new ConversionRequest();
        conversionRequest.setBaseAmount(1.0);
        conversionRequest.setBaseCurrency("USD");
        conversionRequest.setTargetCurrency("TRY");
        Assert.assertEquals(ConversionSuccess.class, fxService.getConversionResponse(conversionRequest).getClass());
    }

    @Test
    public void getConversionList_thenSuccess() {
        ConversionRequest conversionRequest = new ConversionRequest();
        conversionRequest.setBaseAmount(1.0);
        conversionRequest.setBaseCurrency("USD");
        conversionRequest.setTargetCurrency("TRY");
        UUID lastTransactionId = null;
        ConversionSuccess conversionSuccess = null;
        ZonedDateTime zonedDateTime = null;
        for (int i = 0; i < 20; i++) {
            conversionSuccess = ((ConversionSuccess)fxService.getConversionResponse(conversionRequest));
            lastTransactionId = conversionSuccess.getTransactionId();
            if (i == 0) {
                zonedDateTime = conversionSuccess.getTimestamp();
            }
        }
        Assert.assertEquals(new Long(21), fxService.getConversionList(null, null, Pageable.ofSize(5).withPage(0)).getNumberOfItems());
        Assert.assertEquals(conversionSuccess.getTransactionId(), fxService.getConversionList(lastTransactionId, null, Pageable.ofSize(20).withPage(0)).getConversions().get(0).getTransactionId());
        Assert.assertEquals(19, fxService.getConversionList(null, zonedDateTime, Pageable.ofSize(20).withPage(0)).getConversions().size());
    }
}