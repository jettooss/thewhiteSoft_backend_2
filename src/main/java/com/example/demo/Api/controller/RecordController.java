package com.example.demo.Api.controller;

import com.example.demo.Api.NotFoundException;
import com.example.demo.Api.controller.dto.DtoData;
import com.example.demo.Api.controller.dto.DtoUpdate;
import com.example.demo.Api.controller.service.RecordServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "api/records")
public class RecordController {
    private final RecordServiceImpl recordServiceImpl;

    @Autowired
    public RecordController(RecordServiceImpl recordServiceImpl) {
        this.recordServiceImpl = recordServiceImpl;
    }

    @Operation(description = "Добавление записи")
    @PostMapping("/create")
    public ResponseEntity<DtoData> createRecord(@RequestBody DtoData dtoData) {
        DtoData createdDto = recordServiceImpl.createRecord(dtoData);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(description = "Все записи с возможностью поиска по наименованию")
    public ResponseEntity<List<DtoData>> getAllRecords(@RequestParam(required = false) String name) {
        List<DtoData> dtos = recordServiceImpl.getAllRecords(name);
        return handleNonNullDtoDataList(dtos);


    }

    @Operation(description = "Получение по ID")
    @GetMapping("{id}")
    public ResponseEntity<DtoData> getRecordById(@PathVariable Integer id) {
        DtoData dtoData = recordServiceImpl.getRecordById(id);
        return handleNonNullDtoData(dtoData);

    }


    @Operation(description = "Обновление записи")
    @PutMapping("/update_{id}")
    public ResponseEntity<DtoData> updateRecord(@PathVariable Integer id, @RequestBody DtoUpdate dtoUpdate) {
        DtoData updatedDto = recordServiceImpl.updateRecord(id, dtoUpdate);
        return handleNonNullDtoData(updatedDto);

    }

    @Operation(description = "Удаление по ID")
    @DeleteMapping("/delete_{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Integer id) {
        boolean deleted = recordServiceImpl.deleteRecord(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new NotFoundException("Запись не найдена: " + id);
        }
    }

    private ResponseEntity<DtoData> handleNonNullDtoData(DtoData dtoData) {
        if (dtoData != null) {
            return new ResponseEntity<>(dtoData, HttpStatus.OK);
        } else {
            throw new NotFoundException("не найдено");
        }
    }

    private ResponseEntity<List<DtoData>> handleNonNullDtoDataList(List<DtoData> dtoDataList) {
        if (dtoDataList != null && !dtoDataList.isEmpty()) {
            return new ResponseEntity<>(dtoDataList, HttpStatus.OK);
        } else {
            throw new NotFoundException("не найдено");
        }
    }
}