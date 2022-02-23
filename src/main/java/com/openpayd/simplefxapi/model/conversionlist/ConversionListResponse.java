package com.openpayd.simplefxapi.model.conversionlist;

import com.openpayd.simplefxapi.entity.Conversion;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ConversionListResponse {
    private List<Conversion> conversions;
    private Long numberOfItems;
    private int numberOfPages;
    private Integer responseCode;
    private String responseMessage;
}
