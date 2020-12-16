package br.com.bb.rtc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bb.rtc.dao.ClienteDao;
import br.com.bb.dev.ext.exceptions.ErroNegocialException;
import br.com.bb.rtc.exceptions.ErroSqlException;
import br.com.bb.rtc.models.Cliente;
import br.com.bb.rtc.services.UsuarioService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Esta classe contem dois exemplos de teste de unidade utilizando a classe mockito.
// Os testes abaixo verificam as validações feitas na classe UsuarioService.java

@TestInstance(Lifecycle.PER_CLASS)
public class ExampleMockitoTest {

    //classe que será chamada
    @InjectMocks
    UsuarioService us;

    //classe que deverá ser mockada ao fazer a chamada do service acima
    @Mock
    ClienteDao usuarioDao;

    @BeforeAll
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void insereIdadeMenor18Anos() throws ErroSqlException, ErroNegocialException {


        //Criamos um usuário com menos de 18 anos
        Cliente usuarioMenor = new Cliente(99, "Adão", new Date());

        //Dizemos para a classe o que deve ser retornado utilizando o mockito
        Mockito.when(usuarioDao.inserirUsuario(usuarioMenor)).thenReturn(1);

        //Como essa classe lança uma excecao, verificamos como o código abaixo.
        ErroNegocialException thrown = assertThrows(
            ErroNegocialException.class,
           () -> us.inserirUsuario(usuarioMenor),
           "Expected doThing() to throw, but it didn't"
        );
        
        assertTrue(thrown.getMessage().contains("O usuario deve ter mais que"));
    }

    @Test
    public void insereIdadeMaior18Anos() throws ErroSqlException, ErroNegocialException {


        //Criamos um usuário com menos de 18 anos
        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        try {
            data = sd.parse("26/08/1982");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Cliente usuarioMaior = new Cliente(99, "Adão", data);

        //Dizemos para a classe o que deve ser retornado utilizando o mockito
        Mockito.when(usuarioDao.inserirUsuario(usuarioMaior)).thenReturn(1);

        us.inserirUsuario(usuarioMaior);
        //Nesse caso simplesmente fazermos a verificação se o usuário é maior de 18 anos e nao se lança excecao
    }
}