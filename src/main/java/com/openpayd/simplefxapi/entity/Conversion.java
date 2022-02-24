package com.openpayd.simplefxapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "conversion")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conversion {
    @Id
    @GeneratedValue
    private int id;
    private String baseCurrency;
    private String targetCurrency;
    private Double baseAmount;
    private Double targetAmount;
    private Double exchangeRate;
    @Generated(value = GenerationTime.INSERT)
    private UUID transactionId;
    @Generated(value = GenerationTime.INSERT)
    private ZonedDateTime date;
}
