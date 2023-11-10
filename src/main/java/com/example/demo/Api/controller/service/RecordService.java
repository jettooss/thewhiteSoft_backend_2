package com.example.demo.Api.controller.service;
import com.example.demo.DataProcessor.RecordRepository;
import com.example.demo.Model.Record;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class RecordService implements RecordServiceInterface {
    private final RecordRepository repository;

    @Autowired
    public RecordService(RecordRepository repository) {
        this.repository = repository;
    }

    public Record createRecord(int id,String name, String description, String link) {
//        int id = generateUniqueID();
        Record newRecord = new Record(id, name, description, link);
        repository.getRecords().put(id, newRecord);
        return newRecord;
    }

    public Record getRecordById(Integer id) {
        return repository.getRecordById(id);
    }

    public Record updateRecord(Integer id, String name, String description, String link) {
        Record existingRecord = repository.getRecordById(id);

        if (existingRecord != null) {
            existingRecord.setName(name);
            existingRecord.setDescription(description);
            existingRecord.setLink(link);

            return existingRecord;
        } else {
            return null;
        }
    }

    public boolean deleteRecord(Integer id) {
        return repository.getRecords().remove(id) != null;
    }

    public String searchRecordsByName(String name) {
        Record record = repository.getRecordByName(name);
        if (record != null) {
            return "Найденная запись:\n" +
                    "ID: " + record.getId() + "\n" +
                    "Имя: " + record.getName() + "\n" +
                    "Описание: " + record.getDescription() + "\n" +
                    "Ссылка: " + record.getLink() + "\n";
        } else {
            return "Запись не найдена\n";
        }
    }

    public List<Record> getAllRecords() {
        return new ArrayList<>(repository.getRecords().values());
    }

//    private int generateUniqueID() {
//
//        Random rand = new Random();
//        int uniqueID = rand.nextInt(100000);
//        return uniqueID;
//
//    }
}