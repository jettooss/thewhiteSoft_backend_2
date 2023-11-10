package com.example.demo;
import com.example.demo.DataProcessor.*;
import com.example.demo.Model.Record;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;

public class DataProcessorTest {
    private RecordRepository recordRepository;

    @BeforeEach
    void setUp() {
        recordRepository = new RecordRepository("src/test/java/com/example/demo/resources/test-input.json", "src/test/java/com/example/demo/resources/");
    }

    @Test
    void testLoadRecordsFromJson() {
        assertFalse(recordRepository.getRecords().isEmpty());

        Record record = recordRepository.getRecordById(1);
        assertNotNull(record);
    }
    @Test
    void testGetRecordById() {
        Record record = recordRepository.getRecordById(2);
        assertNotNull(record);
        assertEquals("A", record.getName());
        assertEquals("2323qweqwe2", record.getDescription());
        assertEquals("323wqewqeqwe23", record.getLink());
    }
    @Test
    void testGetRecordByName() {
        Record record = recordRepository.getRecordByName("B");
        assertNotNull(record);
        assertEquals(1, record.getId());
        assertEquals("2asdad3232", record.getDescription());
        assertEquals("wqewqWQewqewqe", record.getLink());
    }
    @Test
    void testSaveToJson() {
        recordRepository.getRecords();
        String testFileName = "test-save";
        try {
            assertTrue(recordRepository.saveToJson(testFileName + ".json"));
        } catch (IOException e) {
            fail("Не удалось сохранить записи в файл JSON.");
        }

    }
}