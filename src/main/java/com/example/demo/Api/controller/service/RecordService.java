package com.example.demo.Api.controller.service;
import com.example.demo.Api.controller.dto.DtoData;
import java.util.List;

public interface RecordService {
    DtoData createRecord(DtoData dtoData);

    DtoData getRecordById(Integer id);

    DtoData updateRecord(Integer id, DtoData dtoData);

    boolean deleteRecord(Integer id);


    List<DtoData> getAllRecords(String name);
}