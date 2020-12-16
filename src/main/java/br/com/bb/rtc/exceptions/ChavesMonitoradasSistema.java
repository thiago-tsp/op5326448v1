package br.com.bb.rtc.exceptions;

import br.com.bb.dev.ext.error.ChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IChaveMonitorada;
import br.com.bb.dev.ext.error.interfaces.IEnumChaveMonitorada;

public enum ChavesMonitoradasSistema implements IEnumChaveMonitorada {
  CPF("CPF-CLIENTE",14),
  IDADE("IDADE",3),
  MINHA_CHAVE("chaveMensageria",20),
  CHAVEPIX("CHAVE-PIX",25),
  ;

  IChaveMonitorada chaveMonitorada;

  ChavesMonitoradasSistema(String chave, int tamanho) {
    chaveMonitorada = new ChaveMonitorada(chave,tamanho);
  }

  @Override
  public IChaveMonitorada get() {
    return chaveMonitorada;
  }
}

