package com.example.demo;
import com.example.demo.service.RatingServiceImpl;
import com.example.demo.service.argument.CreateArgument;
import com.example.demo.service.argument.UpdateArgument;
import com.example.demo.repository.RecordRepository;
import com.example.demo.service.RecordServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.demo.Model.Record;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class RecordServiceTest {

    @Mock
    private RecordRepository repository;

    @InjectMocks
    private RecordServiceImpl recordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateRecord() {
        // Arrange
        CreateArgument createArgument = new CreateArgument("TestName", "TestDescription", "TestLink");
        Record createdRecord = Record.builder().name("TestName").description("TestDescription").link("TestLink").build();
        when(repository.save(any())).thenReturn(createdRecord);

        // Act
        Record result = recordService.create(createArgument);

        // Assert
        assertNotNull(result);
        assertEquals("TestName", result.getName());
        assertEquals("TestDescription", result.getDescription());
        assertEquals("TestLink", result.getLink());
        verify(repository, times(1)).save(any());
    }

    @Test
    void testUpdateRecord() {
        // Arrange
        Integer recordId = 1;
        UpdateArgument updateArgument = new UpdateArgument("UpdatedName", "UpdatedDescription", "UpdatedLink");
        Record existingRecord = Record.builder().id(recordId).name("OldName").description("OldDescription").link("OldLink").build();
        when(repository.getRecordById(recordId)).thenReturn(existingRecord);

        // Act
        Optional<Record> result = recordService.updateRecord(recordId, updateArgument);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("UpdatedName", existingRecord.getName());
        assertEquals("UpdatedDescription", existingRecord.getDescription());
        assertEquals("UpdatedLink", existingRecord.getLink());
        verify(repository, times(1)).update(existingRecord);
    }

    @Test
    void testUpdateRecordNonExistentRecord() {
        // Arrange
        Integer nonExistentRecordId = 999;
        UpdateArgument updateArgument = new UpdateArgument("UpdatedName", "UpdatedDescription", "UpdatedLink");
        when(repository.getRecordById(nonExistentRecordId)).thenReturn(null);

        // Act
        Optional<Record> result = recordService.updateRecord(nonExistentRecordId, updateArgument);

        // Assert
        assertFalse(result.isPresent());
        verify(repository, never()).update(any());
    }

    @Test
    void testDeleteRecord() {
        // Arrange
        Integer recordId = 1;
        when(repository.deleteById(recordId)).thenReturn(true);

        // Act
        boolean result = recordService.deleteRecord(recordId);

        // Assert
        assertTrue(result);
        verify(repository, times(1)).deleteById(recordId);
    }

    @Test
    void testGetAllRecords() {
        // Arrange
        String name = "TestName";
        List<Record> records = Arrays.asList(
                Record.builder().name(name).description("Desc1").link("Link1").build(),
                Record.builder().name(name).description("Desc2").link("Link2").build()
        );
        when(repository.getRecordsByName(name)).thenReturn(records);

        // Act
        List<Record> result = recordService.getAllRecords(name);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repository, times(1)).getRecordsByName(name);
    }

    @Test
    void testSearchByID() {
        // Arrange
        Integer recordId = 1;
        Record record = Record.builder().id(recordId).name("TestName").description("TestDescription").link("TestLink").build();
        when(repository.getRecordById(recordId)).thenReturn(record);

        // Act
        Record result = recordService.searchByID(recordId);

        // Assert
        assertNotNull(result);
        assertEquals(record, result);
        verify(repository, times(1)).getRecordById(recordId);
    }
}
