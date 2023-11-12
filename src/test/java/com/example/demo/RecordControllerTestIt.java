package com.example.demo;
import com.example.demo.Api.controller.RecordController;
import com.example.demo.Api.dto.RecordDto.RecordCreateDto;
import com.example.demo.Api.dto.RecordDto.RecordResponseDto;
import com.example.demo.Api.dto.RecordDto.RecordUpdateDto;
import com.example.demo.Api.service.RecordServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import java.util.Collections;


@WebMvcTest(RecordController.class)
public class RecordControllerTestIt {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecordServiceImpl recordService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateRecord() throws Exception {
        RecordCreateDto recordCreateDto = new RecordCreateDto();
        recordCreateDto.setName("ExampleName");
        recordCreateDto.setDescription("ExampleDescription");
        recordCreateDto.setLink("ExampleLink");

        RecordResponseDto createdDto = new RecordResponseDto();
        createdDto.setId(1);
        createdDto.setName(recordCreateDto.getName());
        createdDto.setDescription(recordCreateDto.getDescription());
        createdDto.setLink(recordCreateDto.getLink());

        when(recordService.createRecord(any())).thenReturn(createdDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/records/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recordCreateDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(createdDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(createdDto.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(createdDto.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.link").value(createdDto.getLink()));

    }

    @Test
    void testGetAllRecords() throws Exception {
        List<RecordResponseDto> dtoList = Collections.singletonList(new RecordResponseDto());

        when(recordService.getAllRecords(anyString())).thenReturn(dtoList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/records/all")
                        .param("name", "TestName"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));

        verify(recordService, times(1)).getAllRecords(eq("TestName"));
    }


    @Test
    void testGetRecordById() throws Exception {
        Integer id = 3;
        RecordResponseDto dto = new RecordResponseDto();
        dto.setId(id);
        dto.setName("ExampleName");
        dto.setDescription("ExampleDescription");
        dto.setLink("ExampleLink");
        when(recordService.getRecordById(anyInt())).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/records/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                // Add assertions for other properties of RecordResponseDto as needed
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ExampleName"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("ExampleDescription"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.link").value("ExampleLink"));

        verify(recordService, times(1)).getRecordById(eq(id));
    }

    @Test
    void testUpdateRecord() throws Exception {
        Integer id = 3;
        RecordUpdateDto updateDto = new RecordUpdateDto();
        updateDto.setName("ExampleUpdatedName");

        when(recordService.updateRecord(anyInt(), any())).thenReturn(updateDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/records/{id}/update", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ExampleUpdatedName"));

    }

    @Test
    void testUpdateRecordNotFound() throws Exception {
        Integer id = 1;

        when(recordService.updateRecord(anyInt(), any())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/records/{id}/update", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(recordService, times(1)).updateRecord(eq(id), any());
    }

    @Test
    void testDeleteRecord() throws Exception {
        Integer id = 1;

        when(recordService.deleteRecord(anyInt())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/records/{id}/delete", id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        verify(recordService, times(1)).deleteRecord(eq(id));
    }

    @Test
    void testDeleteRecordNotFound() throws Exception {
        Integer id = 1;

        when(recordService.deleteRecord(anyInt())).thenReturn(false);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/records/{id}/delete", id))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(recordService, times(1)).deleteRecord(eq(id));
    }
}