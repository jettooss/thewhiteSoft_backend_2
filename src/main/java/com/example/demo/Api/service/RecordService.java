package com.example.demo.Api.service;
import com.example.demo.Api.dto.RecordDto.RecordCreateDto;
import com.example.demo.Api.dto.RecordDto.RecordResponseDto;
import com.example.demo.Api.dto.RecordDto.RecordUpdateDto;

import java.util.List;

public interface RecordService {

    RecordResponseDto createRecord(RecordCreateDto createDto);

    RecordResponseDto getRecordById(Integer id);

    RecordUpdateDto updateRecord(Integer id, RecordUpdateDto updateDto);

    boolean deleteRecord(Integer id);

    List<RecordResponseDto> getAllRecords(String name);
}