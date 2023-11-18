package com.example.demo;
import com.example.demo.repository.RecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.demo.Model.Record;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class RecordRepositoryTest {
    private RecordRepository recordRepository;

    @BeforeEach
    void setUp() {
        recordRepository = new RecordRepository("src/test/java/com/example/demo/resources/test-input.json", "src/test/java/com/example/demo/resources/");
    }

    @Test
    void testLoadRecordsFromJson() {
        Record record = recordRepository.getRecordById(1);
        System.out.println(   record); // Add this line for logging

        assertNotNull(record, "Record with ID 1 should not be null");
    }

    @Test
    void testGetRecordById() {
        Record record = recordRepository.getRecordById(2);
        System.out.println(   record); // Add this line for logging

        assertNotNull(record, "Record should not be null");
        assertEquals("A", record.getName(), "Name should match");
        assertEquals("2323qweqwe2", record.getDescription(), "Description should match");
        assertEquals("323wqewqeqwe23", record.getLink(), "Link should match");
    }



    @Test
    void testSaveToJson() {

        String testFileName = "test-savdddde";
        try {
            assertTrue(recordRepository.saveToJson(testFileName + ".json"), "Saving records to JSON should be successful");
        } catch (IOException e) {
            fail("Failed to save records to JSON file.", e);
        }
    }
}