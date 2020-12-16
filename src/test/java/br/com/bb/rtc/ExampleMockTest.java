package br.com.bb.rtc;

import java.text.ParseException;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;
import br.com.bb.rtc.exceptions.ErroSqlException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class ExampleMockTest {

    //Para que esse teste funcione com o mock do quarkus, descomente a linha da classe
    //UsuarioDaoMock.java e descomente a anotation @Test abaixo
    //Isso fará que a resposta dada para a chamada dos usuários seja agora Adão e Eva
    //ao invés de Huguinho, Zezinho e Luizinho
    // @Test
    public void testObterTodosUsuarios() throws ParseException, ErroSqlException {
        given()
        .when().get("/v1/usuarios")
        .then()
        .statusCode(200)
        .assertThat()
        .body(containsString("Adão"));
    }

}