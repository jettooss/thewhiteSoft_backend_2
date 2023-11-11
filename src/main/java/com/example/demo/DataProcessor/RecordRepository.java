package com.example.demo.DataProcessor;

import com.example.demo.Model.Record;
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
import java.util.stream.Collectors;


@Repository
@RequiredArgsConstructor
public class RecordRepository {
    @Getter
    private final Map<Integer, Record> records;
    private final String filePath;
    private final String inputSavePath;

    @Autowired
    public RecordRepository(@Value("${input.data.path}") String filePath, @Value("${input.save.path}") String inputSavePath) {
        this.filePath = filePath;
        this.inputSavePath = inputSavePath;
        this.records = loadRecordsFromJson();
    }

    public Map<Integer, Record> loadRecordsFromJson() {
        try {
            Map<Integer, Record> records = new HashMap<>();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));

            for (JsonNode recordNode : jsonNode) {
                int id = recordNode.get("id").asInt();
                String name = recordNode.get("name").asText();
                String description = recordNode.get("description").asText();
                String link = recordNode.get("link").asText();

                Record record = new Record(id, name, description, link);
                records.put(id, record);
            }
            return records;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load records from dataPath: " + filePath, e);
        }
    }

    public boolean saveToJson(String name_file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Record> recordList = new ArrayList<>(records.values());
        objectMapper.writeValue(new File(inputSavePath + name_file + ".json"), recordList);
        System.out.println("Список записей успешно сохранен в JSON файл: " + name_file);
        return new File(inputSavePath + name_file + ".json").exists();
    }

    public Record getRecordById(Integer id) {
        return records.get(id);
    }

}