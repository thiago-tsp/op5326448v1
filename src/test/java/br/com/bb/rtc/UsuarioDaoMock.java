package br.com.bb.rtc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import br.com.bb.rtc.dao.ClienteDao;
import br.com.bb.rtc.exceptions.ErroSqlException;
import br.com.bb.rtc.models.Cliente;
import io.quarkus.test.Mock;

//O uso dessa classe irá mockar os retornos em todo o escopo de teste(Utilizando @Quarkus Teste).
//A chamada ao método buscaUsuários() irá retornar uma lista mockada ao invés da chamada regular.
//É muito importande que a classe a ser mockada seja herdada nessa nova classe e que o arquivo esteja na pasta de testes.
//Para que o mock seja ativado, basta descomentar a linha abaixo que utiliza io.quarkus.test.Mock;

@Mock
@RequestScoped
public class UsuarioDaoMock extends ClienteDao{

    @Override
    public List<Cliente> buscaUsuarios() throws ErroSqlException {

        SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();
        try {
            data = sd.parse("26/08/1982");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Cliente user2 = new Cliente(99, "Adão", data);
        Cliente user1 = new Cliente(99, "Eva", data);

        List<Cliente> listaMockada = new ArrayList<>();
        listaMockada.add(user1);
        listaMockada.add(user2);

        return listaMockada;
    }
}
