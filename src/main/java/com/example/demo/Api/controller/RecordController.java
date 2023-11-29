package com.example.demo.Api.controller;
import com.example.demo.Api.dto.RecordDto.RecordCreateDto;
import com.example.demo.Api.dto.RecordDto.RecordDto;
import com.example.demo.Api.dto.RecordDto.RecordUpdateDto;
import com.example.demo.Api.mapper.RecordMapper;
import com.example.demo.service.RecordService;
import com.example.demo.Model.Record;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.example.demo.service.argument.CreateArgument;
import com.example.demo.service.argument.UpdateArgument;


@RestController
@RequestMapping("api/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;
    private final RecordMapper recordMapper;


    @PostMapping("{id}/create")
    @Operation(description = "Создать запись")
    public RecordDto create(@RequestBody RecordCreateDto dto) {
        CreateArgument argument = recordMapper.toCreateArgument(dto);
        return recordMapper.toDto(recordService.create(argument));
    }

    @PostMapping("{id}/update")
    @Operation(description = "Изменить запись по ID")
    public RecordDto updateByID(@PathVariable("id") Integer id, @RequestBody RecordUpdateDto dto) {
        UpdateArgument argument = recordMapper.toUpdateArgument(dto);
        Optional<Record> updatedRecord = recordService.updateRecord(id, argument);

        return updatedRecord.map(recordMapper::toDto).orElseThrow();
    }
    @GetMapping("all")
    @Operation(description = "Получить все записи")
    public List<RecordDto> getAllRecords(@RequestParam(required = false) String name) {
        List<RecordDto> dtos = recordMapper.toDtoList(recordService.getAllRecords(name));
        return dtos;
    }
    @DeleteMapping("{id}/delete")
    @Operation(description = "Удалить запись по ID")
    public void deleteRecord(@PathVariable Integer id) {
         recordService.deleteRecord(id);

    }

}