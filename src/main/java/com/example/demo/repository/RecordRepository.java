package com.example.demo.repository;
import com.example.demo.Model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


@Repository
public interface RecordRepository extends JpaRepository<Record, Integer>, JpaSpecificationExecutor<Record> {

    List<Record> findByNameIgnoreCase(String name);
    Record findById(int id);

}
