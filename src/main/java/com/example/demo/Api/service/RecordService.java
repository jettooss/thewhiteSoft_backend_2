package com.example.demo.Api.service;
//import com.example.demo.Api.dto.DtoData;
import com.example.demo.Api.dto.*;
import java.util.List;

public interface RecordService {
    DtoData createRecord(DtoData dtoData);

    DtoData getRecordById(Integer id);

    DtoData updateRecord(Integer id, DtoData dtoData);

    boolean deleteRecord(Integer id);


    List<DtoData> getAllRecords(String name);
}