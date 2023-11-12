package com.example.demo.Api.service;
import com.example.demo.Api.dto.RecordDto.RecordCreateDto;
import com.example.demo.Api.dto.RecordDto.RecordResponseDto;
import com.example.demo.Api.dto.RecordDto.RecordUpdateDto;
import com.example.demo.repository.RecordRepository;
import com.example.demo.Model.Record;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.atomic.AtomicInteger;



@Service
public class RecordServiceImpl implements RecordService {
    private final RecordRepository repository;
    private final AtomicInteger idCounter = new AtomicInteger(1);

    @Autowired
    public RecordServiceImpl(RecordRepository repository) {
        this.repository = repository;
    }

    public RecordResponseDto createRecord(RecordCreateDto createDto) {
        int id = getNextId();
        Record newRecord = new Record(id, createDto.getName(), createDto.getDescription(), createDto.getLink());
        repository.save(newRecord);
        return convertToRecordResponseDto(newRecord);
    }

    public RecordResponseDto getRecordById(Integer id) {
        Record record = repository.getRecordById(id);
        return record != null ? convertToRecordResponseDto(record) : null;
    }

    public RecordUpdateDto updateRecord(Integer id, RecordUpdateDto updateDto) {
        Record existingRecord = repository.getRecordById(id);

        if (existingRecord != null) {
            existingRecord.setName(updateDto.getName());
            existingRecord.setDescription(updateDto.getDescription());
            existingRecord.setLink(updateDto.getLink());

            return convertToRecordUpdateDto(existingRecord);
        } else {
            return null;
        }
    }

    public boolean deleteRecord(Integer id) {
        return repository.deleteById(id);
    }

    public List<RecordResponseDto> getAllRecords(String name) {
        List<Record> records = new ArrayList<>(repository.getAllRecords());

        if (name != null && !name.isEmpty()) {
            records = records.stream().filter(record -> record.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        }

        return records.stream().map(this::convertToRecordResponseDto).collect(Collectors.toList());
    }

    private RecordResponseDto convertToRecordResponseDto(Record record) {
        return new RecordResponseDto(record.getId(), record.getName(), record.getDescription(), record.getLink());
    }

    private RecordUpdateDto convertToRecordUpdateDto(Record record) {
        return new RecordUpdateDto(record.getName(), record.getDescription(), record.getLink());
    }

    private int getNextId() {
        return repository.getNextId();
    }
}