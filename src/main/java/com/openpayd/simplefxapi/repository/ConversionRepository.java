package com.openpayd.simplefxapi.repository;

import com.openpayd.simplefxapi.entity.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Integer> {
}
