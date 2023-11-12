package com.example.demo.Api.controller;
import com.example.demo.Api.NotFoundException;
import com.example.demo.Api.dto.RecordDto.RecordCreateDto;
import com.example.demo.Api.dto.RecordDto.RecordResponseDto;
import com.example.demo.Api.dto.RecordDto.RecordUpdateDto;
import com.example.demo.Api.service.RecordServiceImpl;
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
    public ResponseEntity<RecordResponseDto> createRecord(@RequestBody RecordCreateDto recordCreateDto) {
        RecordResponseDto createdDto = recordServiceImpl.createRecord(recordCreateDto);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(description = "Все записи с возможностью поиска по наименованию")
    public ResponseEntity<List<RecordResponseDto>> getAllRecords(@RequestParam(required = false) String name) {
        List<RecordResponseDto> dtos = recordServiceImpl.getAllRecords(name);
        return handleNonNullDtoDataList(dtos);
    }

    @Operation(description = "Получение по ID")
    @GetMapping("/{id}")
    public ResponseEntity<RecordResponseDto> getRecordById(@PathVariable Integer id) {
        RecordResponseDto dtoData = recordServiceImpl.getRecordById(id);
        return handleNonNullDtoData(dtoData);
    }

    @Operation(description = "Обновление записи")
    @PutMapping("/{id}/update")
    public ResponseEntity<RecordUpdateDto> updateRecord(@PathVariable Integer id, @RequestBody RecordUpdateDto recordUpdateDto) {
        RecordUpdateDto updatedDto = recordServiceImpl.updateRecord(id, recordUpdateDto);

        if (updatedDto != null) {
            return new ResponseEntity<>(updatedDto, HttpStatus.OK);
        } else {
            throw new NotFoundException("Запись не найдена: " + id);
        }
    }

    @Operation(description = "Удаление по ID")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteRecord(@PathVariable Integer id) {
        boolean deleted = recordServiceImpl.deleteRecord(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new NotFoundException("Запись не найдена: " + id);
        }
    }

    private ResponseEntity<RecordResponseDto> handleNonNullDtoData(RecordResponseDto dtoData) {
        if (dtoData != null) {
            return new ResponseEntity<>(dtoData, HttpStatus.OK);
        } else {
            throw new NotFoundException("не найдено");
        }
    }

    private ResponseEntity<List<RecordResponseDto>> handleNonNullDtoDataList(List<RecordResponseDto> dtoDataList) {
        if (dtoDataList != null && !dtoDataList.isEmpty()) {
            return new ResponseEntity<>(dtoDataList, HttpStatus.OK);
        } else {
            throw new NotFoundException("не найдено");
        }
    }
}