package com.openpayd.simplefxapi.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Repository
public class CurrencyInMemoryRepository {
    private List<String> currencies;

    public CurrencyInMemoryRepository(ResourceLoader resourceLoader) {
        try {
            File file = resourceLoader.getResource("classpath:currencies.json").getFile();
            ObjectMapper objectMapper = new ObjectMapper();
            currencies = (List<String>) objectMapper.readValue(file, HashMap.class).get("currencies");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean contains(String currency) {
        return currencies.contains(currency);
    }
}
