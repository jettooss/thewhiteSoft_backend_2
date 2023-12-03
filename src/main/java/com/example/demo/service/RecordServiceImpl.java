package com.example.demo.service;
import com.example.demo.service.argument.CreateArgument;
import com.example.demo.service.argument.UpdateArgument;
import com.example.demo.repository.RecordRepository;
import com.example.demo.Model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class RecordServiceImpl implements RecordService {

    private final RecordRepository repository;

    public RecordServiceImpl(RecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public Record create(CreateArgument argument) {
        Record record = Record.builder()
                .name(argument.getName())
                .description(argument.getDescription())
                .link(argument.getLink())
                .build();
        return repository.save(record);
    }


    @Override
    public Optional<Record> update(Integer id, UpdateArgument argument) {
        Optional<Record> existingRecord = repository.findById(id);

        if (existingRecord.isPresent()) {
            Record recordToUpdate = existingRecord.get();
            recordToUpdate.setName(argument.getName());
            recordToUpdate.setDescription(argument.getDescription());
            recordToUpdate.setLink(argument.getLink());

            repository.save(recordToUpdate);
            return existingRecord;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Record> getAll(String name) {

        return repository.findByNameIgnoreCase(name);

    }

    @Override
    public Record searchByID(Integer id) {
        return repository.findById(id).orElse(null);
    }


    @Override
    public Page<Record> getRecordsByCriteria(
            String partialName, String partialDescription, String sortBy, String sortDirection, Pageable pageable) {
        Specification<Record> spec = Specification.where(nameContains(partialName))
                .and(descriptionContains(partialDescription));

        return repository.findAll(spec, pageable);
    }

    @Override
    public void deleteRecord(Integer id) {
        repository.deleteById(id);
    }


    private Specification<Record> nameContains(String partialName) {
        return (root, query, criteriaBuilder) ->
                partialName == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + partialName.toLowerCase() + "%");
    }

    private Specification<Record> descriptionContains(String partialDescription) {
        return (root, query, criteriaBuilder) ->
                partialDescription == null ? criteriaBuilder.conjunction() :
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),
                                "%" + partialDescription.toLowerCase() + "%");
    }

}
