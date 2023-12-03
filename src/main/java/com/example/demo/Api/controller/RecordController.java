package com.example.demo.Api.controller;
import com.example.demo.Api.dto.RecordDto.RecordCreateDto;
import com.example.demo.Api.dto.RecordDto.RecordDto;
import com.example.demo.Api.dto.RecordDto.RecordUpdateDto;
import com.example.demo.Api.mapper.RecordMapper;
import com.example.demo.service.RecordService;
import com.example.demo.Model.Record;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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
        Optional<Record> updatedRecord = recordService.update(id, argument);

        return updatedRecord.map(recordMapper::toDto).orElseThrow();
    }

    @GetMapping("all")
    @Operation(description = "Получить все записи")
    public List<RecordDto> getAllRecords(@RequestParam(required = false) String name) {

        List<RecordDto> dtos = recordMapper.toDtoList(recordService.getAll(name));
        return dtos;
    }

    @DeleteMapping("{id}/delete")
    @Operation(description = "Удалить запись по ID")
    public void deleteRecord(@PathVariable Integer id) {

        recordService.deleteRecord(id);

    }

    @GetMapping("search")
    @Operation(description = "Получить записи с учетом фильтрации, сортировки и пагинации")
    public Page<RecordDto> getFilteredRecords(
            @RequestParam(required = false) String partialName,
            @RequestParam(required = false) String partialDescription,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));

        Page<Record> recordsPage = recordService.getRecordsByCriteria(
                partialName, partialDescription, sortBy, sortDirection, pageable);

        List<RecordDto> dtos = recordMapper.toDtoList(recordsPage.getContent());
        return new PageImpl<>(dtos, pageable, recordsPage.getTotalElements());
    }

}