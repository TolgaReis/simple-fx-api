package com.openpayd.simplefxapi.repository;

import com.openpayd.simplefxapi.entity.Conversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Repository interface used for conversion table.
 */
@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Integer> {
    /**
     * Gets all conversion transactions later from given date.
     * @param date Start date to list conversion transactions from.
     * @param pageable Page features.
     * @return Conversion transaction page.
     */
    Page<Conversion> findAllByDateGreaterThan(ZonedDateTime date, Pageable pageable);

    /**
     * Gets conversion transaction filtered by transactionId.
     * @param transactionId Transaction ID of wanted conversion transaction.
     * @param pageable Page features.
     * @return Conversion transaction page.
     */
    Page<Conversion> findAllByTransactionId(UUID transactionId, Pageable pageable);
}
