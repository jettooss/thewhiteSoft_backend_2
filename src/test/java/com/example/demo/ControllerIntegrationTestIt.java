package com.example.demo;
import com.example.demo.Api.controller.RecordController;
import com.example.demo.Api.controller.dto.DtoData;
import com.example.demo.Api.controller.dto.DtoUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



@SpringBootTest
@AutoConfigureMockMvc
class ControllerIntegrationTestIt {


    @Autowired
    private RecordController recordController;

    private DtoData createSampleDto() {
        return DtoData.builder()
                .name("Name")
                .description("Description")
                .link("http://link.com")
                .build();
    }

    @Test
    void TestPostCreate() {
        DtoData material = createSampleDto();
        DtoData dto = new DtoData(material.getName(), material.getDescription(), material.getLink());
        // Act
        ResponseEntity<DtoData> responseEntity = recordController.createRecord(dto);

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        DtoData response = responseEntity.getBody();

        DtoData expectedDto = DtoData.builder()
                .id(response.getId())
                .name("Name")
                .description("Description")
                .link("http://link.com")
                .build();

        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(expectedDto);
    }

    @Test
    void TestGetRecordById() {
        // Arrange
        DtoData material = createSampleDto();
        DtoData createdDtoData = recordController.createRecord(material).getBody();

        // Act
        ResponseEntity<DtoData> responseEntity = recordController.getRecordById(createdDtoData.getId());

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        DtoData response = responseEntity.getBody();

        assertThat(response).isNotNull();

    }

    @Test
    void testUpdateRecord() {
        // Arrange
        DtoData material = createSampleDto();
        DtoData createdDtoData = recordController.createRecord(material).getBody();
        // Act
        ResponseEntity<DtoData> responseEntity = recordController.updateRecord(createdDtoData.getId(), new DtoUpdate("UpdatedName", "UpdatedDescription", "UpdatedLink"));

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        DtoData response = responseEntity.getBody();

        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(DtoData.builder()
                        .id(response.getId())

                .name("UpdatedName")
                .description("UpdatedDescription")
                .link("UpdatedLink")
                .build());
    }

    @Test
    void testDeleteRecord() {
        // Arrange
        DtoData material = createSampleDto();
        DtoData createdDtoData = recordController.createRecord(material).getBody();

        // Act
        ResponseEntity<Void> responseEntity = recordController.deleteRecord(createdDtoData.getId());

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void testGetAllRecords() {
        // Arrange
        DtoData material = createSampleDto();
        DtoData createdDtoData = recordController.createRecord(material).getBody();

        // Act
        ResponseEntity<List<DtoData>> responseEntity = recordController.getAllRecords(createdDtoData.getName());

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<DtoData> response = responseEntity.getBody();

        assertThat(response).isNotNull();

    }
}