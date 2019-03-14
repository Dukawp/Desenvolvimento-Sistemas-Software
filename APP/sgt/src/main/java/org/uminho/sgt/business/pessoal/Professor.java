package org.uminho.sgt.business.pessoal;

import org.uminho.sgt.business.aulas.Turno;
import org.uminho.sgt.business.aulas.UnidadeCurricular;

import java.util.ArrayList;
import java.util.HashMap;

public class Professor extends Utilizador {

  private boolean isDc;
  private String abreviatura;

  private HashMap<UnidadeCurricular, ArrayList<Turno>> cadeiras;

  // **************************************************************************************************************
  // ******************************************* Construtores
  // *****************************************************

  public Professor(boolean isDc,String nome, String mail) {
    super(nome, mail);
    this.isDc = isDc;

    this.cadeiras = new HashMap<UnidadeCurricular, ArrayList<Turno>>();
  }

  public Professor(
      boolean isDc,
      String abreviatura,
      HashMap<UnidadeCurricular, ArrayList<Turno>> cadeiras,
      int numero,
      String nome,
      String mail,
      String password) {
    super(numero, nome, mail, password);
    this.isDc = isDc;
    this.abreviatura = abreviatura;
    this.cadeiras = cadeiras;
  }

  // **************************************************************************************************************
  // ******************************************* Get's e Set's
  // ****************************************************

  public HashMap<UnidadeCurricular, ArrayList<Turno>> getCadeiras() {
    return cadeiras;
  }

  public void setCadeiras(HashMap<UnidadeCurricular, ArrayList<Turno>> cadeiras) {
    this.cadeiras = cadeiras;
  }

  public boolean isIsDc() {
    return isDc;
  }

  public void setIsDc(boolean isDc) {
    this.isDc = isDc;
  }

  @Override
  public String toString() {
    return "*P*" + getNumero() + " -> " + getNome();
  }

  // **************************************************************************************************************
  // ******************************************* Métodos Adicionais
  // ***********************************************

  public void addUcs(UnidadeCurricular aThis) {
    ArrayList<Turno> lista = new ArrayList<Turno>();
    this.cadeiras.put(aThis, lista);
  }

  public void removeTurnos(UnidadeCurricular aThis, Turno t) {
    for (UnidadeCurricular u : this.cadeiras.keySet()) {
      if (u.getNome().equals(aThis.getNome())) {
        this.cadeiras.get(u).remove(t);
        return;
      }
    }
  }

  public void adicionaTurnos(UnidadeCurricular aThis, Turno t) {
    this.cadeiras.get(aThis).add(t);
  }
}
