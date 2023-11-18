package com.example.demo.Api.mapper;
import com.example.demo.Api.dto.RecordDto.RecordCreateDto;
import com.example.demo.Api.dto.RecordDto.RecordDto;
import com.example.demo.Api.dto.RecordDto.RecordUpdateDto;
import com.example.demo.service.argument.CreateArgument;
import com.example.demo.service.argument.UpdateArgument;
import com.example.demo.Model.Record;
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