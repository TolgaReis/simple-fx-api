package com.openpayd.simplefxapi.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @org.hibernate.annotations.Generated(value = GenerationTime.INSERT)
    private UUID transactionId;
    @org.hibernate.annotations.Generated(value = GenerationTime.INSERT)
    private LocalDate date;
}
