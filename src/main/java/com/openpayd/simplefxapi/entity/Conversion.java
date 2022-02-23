package com.openpayd.simplefxapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Conversion {
    @Id
    @GeneratedValue
    private int id;
    private String baseCurrency;
    private String targetCurrency;
    private Double baseAmount;
    private Double targetAmount;
    private String transactionId;
    private LocalDateTime dateTime;
}
