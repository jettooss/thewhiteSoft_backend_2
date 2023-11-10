package com.example.demo.Api.controller.service;
import com.example.demo.Model.Record;
import java.util.List;

public interface RecordServiceInterface {
    Record createRecord(int id,String name, String description, String link);
    Record getRecordById(Integer id);
    Record updateRecord(Integer id, String name, String description, String link);
    boolean deleteRecord(Integer id);
    String searchRecordsByName(String name);
    List<Record> getAllRecords();
}