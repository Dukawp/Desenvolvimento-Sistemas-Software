package org.uminho.sgt.business.aulas;

import org.uminho.sgt.business.pessoal.Aluno;
import org.uminho.sgt.business.pessoal.Professor;

import java.util.HashMap;

public class Turno {

  private String hora;
  private String diaSemana;
  private String codigo;
  private String tipoAula;
  private int aulasPrevistas;
  private int capacidadeMaxima;
  private String sala;

  private Professor profALecionar;
  private HashMap<Aluno, Integer> faltas;

  // **************************************************************************************************************
  // ******************************************* Construtores
  // *****************************************************

  public Turno(String hora,String diaSemana,String codigo,String tipoAula,int aulasPrevistas,int capacidadeMaxima,String sala) {
    this.hora = hora;
    this.diaSemana = diaSemana;
    this.codigo = codigo;
    this.tipoAula = tipoAula;
    this.aulasPrevistas = aulasPrevistas;
    this.capacidadeMaxima = capacidadeMaxima;
    this.sala = sala;

    this.profALecionar = null;
    this.faltas = new HashMap<Aluno, Integer>();
  }

  public Turno(
      String hora,
      String diaSemana,
      String codigo,
      String tipoAula,
      int aulasPrevistas,
      int capacidadeMaxima,
      String sala,
      Professor profALecionar,
      HashMap<Aluno, Integer> faltas) {
    this.hora = hora;
    this.diaSemana = diaSemana;
    this.codigo = codigo;
    this.tipoAula = tipoAula;
    this.aulasPrevistas = aulasPrevistas;
    this.capacidadeMaxima = capacidadeMaxima;
    this.sala = sala;
    this.profALecionar = profALecionar;
    this.faltas = faltas;
  }

  // **************************************************************************************************************
  // ******************************************* Get's e Set's
  // ****************************************************
  public String getHora() {
    return this.hora;
  }

  public void setHora(String hora) {
    this.hora = hora;
  }

  public String getCodigo() {
    return this.codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public HashMap<Aluno, Integer> getFaltas() {
    return this.faltas;
  }

  public void setFaltas(HashMap<Aluno, Integer> faltas) {
    this.faltas = faltas;
  }

  public int getCapacidadeMaxima() {
    return this.capacidadeMaxima;
  }

  public void setCapacidadeMaxima(int capacidadeMaxima) {
    this.capacidadeMaxima = capacidadeMaxima;
  }

  public int getAulasPrevistas() {
    return this.aulasPrevistas;
  }

  public void setAulasPrevistas(int aulasPrevistas) {
    this.aulasPrevistas = aulasPrevistas;
  }

  public String getSala() {
    return this.sala;
  }

  public void setSala(String sala) {
    this.sala = sala;
  }

  public String getDiaSemana() {
    return this.diaSemana;
  }

  public void setDiaSemana(String diaSemana) {
    this.diaSemana = diaSemana;
  }

  public String getTipoAula() {
    return this.tipoAula;
  }

  public void setTipoAula(String tipoAula) {
    this.tipoAula = tipoAula;
  }

  public Professor getProfALecionar() {
    return this.profALecionar;
  }

  public void setProfALecionar(Professor profALecionar) {
    this.profALecionar = profALecionar;
  }

  @Override
  public String toString() {
    return this.tipoAula;
  }

  // **************************************************************************************************************
  // ******************************************* Métodos Adicionais
  // ***********************************************

  public boolean marcaFalta(Aluno a) {
    for (Aluno m : faltas.keySet()) {
      if (m.getNumero() == a.getNumero()) {
        int f = faltas.get(m);
        f++;
        faltas.put(m, f);
        if (f == (0.25 * aulasPrevistas)) {
          return true;
        } else {
          return false;
        }
      }
    }
    return false;
  }

  public void removeAluno(Aluno a) {
    faltas.remove((a));
  }

  public void adicionaAluno(Aluno a) {
    faltas.put(a, 0);
  }

  Aluno getAluno(int numOrigem) {
    for (Aluno a : this.faltas.keySet()) {
      if (a.getNumero() == numOrigem) {
        return a;
      }
    }
    return null;
  }

}
