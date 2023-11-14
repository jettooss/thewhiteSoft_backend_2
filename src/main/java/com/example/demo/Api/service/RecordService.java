package com.example.demo.Api.service;
import com.example.demo.Model.argument.CreateArgument;
import com.example.demo.Model.argument.UpdateArgument;
import com.example.demo.Model.Record;
import java.util.List;
import java.util.Optional;


public interface RecordService {
    Record createRecord(CreateArgument record);


    Optional<Record> updateRecord(Integer id, UpdateArgument record);

    boolean deleteRecord(Integer id);

    List<Record> getAllRecords(String name);
    Record searchByID(Integer id);

}