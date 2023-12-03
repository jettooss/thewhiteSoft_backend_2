package com.example.demo.RecordTest;
import com.example.demo.Api.dto.RecordDto.RecordCreateDto;
import com.example.demo.Api.dto.RecordDto.RecordDto;
import com.example.demo.Api.dto.RecordDto.RecordUpdateDto;
import com.example.demo.service.RecordService;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ExtendWith(SoftAssertionsExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RecordControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private RecordService recordService;


    @Test
    @Order(1)
    void TestPostCreate(SoftAssertions assertions) {
        // Arrange
        RecordCreateDto recordCreateDto = RecordCreateDto.builder()
                .name("name")
                .description("name")
                .link("name")
                .build();

        // Act
        RecordDto responseDto = webTestClient.post()
                .uri("/api/records/{id}/create", 11)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(recordCreateDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RecordDto.class)
                .returnResult().getResponseBody();

        // Assert
        RecordDto expectedDto = RecordDto.builder()
                .id(11)
                .name("name")
                .description("name")
                .link("name")
                .build();

        assertions.assertThat(responseDto)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expectedDto);
    }

    @Test
    @Order(2)
    void TestDeleteRecordIsOk(SoftAssertions assertions) {
        RecordCreateDto createDto = RecordCreateDto.builder()
                .name("name")
                .description("name")
                .link("name")
                .build();
        // Act
        webTestClient.delete()
                .uri("/api/records/{id}/delete", 1)
                .exchange()
                .expectStatus();

        // Assert
        assertions.assertThat(recordService.searchByID(1)).isNull();
    }


    @Test
    @Order(3)
    void TestPostUpdateByIDIsOk(SoftAssertions assertions) {
        // Arrange
        RecordUpdateDto recordUpdateDto = RecordUpdateDto.builder()
                .name("name")
                .description("name")
                .link("name")
                .build();

        // Act
        RecordDto responseDto = webTestClient.post()
                .uri("/api/records/{id}/update", 4)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(recordUpdateDto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(RecordDto.class)
                .returnResult()
                .getResponseBody();

        // Assert
        RecordDto expectedDto = RecordDto.builder()
                .id(4)
                .name("name")
                .description("name")
                .link("name")
                .build();

        assertions.assertThat(responseDto)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expectedDto);
    }

    @Test
    @Order(4)
    void testGetAllRecords(SoftAssertions assertions) {

        // Act
        List<RecordDto> responseDtoList = webTestClient.get()
                .uri("/api/records/all")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecordDto.class)
                .returnResult().getResponseBody();

        // Assert
        assertions.assertThat(responseDtoList)
                .hasSize(3)
                .extracting("name")
                .containsExactlyInAnyOrder("weqe", "ere", "name");
    }

}