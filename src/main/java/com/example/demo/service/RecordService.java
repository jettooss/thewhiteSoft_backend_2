package com.example.demo.service;
import com.example.demo.service.argument.CreateArgument;
import com.example.demo.service.argument.UpdateArgument;
import com.example.demo.Model.Record;
import java.util.List;
import java.util.Optional;


public interface RecordService {
    Record create(CreateArgument record);

    Optional<Record> updateRecord(Integer id, UpdateArgument record);

    boolean deleteRecord(Integer id);

    List<Record> getAllRecords(String name);

    Record searchByID(Integer id);

}