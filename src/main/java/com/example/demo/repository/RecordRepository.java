package com.example.demo.repository;

import com.example.demo.Model.Record;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class RecordRepository {
    private final Map<Integer, Record> records;
    private final String filePath;
    private final String inputSavePath;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public RecordRepository(@Value("${input.data.path}") String filePath, @Value("${input.save.path}") String inputSavePath) {
        this.filePath = filePath;
        this.inputSavePath = inputSavePath;
        this.records = new ConcurrentHashMap<>();
        loadData();
    }

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

    public boolean saveToJson(String name_file) throws IOException {
        List<Record> recordList = new ArrayList<>(records.values());
        objectMapper.writeValue(new File(inputSavePath + name_file + ".json"), recordList);
        System.out.println("Список записей успешно сохранен в JSON файл: " + name_file);
        return new File(inputSavePath + name_file + ".json").exists();
    }

    public Record getRecordById(Integer id) {
        return records.get(id);
    }

    public Record save(Record record) {
        int nextId = getNextId();
        record.setId(nextId);
        records.put(nextId, record);
        return record;
    }

    public Record update(Record record) {
        int nextId = record.getId();
        record.setId(nextId);
        records.put(nextId, record);
        return record;
    }

    public boolean deleteById(int id) {
        return records.remove(id) != null;
    }

    public List<Record> getAllRecords() {
        return new ArrayList<>(records.values());
    }

    public int getNextId() {
        int maxId = records.keySet().stream()
                .max(Integer::compareTo)
                .orElse(0);

        return maxId + 1;
    }

}