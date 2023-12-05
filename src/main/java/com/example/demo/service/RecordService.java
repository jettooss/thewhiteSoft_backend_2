package com.example.demo.service;
import com.example.demo.service.argument.CreateArgument;
import com.example.demo.service.argument.UpdateArgument;
import com.example.demo.model.Record;
import java.util.List;
import java.util.Optional;

public interface RecordService {
    Record create(CreateArgument record);

    Optional<Record> update(Integer id, UpdateArgument record);

    void deleteRecord(Integer id);

    List<Record> getAll(String name);

    Record getExisting(Integer id);

}