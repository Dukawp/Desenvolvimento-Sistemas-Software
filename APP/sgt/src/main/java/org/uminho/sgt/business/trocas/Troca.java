package org.uminho.sgt.business.trocas;

import org.uminho.sgt.business.aulas.Turno;
import org.uminho.sgt.business.aulas.UnidadeCurricular;

public abstract class Troca {

  private int numOrigem;
  private String estado;
  private String ucString;

  private Turno origem;
  private Turno destino;
  private UnidadeCurricular uc;

  // **************************************************************************************************************
  // ******************************************* Construtores
  // *****************************************************

  public Troca(String mailOrigem, String estado,String ucString) {
    this.numOrigem = parseMail(mailOrigem);
    this.estado = estado;
    this.ucString = ucString;

    this.origem = null;
    this.destino = null;
    this.uc = null;
  }

  public Troca(int numOrigem, String estado, Turno origem, Turno destino, UnidadeCurricular uc) {
    this.numOrigem = numOrigem;
    this.estado = estado;
    this.origem = origem;
    this.destino = destino;
    this.uc = uc;
  }

  // **************************************************************************************************************
  // ******************************************* Get's e Set's
  // ****************************************************

  public Turno getOrigem() {
    return origem;
  }

  public void setOrigem(Turno origem) {
    this.origem = origem;
  }

 public String getUcString() {
    return ucString;
 }

  public Turno getDestino() {
    return destino;
  }

  public void setDestino(Turno destino) {
    this.destino = destino;
  }

  public int getNumOrigem() {
    return numOrigem;
  }

  public void setNumOrigem(int numOrigem) {
    this.numOrigem = numOrigem;
  }

  public UnidadeCurricular getUc() {
    return uc;
  }

  public void setUc(UnidadeCurricular uc) {
    this.uc = uc;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }


    public void setUcString(String ucString) {
        this.ucString = ucString;
    }

  
  
  @Override
  public String toString() {
    return "*T* "
        + this.numOrigem
        + " ** UC= "
        + uc.getAbreviatura()
        + ":"
        + origem
        + " -> "
        + destino;
  }

   private int parseMail(String mail) {
        String parts[] = mail.split("@");
        int numero = Integer.parseInt(parts[0]);
        return numero;
    }
}
