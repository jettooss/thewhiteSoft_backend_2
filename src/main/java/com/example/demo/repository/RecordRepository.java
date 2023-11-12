package com.example.demo.repository;

import com.example.demo.Model.Record;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class RecordRepository {
    @Getter
    private final Map<Integer, Record> records;
    private final String filePath;
    private final String inputSavePath;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public RecordRepository(@Value("${input.data.path}") String filePath, @Value("${input.save.path}") String inputSavePath) {
        this.filePath = filePath;
        this.inputSavePath = inputSavePath;
        this.records = new ConcurrentHashMap<>(); // Инициализация пустого хранилища; данные будут загружены в @PostConstruct
    }

    @PostConstruct
    private void loadData() {
        try {
            List<Record> recordList = objectMapper.readValue(new File(filePath), new TypeReference<List<Record>>() {
            });

            this.records.putAll(recordList.stream()
                    .collect(Collectors.toMap(Record::getId, Function.identity())));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load records from dataPath: " + filePath, e);
        }
    }

    public Record getRecordById(Integer id) {
        return records.get(id);
    }

}