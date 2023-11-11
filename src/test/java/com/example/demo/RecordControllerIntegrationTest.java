package com.example.demo;

import com.example.demo.Model.Record;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import com.example.demo.Api.controller.service.RecordServiceImpl;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

//@SpringBootTest
//@AutoConfigureMockMvc
//public class RecordControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private RecordServiceImpl recordServiceImpl;
//
//    @BeforeEach
//    public void setup() {
//        Record record = new Record(1, "Example Record", "Description", "example.com");
//        Mockito.when(recordServiceImpl.getRecordById(1)).thenReturn(record);
//        Mockito.when(recordServiceImpl.getAllRecords()).thenReturn(Arrays.asList(record));
//
//        // Дополнительная настройка для других методов
//        Record createdRecord = new Record(2, "New Record", "New Description", "newexample.com");
//        Mockito.when(recordServiceImpl.createRecord("New Record", "New Description", "newexample.com")).thenReturn(createdRecord);
//
//        Record updatedRecord = new Record(1, "Updated Record", "Updated Description", "updatedexample.com");
//        Mockito.when(recordServiceImpl.updateRecord(1, "Updated Record", "Updated Description", "updatedexample.com")).thenReturn(updatedRecord);
//
//        Mockito.when(recordServiceImpl.searchRecordsByName("Example Record")).thenReturn("Record found:\nID: 1\nName: Example Record\nDescription: Description\nLink: example.com\n");
//
//        Mockito.when(recordServiceImpl.deleteRecord(1)).thenReturn(true);
//        Mockito.when(recordServiceImpl.deleteRecord(2)).thenReturn(false);
//    }
//
//    @Test
//    public void testGetRecordById() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/records/1").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void testGetAllRecords() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/records/all").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void testCreateRecord() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/records/create").content("{\"name\":\"New Record\",\"description\":\"New Description\",\"link\":\"newexample.com\"}").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated());
//    }
//
//    @Test
//    public void testSearchRecordsByName() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/records/name?name=Example Record").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void testDeleteRecord() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/records/1").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNoContent());
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/api/records/2").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
//}