package com.fm.integration;

import com.fm.Application;
import com.fm.config.DbInit;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {Application.class, DbInit.class}
)
class SampleIntegrationTest {

    static String API_VISITS = "/api/visits";

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port + API_VISITS;
        RestAssured.port = port;
    }

    @Test
    void givenRequest_whenFindAll_thenReturn200() {
        given()
            .when()
            .get(API_VISITS)
            .then()
            .assertThat()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("[0]", notNullValue());
    }
}
