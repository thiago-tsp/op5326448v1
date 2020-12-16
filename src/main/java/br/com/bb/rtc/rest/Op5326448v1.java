package br.com.bb.rtc.rest;

import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.opentracing.Traced;

import antlr.collections.List;
import br.com.bb.dev.ext.exceptions.ErroNegocialException;
import br.com.bb.rtc.dao.ClienteDao;
import br.com.bb.rtc.exceptions.ErrosSistema;
import br.com.bb.rtc.models.Cliente;
import br.com.bb.rtc.operacao.listarInformacoesClienteV1.bean.requisicao.DadosRequisicaoListarInformacoesCliente;
import br.com.bb.rtc.operacao.listarInformacoesClienteV1.bean.resposta.DadosRespostaListarInformacoesCliente;
import br.com.bb.rtc.operacao.listarInformacoesClienteV1.bean.resposta.ListaOcorrenciaCliente;

@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Path("/op5326448v1")
@Traced
@RequestScoped
public class Op5326448v1 {
	
	@Inject
	private ClienteDao dao;

    @POST
    // Anotações para documentação no Swagger
    @Operation(summary = "ListarInformacoesCliente", description = "Descricao da sua operação: ListarInformacoesCliente")
    @APIResponse(responseCode = "200", description = "DadosRespostaListarInformacoesCliente",
        content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = DadosRespostaListarInformacoesCliente.class))})
    public DadosRespostaListarInformacoesCliente servir(DadosRequisicaoListarInformacoesCliente requisicao) throws ErroNegocialException {

        validarEntrada(requisicao);

        DadosRespostaListarInformacoesCliente resposta = tratarRequisicao(requisicao);

        return resposta;
    }

    private void validarEntrada(DadosRequisicaoListarInformacoesCliente requisicao) throws ErroNegocialException{
        
    	if(requisicao.getCodigoCliente() == 0) {
    		throw new ErroNegocialException(ErrosSistema.INFORME_CODIGO_CLIENTE.get());
    	}
    }

    private DadosRespostaListarInformacoesCliente tratarRequisicao(DadosRequisicaoListarInformacoesCliente requisicao) throws ErroNegocialException{
        
        DadosRespostaListarInformacoesCliente resposta = new DadosRespostaListarInformacoesCliente();
        
        ArrayList<ListaOcorrenciaCliente> lista = new ArrayList<ListaOcorrenciaCliente>();
        
        lista.addAll(preencherLista(dao.listarInformacoes(requisicao.getCodigoCliente())));
        
        return resposta;
    }
    
    private Collection<ListaOcorrenciaCliente> preencherLista(List<Cliente> listaEntrada) {
    	
    	ArrayList<ListaOcorrenciaCliente> listaSaida = new ArrayList<ListaOcorrenciaCliente>();
    	
    	for (Cliente cliente : listaEntrada) {
    		ListaOcorrenciaCliente ocorrencia = new ListaOcorrenciaCliente();
    		
    		if(cliente != null) {
    			
    			ocorrencia.setCodigoCausaPerdaMargemContribuicao(cliente.getCodigoCausaPerdaMargemContribuicao());
    			ocorrencia.setCodigoIndicioPerdaCliente(cliente.getCodigoIndicioPerdaCliente());
    			ocorrencia.setNumeroOrdemNivelIndicio(cliente.getNumeroOrdemNivelIndicio());
    			ocorrencia.setNomeEventoPerdaMargemContribuicao(cliente.getNomeEventoPerdaMargemContribuicao());   			
    			
    			listaSaida.add(ocorrencia);			
    		}  		    		
    	}
    	
    	return listaSaida;    	
    	
    }
    
    
    
}
