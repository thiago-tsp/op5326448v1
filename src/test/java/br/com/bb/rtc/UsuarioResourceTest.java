package br.com.bb.rtc;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class UsuarioResourceTest {

    @Test
    public void testObterTodosUsuarios() {
        given()
                .when().get("/v1/usuarios")
                .then()
                .statusCode(200);
    }

}