package com.example.demo.DataProcessor;
import com.example.demo.Model.Record;
import org.springframework.stereotype.Service;


@Service
public class RecordService {
    private final RecordRepository repository;

    public RecordService(RecordRepository repository) {
        this.repository = repository;
    }
    public String searchRecordsById(Integer id) {
        Record record = repository.getRecordById(id);
        if (record != null) {
            return "Запись найдена:\n" +
                    "Идентификатор: " + record.getId() + "\n" +
                    "Наименование: " + record.getName() + "\n" +
                    "Описание: " + record.getDescription() + "\n" +
                    "Ссылка: " + record.getLink() + "\n";
        } else {
            return "Запись не найдена.\n";
        }
    }
    public String searchRecordsByName(String name) {
        Record record = repository.getRecordByName(name);
        if (record != null) {
            return "Запись найдена:\n" +
                    "Идентификатор: " + record.getId() + "\n" +
                    "Наименование: " + record.getName() + "\n" +
                    "Описание: " + record.getDescription() + "\n" +
                    "Ссылка: " + record.getLink() + "\n";
        } else {
            return "Запись не найдена.\n";
        }
    }
    public Record addRecord(int id, String name, String description, String link) {
        Record newRecord = new Record(id, name, description, link);
        repository.put(newRecord);
        return newRecord;
    }
}