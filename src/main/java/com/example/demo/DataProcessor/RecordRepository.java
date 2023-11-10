package com.example.demo.DataProcessor;
import com.example.demo.Model.Record;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

            // Преобразование списка в карту
            Map<Integer, Record> records = recordList.stream()
                    .collect(Collectors.toMap(Record::getId, Function.identity()));

            return records;
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
    public Record getRecordByName(String name) {
        for (Record record : records.values()) {
            if (record.getName().equalsIgnoreCase(name)) {
                return record;
            }
        }
        return null;
    }
    public void put(Record record) {
        if (record != null) {
            records.put(record.getId(), record);
        }
    }
}