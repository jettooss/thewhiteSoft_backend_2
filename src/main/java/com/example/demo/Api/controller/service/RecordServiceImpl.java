package com.example.demo.Api.controller.service;

import com.example.demo.Api.controller.dto.DtoData;
import com.example.demo.Api.controller.dto.DtoUpdate;
import com.example.demo.DataProcessor.RecordRepository;
import com.example.demo.Model.Record;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Service
public class RecordServiceImpl implements RecordService {
    private final RecordRepository repository;

    @Autowired
    public RecordServiceImpl(RecordRepository repository) {
        this.repository = repository;
    }

    public DtoData createRecord(DtoData dtoData) {
        int id = generateUniqueID();
        Record newRecord = new Record(id, dtoData.getName(), dtoData.getDescription(), dtoData.getLink());
        repository.getRecords().put(id, newRecord);
        return convertToDtoData(newRecord);
    }

    public DtoData getRecordById(Integer id) {
        Record record = repository.getRecordById(id);
        if (record != null) {
            return convertToDtoData(record);
        } else {
            return null;
        }
    }

    public DtoData updateRecord(Integer id, DtoUpdate dtoUpdate) {
        Record existingRecord = repository.getRecordById(id);

        if (existingRecord != null) {
            existingRecord.setName(dtoUpdate.getName());
            existingRecord.setDescription(dtoUpdate.getDescription());
            existingRecord.setLink(dtoUpdate.getLink());

            return convertToDtoData(existingRecord);
        } else {
            return null;
        }
    }

    public boolean deleteRecord(Integer id) {
        return repository.getRecords().remove(id) != null;
    }


    public List<DtoData> getAllRecords(String name) {
        List<Record> records = new ArrayList<>(repository.getRecords().values());

        if (name != null && !name.isEmpty()) {
            records = records.stream().filter(record -> record.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        }

        return records.stream().map(this::convertToDtoData).collect(Collectors.toList());
    }

    private DtoData convertToDtoData(Record record) {
        return new DtoData(record.getId(), record.getName(), record.getDescription(), record.getLink());
    }

    private int generateUniqueID() {

        Random rand = new Random();
        int uniqueID = rand.nextInt(100000);
        return uniqueID;

    }
}