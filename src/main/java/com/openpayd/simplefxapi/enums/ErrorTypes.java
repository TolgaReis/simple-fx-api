package com.openpayd.simplefxapi.enums;

/**
 * Response codes used in error responses.
 */
public enum ErrorTypes {
    /**
     * Invalid base currency.
     */
    INVALID_BASE_CURRENCY,
    /**
     * Invalid target currency
     */
    INVALID_TARGET_CURRENCY,
    /**
     * Not exist parameter.
     */
    NOT_EXIST_PARAMETER,
    /**
     * Invalid base amount.
     */
    INVALID_BASE_AMOUNT,
    /**
     * Too long target amount.
     */
    TOO_LONG_TARGET_AMOUNT
}
