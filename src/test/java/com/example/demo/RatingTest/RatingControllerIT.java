package com.example.demo.RatingTest;
import com.example.demo.Api.dto.Ratingdto.RatingCreateDto;
import com.example.demo.Api.dto.Ratingdto.RatingDto;
import com.example.demo.Model.Rating;
import com.example.demo.service.RatingService;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureWebTestClient
//@ExtendWith(SoftAssertionsExtension.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class RatingControllerIT {
//
//
//    @Autowired
//    private WebTestClient webTestClient;
//
//    @Autowired
//    private RatingService ratingService;
//
//
//    @Test
//    @Order(1)
//    void testCreateRating(SoftAssertions assertions) {
//
//        // Arrange
//        RatingCreateDto ratingCreateDto = RatingCreateDto.builder()
//                .value(5)
//                .comment("Great!")
//                .recordId(3)
//                .build();
//
//        // Act
//        webTestClient.post()
//                .uri("/api/ratings/create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(ratingCreateDto)
//                .exchange()
//                .expectStatus().isOk();
//
//        // Assert
//        List<Rating> ratings = ratingService.getRatingsById(1);
//        System.out.println(ratings);
//        assertions.assertThat(ratings).hasSize(1);
//        assertions.assertThat(ratings.get(0).getValue()).isEqualTo(5);
//        assertions.assertThat(ratings.get(0).getComment()).isEqualTo("Great!");
//    }
//
//    @Test
//    @Order(2)
//    void testGetRatingsByRecordId(SoftAssertions assertions) {
//
//        // Arrange
//        RatingCreateDto ratingCreateDto = RatingCreateDto.builder()
//                .value(5)
//                .comment("Great!")
//                .recordId(3)
//                .build();
//
//        // Act
//        webTestClient.post()
//                .uri("/api/ratings/create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(ratingCreateDto)
//                .exchange()
//                .expectStatus().isOk();
//
//        List<RatingDto> responseDtoList = webTestClient.get()
//                .uri("/api/ratings/list-by-record?recordId=3")
//                .exchange()
//                .expectStatus().isOk()
//                .expectBodyList(RatingDto.class)
//                .returnResult().getResponseBody();
//
//        // Assert
//        assertions.assertThat(responseDtoList)
//                .extracting("value", "comment", "recordId")
//                .contains(tuple(5, "Great!", 3));
//    }
//
//    @Test
//    @Order(3)
//    void testDeleteRating(SoftAssertions assertions) {
//        // Arrange
//        RatingDto ratingCreateDto = RatingDto.builder()
//                .id(1)
//                .value(5)
//                .comment("Great!")
//                .recordId(3)
//                .build();
//        webTestClient.post()
//                .uri("/api/ratings/create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(ratingCreateDto)
//                .exchange()
//                .expectStatus().isOk();
//
//        // Act
//        webTestClient.delete()
//                .uri("/api/ratings/{id}/delete", ratingCreateDto.getId())
//                .exchange()
//                .expectStatus().isOk();
//
//        // Assert
//        List<Rating> ratingsAfterDelete = ratingService.getRatingsById(ratingCreateDto.getId());
//        assertThat(ratingsAfterDelete).isEmpty();
//    }
//
//}
