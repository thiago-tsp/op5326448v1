package br.com.bb.rtc;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

@QuarkusTest
public class ContractErrosTest {

    @Test
    public void testReturnUsersListJSON() {
        given()
            .when().get("/v1/usuarios")
            .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/JsonSchema.json"));
    }

    @Test
    public void testStatusCode500dataInvalida(){

        String payload = "{\n" +
        "\"id\": 4,\n" +
        "\"nome\": \"Chico Chicote\",\n" +
        "\"nascimento\": \"Data inválida\"\n" +
        "}";

        given()
        .contentType(ContentType.JSON)
        .body(payload)
        .when().post("/v1/usuarios")
        .then()
        .statusCode(500)
        .and()
        .body(matchesJsonSchemaInClasspath("schemas/JsonSchemaError.json"));
}

@Test
public void testStatusCode422dataInvalida(){

        String payload = "{\n" +
        "\"id\": 4,\n" +
        "\"nome\": \"Chico Chicote\",\n" +
        "\"nascimento\": \"2019-11-19T00:00:00Z[UTC]\"\n" +
        "}";

        given()
        .contentType(ContentType.JSON)
        .body(payload)
        .when().post("/v1/usuarios")
        .then()
        .statusCode(422)
        .and()
        .body(matchesJsonSchemaInClasspath("schemas/JsonSchemaError.json"));
}

}