package com.example.demo.service;
import com.example.demo.service.argument.CreateArgument;
import com.example.demo.service.argument.UpdateArgument;
import com.example.demo.repository.RecordRepository;
import com.example.demo.Model.Record;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class RecordServiceImpl implements RecordService {


    private final RecordRepository repository;

    public RecordServiceImpl(RecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public Record create(CreateArgument argument) {
        Record rating = Record.builder()
                .name(argument.getName())
                .description(argument.getDescription())
                .link(argument.getLink())
                .build();
        return repository.save(rating);
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
        return repository.getRecordsByName(name);

    }

    @Override
    public Record searchByID(Integer id) {
        return repository.getRecordById(id);
    }
}
