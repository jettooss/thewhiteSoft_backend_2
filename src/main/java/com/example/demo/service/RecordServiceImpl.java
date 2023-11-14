package com.example.demo.service;
import com.example.demo.service.argument.CreateArgument;
import com.example.demo.service.argument.UpdateArgument;
import com.example.demo.repository.RecordRepository;
import com.example.demo.Model.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordRepository repository;

    @Override
    public Record createRecord(CreateArgument argument) {
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
