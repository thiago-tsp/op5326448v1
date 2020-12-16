package br.com.bb.rtc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.eclipse.microprofile.opentracing.Traced;

import br.com.bb.rtc.exceptions.ErroSqlException;
import br.com.bb.rtc.models.Cliente;

@Traced
@RequestScoped
public class ClienteDao {

    @PersistenceContext
    EntityManager em;

    public List<Cliente> listarInformacoes(Integer codigoCliente) throws ErroSqlException {
        String nameQuery = "LISTAR_INFORMACAO_CLIENTE";

        TypedQuery<Cliente> query = em.createNamedQuery(nameQuery, Cliente.class);
        
        query.setParameter("CODIGO_CLIENTE", codigoCliente);
        
        try {
            List<Cliente> resultList = query.getResultList();
            return resultList;
        } catch (NoResultException e){
            return new ArrayList<>();
        } catch (PersistenceException e){
            throw new ErroSqlException(e);
        }
    }    
}
