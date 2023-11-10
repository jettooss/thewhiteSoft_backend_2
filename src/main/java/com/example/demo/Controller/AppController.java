package com.example.demo.Controller;
import com.example.demo.Model.Record;
import com.example.demo.DataProcessor.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;


@RequiredArgsConstructor
@Component
public class AppController implements CommandLineRunner {
    private final RecordService recordService;
    private final RecordRepository recordRepository;

    public void run(String[] args) throws IOException {
        Map<Integer, Record> records = recordRepository.getRecords();
        while (true) {
            Scanner scanner = new Scanner(System.in);

            Menu.printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    searchRecordsById(scanner, records);
                    break;
                case 2:
                    searchRecordsByName(scanner, records);
                    break;
                case 5:
                    addRecord(scanner, records);
                    break;
                case 4:
                    saveRecordsToJson(scanner);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте ещё раз.");
            }
        }
    }
    private void searchRecordsById(Scanner scanner, Map<Integer, Record> records) {
        System.out.print("Введите идентификатор записи: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        String result = recordService.searchRecordsById(id);
        System.out.println(result);
    }
    private void searchRecordsByName(Scanner scanner, Map<Integer, Record> records) {
        System.out.print("Введите часть наименования для поиска: ");
        String searchTerm = scanner.nextLine();
        String result = recordService.searchRecordsByName(searchTerm);
        System.out.println(result);
    }
    private  void saveRecordsToJson(Scanner scanner) throws IOException, IOException {
        System.out.print("Введите имя файла для сохранения данных: ");
        String fileName = scanner.nextLine();
        recordRepository.saveToJson(fileName);
    }
    private void addRecord(Scanner scanner, Map<Integer, Record> records) {
        System.out.print("Введите идентификатор записи: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите наименование записи: ");
        String name = scanner.nextLine();
        System.out.print("Введите описание записи: ");
        String description = scanner.nextLine();
        System.out.print("Введите ссылку на запись: ");
        String link = scanner.nextLine();
        Record addedRecord = recordService.addRecord(id, name, description, link);
        System.out.println("Запись успешно добавлена в Map: " + addedRecord);
    }
}