package br.com.bb.rtc.exceptions;

import br.com.bb.dev.ext.error.ChavesMonitoradasSQL;
import br.com.bb.dev.ext.error.Erro;
import br.com.bb.dev.ext.error.interfaces.IEnumChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IEnumErro;
import br.com.bb.dev.ext.error.interfaces.IErro;

public enum ErrosSistema implements IEnumErro {
    ERRO_SISTEMA("001","Erro Negocial Teste", ChavesMonitoradasSistema.class),
    ERRO_SQL("900","Erro no sistema", ChavesMonitoradasSistema.class,ChavesMonitoradasSQL.class),
    INFORME_DATA_NASCIMENTO("001","Informe a data de nascimento"),
    ERRO_EXCLUSAO_USUARIO("002","Não foi possivel excluir o usuario do identificador.",ChavesMonitoradasSistema.class),
    ERRO_INCLUSAO_USUARIO("003","O usuario deve ter mais que %s anos.", ChavesMonitoradasSistema.class),
    INFORME_CODIGO_CLIENTE("004", "Codigo do cliente não informado.", ChavesMonitoradasSistema.class),
    TEXTO_ENTRADA_NAO_INFORMADO("004", "Texto de entrada nao foi informado",ChavesMonitoradasSistema.class);

    String codigo;
    String mensagem;
    Class<? extends IEnumChaveMonitorada>[] enumsChaves;

    ErrosSistema(String codigo, String mensagem, Class<? extends IEnumChaveMonitorada>... enumsChaves) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.enumsChaves = enumsChaves;
    }
    ErrosSistema(String codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    @Override
    public IErro get() {
        return new Erro(codigo, mensagem, enumsChaves);
    }
}

