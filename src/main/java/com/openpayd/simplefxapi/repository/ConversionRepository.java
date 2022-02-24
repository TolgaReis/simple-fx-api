package com.openpayd.simplefxapi.repository;

import com.openpayd.simplefxapi.entity.Conversion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Integer> {
    Page<Conversion> findAllByDateGreaterThan(ZonedDateTime date, Pageable pageable);
    Page<Conversion> findAllByTransactionId(UUID transactionId, Pageable pageable);
}
