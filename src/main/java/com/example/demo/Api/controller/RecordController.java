package com.example.demo.Api.controller;
import com.example.demo.Api.Exception;
import com.example.demo.Model.Record;
import com.example.demo.Api.controller.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Tag(name = "Контроллер для работы с хранилищем полезностей")
@RestController
@RequestMapping(value = "api/records", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecordController {
    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/all")
    @Operation(description = "Получение записи по идентификатору")
    public ResponseEntity<List<Record>> getAllRecords() {
        List<Record> records = recordService.getAllRecords();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }
    @Operation(description = "Получение по ID")
    @GetMapping("/{id}")
    public ResponseEntity<Record> getRecordById(@PathVariable Integer id) {
        Record record = recordService.getRecordById(id);
        if (record != null) {
            return new ResponseEntity<>(record, HttpStatus.OK);
        } else {
            throw new Exception("Запись не найдена: " + id);
        }
    }

    @Operation(description = "Получение по имени")
    @GetMapping("/name")
    public ResponseEntity<String> searchRecordsByName(@RequestParam String name) {
        String result = recordService.searchRecordsByName(name);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(description = "Добавление записи")
    @PostMapping("/create")
    public ResponseEntity createRecord(@RequestBody Record record) {
        Record existingRecord = recordService.getRecordById(record.getId());

        if (existingRecord != null) {
            throw new Exception("Запись с ID " + record.getId() + " уже существует");
        }

        Record createdRecord = recordService.createRecord(record.getId(),record.getName(), record.getDescription(), record.getLink());
        return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
    }

    @Operation(description = "Обновление записи")
    @PutMapping("/{id}")
    public ResponseEntity<Record> updateRecord(@PathVariable Integer id, @RequestParam String name,@RequestParam String description,@RequestParam String link ) {
        Record updated = recordService.updateRecord(id,       name,   description,   link);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            throw new Exception("Запись не найдена: " + id);
        }
    }

    @Operation(description = "Удаление по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Integer id) {
        boolean deleted = recordService.deleteRecord(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new Exception("Запись не найдена: " + id);
        }
    }
}