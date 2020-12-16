package br.com.bb.rtc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

@Entity
@NamedNativeQueries({
    @NamedNativeQuery(name="LISTAR_INFORMACAO_CLIENTE",    		
    		query =
    		
    				" SELECT "
                  		  + " DISTINCT(A.CD_IDCO_PDA_CLI), "
                  		  + " A.CD_CAS_PDA_MGCT, "
                  		  + " A.NR_ORD_NVL_IDCO, "
                  		  + " CASE "
                  		  + 	" WHEN "
                  		  + 	" A.CD_CAS_PDA_MGCT = 1 "
                  		  + 	" THEN "
                  		  + 	" (SELECT TRIM(D.NM_EVT_PDA_MGCT) AS NM_EVT_PDA_MGCT "
                  		  + 		" FROM DB2RTC.EVT_PDA_MGCT D "
                  		  + 		" WHERE D.CD_EVT_PDA_MGCT = A.CD_IDCO_PDA_CLI "
                  		  + 		" AND D.CD_TIP_MC_PDA_MGCT = 2 "
                  		  + 		" AND D.IN_ATVC_EVT_PDA = 'S') "
                  		  + 	" WHEN "
                  		  + 	" A.CD_CAS_PDA_MGCT = 2 "
                  		  + 	" THEN "
                  		  + 	" (SELECT TRIM(E.NM_FTR_PDA_MGCT) NM_FTR_PDA_MGCT "
                  		  + 		" FROM DB2RTC.FTR_PDA_MGCT E "
                  		  + 		" WHERE E.CD_FTR_PDA_MGCT = A.CD_IDCO_PDA_CLI "
                  		  + 		" AND E.CD_TIP_MC_PDA_MGCT = 2 "
                  		  + 		" AND E.IN_ATVC_FTR_PDA = 'S') "
                  		  + " END AS NM_EVT_PDA_MGCT "
                  		  + " FROM DB2RTC.NVL_IDCO_PDA_CLI A "
                  		  + " WHERE A.CD_PSS = :CODIGO_CLIENTE "
                  		  + " AND A.CD_TIP_MC_PDA_MGCT = 2 "
                  		  + " ORDER BY A.NR_ORD_NVL_IDCO ASC ",
    		
    		resultClass = Cliente.class),
   
})
public class Cliente {
	
	@Column(name="NR_ORD_NVL_IDCO")
	private Integer numeroOrdemNivelIndicio;	
	
	@Id
	@Column(name="CD_IDCO_PDA_CLI")
	private Integer codigoIndicioPerdaCliente;
	
	@Column(name="CD_CAS_PDA_MGCT")
	private Integer codigoCausaPerdaMargemContribuicao;
	
	@Column(name="NM_EVT_PDA_MGCT")
	private String nomeEventoPerdaMargemContribuicao;
	
	public Cliente() {
	}

	public Cliente(Integer numeroOrdemNivelIndicio, Integer codigoIndicioPerdaCliente,
			Integer codigoCausaPerdaMargemContribuicao, String nomeEventoPerdaMargemContribuicao) {
		super();
		this.numeroOrdemNivelIndicio = numeroOrdemNivelIndicio;
		this.codigoIndicioPerdaCliente = codigoIndicioPerdaCliente;
		this.codigoCausaPerdaMargemContribuicao = codigoCausaPerdaMargemContribuicao;
		this.nomeEventoPerdaMargemContribuicao = nomeEventoPerdaMargemContribuicao;
	}

	public Integer getNumeroOrdemNivelIndicio() {
		return numeroOrdemNivelIndicio;
	}

	public void setNumeroOrdemNivelIndicio(Integer numeroOrdemNivelIndicio) {
		this.numeroOrdemNivelIndicio = numeroOrdemNivelIndicio;
	}

	public Integer getCodigoIndicioPerdaCliente() {
		return codigoIndicioPerdaCliente;
	}

	public void setCodigoIndicioPerdaCliente(Integer codigoIndicioPerdaCliente) {
		this.codigoIndicioPerdaCliente = codigoIndicioPerdaCliente;
	}

	public Integer getCodigoCausaPerdaMargemContribuicao() {
		return codigoCausaPerdaMargemContribuicao;
	}

	public void setCodigoCausaPerdaMargemContribuicao(Integer codigoCausaPerdaMargemContribuicao) {
		this.codigoCausaPerdaMargemContribuicao = codigoCausaPerdaMargemContribuicao;
	}

	public String getNomeEventoPerdaMargemContribuicao() {
		return nomeEventoPerdaMargemContribuicao;
	}

	public void setNomeEventoPerdaMargemContribuicao(String nomeEventoPerdaMargemContribuicao) {
		this.nomeEventoPerdaMargemContribuicao = nomeEventoPerdaMargemContribuicao;
	}

	@Override
	public String toString() {
		return "Cliente [numeroOrdemNivelIndicio=" + numeroOrdemNivelIndicio + ", codigoIndicioPerdaCliente="
				+ codigoIndicioPerdaCliente + ", codigoCausaPerdaMargemContribuicao="
				+ codigoCausaPerdaMargemContribuicao + ", nomeEventoPerdaMargemContribuicao="
				+ nomeEventoPerdaMargemContribuicao + "]";
	}

   
}
