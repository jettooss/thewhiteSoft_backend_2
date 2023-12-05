package com.example.demo.api.mapper;
import com.example.demo.api.dto.RecordDto.RecordCreateDto;
import com.example.demo.api.dto.RecordDto.RecordDto;
import com.example.demo.api.dto.RecordDto.RecordUpdateDto;
import com.example.demo.model.Record;
import com.example.demo.service.argument.CreateArgument;
import com.example.demo.service.argument.UpdateArgument;
import org.mapstruct.Mapper;
import java.util.List;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


@Mapper(componentModel = SPRING)
public interface RecordMapper {
    CreateArgument toCreateArgument(RecordCreateDto dto);

    UpdateArgument toUpdateArgument(RecordUpdateDto dto);

    RecordDto toDto(Record dto);

    List<RecordDto> toDtoList(List<Record> materials);

}