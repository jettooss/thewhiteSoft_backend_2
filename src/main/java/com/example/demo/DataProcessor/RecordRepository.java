package com.example.demo.DataProcessor;

import com.example.demo.Model.Record;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        this.records = loadRecordsFromJson();
    }

    private Map<Integer, Record> loadRecordsFromJson() {
        try {
            List<Record> recordList = objectMapper.readValue(new File(filePath), new TypeReference<List<Record>>() {});

            Map<Integer, Record> records = recordList.stream()
                    .collect(Collectors.toMap(Record::getId, Function.identity()));

            return records;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load records from dataPath: " + filePath, e);
        }
    }

    public Record getRecordById(Integer id) {
        return records.get(id);
    }

}