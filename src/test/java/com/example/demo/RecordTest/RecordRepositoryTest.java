package com.example.demo.RecordTest;
import com.example.demo.repository.RecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.demo.model.Record;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class RecordRepositoryTest {
    private RecordRepository recordRepository;

    @BeforeEach
    public void setup() {
        recordRepository = new RecordRepository("src/test/java/com/example/demo/resources/test-input.json", "src/test/java/com/example/demo/resources/");
        recordRepository.loadData();
    }

    @Test
    void testLoadRecordsFromJson() {
        Record record = recordRepository.getRecordById(1);
        assertNotNull(record, "Record with ID 1 should not be null");
    }

    @Test
    void testGetRecordById() {
        Record record = recordRepository.getRecordById(2);
        assertNotNull(record, "Record should not be null");
        assertEquals("A", record.getName(), "Name should match");
        assertEquals("2323qweqwe2", record.getDescription(), "Description should match");
        assertEquals("323wqewqeqwe23", record.getLink(), "Link should match");
    }


    @Test
    void testSaveToJson() {

        String testFileName = "test-save";
        try {
            assertTrue(recordRepository.saveToJson(testFileName + ".json"), "Saving records to JSON should be successful");
        } catch (IOException e) {
            fail("Failed to save records to JSON file.", e);
        }
    }
}