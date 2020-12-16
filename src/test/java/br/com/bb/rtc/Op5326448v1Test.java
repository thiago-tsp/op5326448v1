package br.com.bb.rtc;

import br.com.bb.rtc.operacao.listarInformacoesClienteV1.bean.requisicao.DadosRequisicaoListarInformacoesCliente;
import br.com.bb.rtc.operacao.listarInformacoesClienteV1.bean.resposta.DadosRespostaListarInformacoesCliente;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class Op5326448v1Test {

    @Test
    public void testOp5326448v1() {

        DadosRequisicaoListarInformacoesCliente requisicao = new DadosRequisicaoListarInformacoesCliente();
        requisicao.setCodigoCliente(355);        

        //Preencher o objeto de requisicao

        given()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .when()
            .post("/op5326448v1")
            .then()
            .statusCode(200);
    }
    
    @Test
    public void testOp5326448v1Zerado() {

        DadosRequisicaoListarInformacoesCliente requisicao = new DadosRequisicaoListarInformacoesCliente();
        requisicao.setCodigoCliente(0);        

        //Preencher o objeto de requisicao

        given()
            .contentType(ContentType.JSON)
            .body(requisicao)
            .when()
            .post("/op5326448v1")
            .then()
            .statusCode(422);
    }
}
