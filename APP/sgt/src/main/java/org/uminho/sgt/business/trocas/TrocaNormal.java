package org.uminho.sgt.business.trocas;

import org.uminho.sgt.business.aulas.Turno;
import org.uminho.sgt.business.aulas.UnidadeCurricular;

public class TrocaNormal extends Troca {

  private String destinoString;
  // **************************************************************************************************************
  // ******************************************* Construtores
  // *****************************************************

  public TrocaNormal(String mail, String estado, String destinoString,String ucString) {
    super(mail, estado,ucString);
    this.destinoString = destinoString;
    
  }

  public TrocaNormal(Turno origem, Turno destino, int numero, UnidadeCurricular aThis) {
    super(numero, "Pendente", origem, destino, aThis);
  }
  

  // **************************************************************************************************************
  // ******************************************* Get's e Set's
  // ****************************************************

    public String getDestinoString() {
        return destinoString;
    }

    public void setDestinoString(String destinoString) {
        this.destinoString = destinoString;
    }
  
  
  

  @Override
  public String toString() {
    return "*T* "
        + getNumOrigem()
        + " ** UC= "
        + getUc().getAbreviatura()
        + ":"
        + getOrigem().getTipoAula()
        + " -> "
        + getDestino().getTipoAula();
  }
}
