package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class GreetingResourceTest {
    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }

    @Test
    public void createBookWithGeneratedId() {
        String author = given()
                .when().get("/library/author/2")
                .then()
                .statusCode( HttpStatus.SC_OK)
                .extract().body().asString();
        assertEquals("Vern", author);
        Response creation = given()
                .log().all().filter(new ResponseLoggingFilter())
                .put("library/books/2/Around_the_World_in_Eighty_Days");
        assertEquals(HttpStatus.SC_CREATED, creation.statusCode());
        given()
                .when().get("library/books/author/Vern")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("$", hasItem("Around_the_World_in_Eighty_Days"));
    }

}