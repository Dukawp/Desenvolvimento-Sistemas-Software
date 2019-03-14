package org.uminho.sgt.business.pessoal;

import org.uminho.sgt.business.aulas.Turno;
import org.uminho.sgt.business.aulas.UnidadeCurricular;
import org.uminho.sgt.business.trocas.Troca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Aluno extends Utilizador {

  private boolean estatuto;

  private HashMap<UnidadeCurricular, Turno> cadeiras;
  private HashMap<String, Boolean[]> horario;
  private ArrayList<Troca> trocas;

  // **************************************************************************************************************
  // ******************************************* Construtores
  // *****************************************************

  public Aluno(boolean estatuto, String nome, String mail) {
    super(nome, mail);
    this.estatuto = estatuto;

    this.cadeiras = new HashMap<UnidadeCurricular, Turno>();
    this.horario = new HashMap<String, Boolean[]>();
    this.trocas = new ArrayList<Troca>();
  }

  public Aluno(boolean estatuto, HashMap<UnidadeCurricular, Turno> cadeiras, HashMap<String, Boolean[]> horario, ArrayList<Troca> trocas, int numero, String nome, String mail, String password) {
    super(numero, nome, mail, password);
    this.estatuto = estatuto;
    this.cadeiras = cadeiras;
    this.horario = horario;
    this.trocas = trocas;
  }



  // **************************************************************************************************************
  // ******************************************* Get's e Set's
  // ****************************************************

  public HashMap<UnidadeCurricular, Turno> getCadeiras() {
    return cadeiras;
  }

  public void setCadeiras(HashMap<UnidadeCurricular, Turno> cadeiras) {
    this.cadeiras = cadeiras;
  }

  public boolean isEstatuto() {
    return estatuto;
  }

  public void setEstatuto(boolean estatuto) {
    this.estatuto = estatuto;
  }

  public HashMap<String, Boolean[]> getHorario() {
    return horario;
  }

  public void setHorario(HashMap<String, Boolean[]> horario) {
    this.horario = horario;
  }

  public ArrayList<Troca> getTrocas() {
    return trocas;
  }

  public void setTrocas(ArrayList<Troca> trocas) {
    this.trocas = trocas;
  }

  @Override
  public String toString() {
    return "*A*" + getNumero() + " -> " + getNome() + "*** E:" + this.estatuto;
  }

  // **************************************************************************************************************
  // ******************************************* Métodos Adicionais
  // ***********************************************

  public void removeTrocas(Turno origem) {

    for (Iterator<Troca> iterator = this.trocas.iterator(); iterator.hasNext(); ) {
      Troca value = iterator.next();
      if (value.getOrigem().getCodigo().equals(origem.getCodigo())) {
        iterator.remove();
      }
    }
  }

  public void alteraTurno(UnidadeCurricular aThis, Turno destino) {
    this.cadeiras.put(aThis, destino);
  }
}
