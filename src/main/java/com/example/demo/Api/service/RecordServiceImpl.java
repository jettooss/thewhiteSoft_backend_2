package com.example.demo.Api.service;
import com.example.demo.Model.argument.CreateArgument;
import com.example.demo.Model.argument.UpdateArgument;
import com.example.demo.repository.RecordRepository;
import com.example.demo.Model.Record;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {


    private final RecordRepository repository;

    public RecordServiceImpl(RecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public Record createRecord(CreateArgument record) {
        Record newRecord = new Record(1, record.getName(), record.getDescription(), record.getLink());
        return repository.save(newRecord);
    }



    public Optional<Record> updateRecord(Integer id, UpdateArgument argument) {
        Record existingRecord = repository.getRecordById(id);

        if (existingRecord != null) {
            existingRecord.setName(argument.getName());
            existingRecord.setDescription(argument.getDescription());
            existingRecord.setLink(argument.getLink());

            repository.update(existingRecord);
            return Optional.of(existingRecord);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteRecord(Integer id) {
        return repository.deleteById(id);
    }

    @Override
    public List<Record> getAllRecords(String name) {
        List<Record> records = new ArrayList<>(repository.getAllRecords());

        if (name != null && !name.isEmpty()) {
            records = records.stream()
                    .filter(record -> record.getName().equalsIgnoreCase(name))
                    .collect(Collectors.toList());
        }

        return records;
    }

    @Override
    public Record searchByID(Integer id) {
        return repository.getRecordById(id);
    }
}
