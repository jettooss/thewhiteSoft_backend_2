package com.example.demo;
import com.example.demo.Api.controller.RecordController;
import com.example.demo.Api.controller.dto.DtoData;
import com.example.demo.Api.controller.dto.DtoUpdate;
import com.example.demo.Api.controller.service.RecordServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(RecordController.class)
class RecordControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecordServiceImpl recordService;

    @Test
    void testCreateRecord() throws Exception {
        DtoData inputDto = new DtoData(1, "RecordName", "RecordDescription", "http://example.com");
        DtoData createdDto = new DtoData(1, "RecordName", "RecordDescription", "http://example.com");

        when(recordService.createRecord(any(DtoData.class))).thenReturn(createdDto);

        mockMvc.perform(post("/api/records/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("RecordName"))
                .andExpect(jsonPath("$.description").value("RecordDescription"))
                .andExpect(jsonPath("$.link").value("http://example.com"));

        verify(recordService, times(1)).createRecord(any(DtoData.class));
    }

//    @Test
//    void testGetAllRecords() throws Exception {
//        DtoData dtoData = new DtoData(1, "RecordName", "RecordDescription", "http://example.com");
//
//        when(recordService.getAllRecords(anyString())).thenReturn(Collections.singletonList(dtoData));
//
//        mockMvc.perform(get("/api/records/all"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].name").value("RecordName"))
//                .andExpect(jsonPath("$[0].description").value("RecordDescription"))
//                .andExpect(jsonPath("$[0].link").value("http://example.com"));
//
//        verify(recordService, times(1)).getAllRecords(anyString());
//    }

    @Test
    void testGetRecordById() throws Exception {
        Integer id = 1;
        DtoData dtoData = new DtoData(1, "RecordName", "RecordDescription", "http://example.com");

        when(recordService.getRecordById(anyInt())).thenReturn(dtoData);

        mockMvc.perform(get("/api/records/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("RecordName"))
                .andExpect(jsonPath("$.description").value("RecordDescription"))
                .andExpect(jsonPath("$.link").value("http://example.com"));

        verify(recordService, times(1)).getRecordById(anyInt());
    }

    @Test
    void testUpdateRecord() throws Exception {
        Integer id = 1;
        DtoUpdate dtoUpdate = new DtoUpdate("UpdatedName", "UpdatedDescription", "http://updated.com");
        DtoData updatedDto = new DtoData(1, "UpdatedName", "UpdatedDescription", "http://updated.com");

        when(recordService.updateRecord(anyInt(), any(DtoUpdate.class))).thenReturn(updatedDto);

        mockMvc.perform(put("/api/records/update_{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoUpdate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("UpdatedName"))
                .andExpect(jsonPath("$.description").value("UpdatedDescription"))
                .andExpect(jsonPath("$.link").value("http://updated.com"));

        verify(recordService, times(1)).updateRecord(anyInt(), any(DtoUpdate.class));
    }

    @Test
    void testDeleteRecord() throws Exception {
        Integer id = 1;

        when(recordService.deleteRecord(anyInt())).thenReturn(true);

        mockMvc.perform(delete("/api/records/delete_{id}", id))
                .andExpect(status().isNoContent());

        verify(recordService, times(1)).deleteRecord(anyInt());
    }
}