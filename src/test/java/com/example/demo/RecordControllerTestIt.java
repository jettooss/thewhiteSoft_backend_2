package com.example.demo;
import com.example.demo.Api.dto.Ratingdto.RatingCreateDto;
import com.example.demo.Api.dto.RecordDto.RecordCreateDto;
import com.example.demo.Api.dto.RecordDto.RecordResponseDto;
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
        RecordResponseDto responseDto = webTestClient.post()
                .uri("/api/records/{id}/create", 4)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(recordCreateDto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RecordResponseDto.class)
                .returnResult().getResponseBody();

        // Assert
        RecordResponseDto expectedDto = RecordResponseDto.builder()
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
    @Order(2)
    void TestDeleteRecordIsOk(SoftAssertions assertions) {
        RecordCreateDto createDto  = RecordCreateDto.builder()
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
        RecordResponseDto responseDto = webTestClient.post()
                .uri("/api/records/{id}/update", 4)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(recordUpdateDto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(RecordResponseDto.class)
                .returnResult()
                .getResponseBody();

        // Assert
        RecordResponseDto expectedDto = RecordResponseDto.builder()
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
    void testPostCreateValidation(SoftAssertions assertions) {
        // Arrange
        RatingCreateDto invalidDto = RatingCreateDto.builder()
                .value(10)  // This value is invalid, as it should be between 1 and 5
                .comment("") // This comment is invalid, as it should not be empty
                .build();

        // Act
        webTestClient.post()
                .uri("/api/ratings/{id}/create", 4)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(invalidDto)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .value(responseBody ->
                        assertions.assertThat(responseBody)
                                .contains("Значение должно быть между 1 и 5")
                                .contains("Комментарий не может быть пустым")
                );
    }

 }