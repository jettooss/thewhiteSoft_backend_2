package com.example.demo.service;
import com.example.demo.service.argument.CreateArgument;
import com.example.demo.service.argument.UpdateArgument;
import com.example.demo.Model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface RecordService {
    Record create(CreateArgument record);

    Optional<Record> update(Integer id, UpdateArgument record);

    void deleteRecord(Integer id);

    List<Record> getAll(String name);

    Record searchByID(Integer id);
    Page<Record> getRecordsByCriteria(
            String partialName, String partialDescription, String sortBy, String sortDirection, Pageable pageable);}