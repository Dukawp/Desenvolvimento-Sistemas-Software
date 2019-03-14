package org.uminho.sgt.business.aulas;

import org.uminho.sgt.business.pessoal.Aluno;
import org.uminho.sgt.business.pessoal.Professor;
import org.uminho.sgt.business.trocas.PedidoTroca;
import org.uminho.sgt.business.trocas.Troca;
import org.uminho.sgt.business.trocas.TrocaNormal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class UnidadeCurricular {

  private String codigo;
  private String nome;
  private String abreviatura;
  private int ano;

  private Professor regente;
  private ArrayList<Professor> professores;
  private ArrayList<Turno> turnos;
  private HashMap<Turno, ArrayList<Troca>> listaTrocas;

  // **************************************************************************************************************
  // ******************************************* Construtores
  // *****************************************************

  public UnidadeCurricular(String codigo, String nome, String abreviatura, int ano) {
    this.codigo = codigo;
    this.nome = nome;
    this.abreviatura = abreviatura;
    this.ano = ano;

    this.regente = null;
    this.professores = new ArrayList<Professor>();
    this.turnos = new ArrayList<Turno>();
    this.listaTrocas = new HashMap<Turno, ArrayList<Troca>>();
  }

  public UnidadeCurricular(String codigo,String nome,String abreviatura,int ano,Professor regente,ArrayList<Professor> professores,ArrayList<Turno> turnos,HashMap<Turno, ArrayList<Troca>> listaTrocas) {
    this.codigo = codigo;
    this.nome = nome;
    this.abreviatura = abreviatura;
    this.ano = ano;
    this.regente = regente;
    this.professores = professores;
    this.turnos = turnos;
    this.listaTrocas = listaTrocas;
  }

  // **************************************************************************************************************
  // ******************************************* Get's e Set's
  // ****************************************************

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public ArrayList<Turno> getTurnos() {
    return turnos;
  }

  public void setTurnos(ArrayList<Turno> turnos) {
    this.turnos = turnos;
  }

  public ArrayList<Professor> getProfessores() {
    return professores;
  }

  public void setProfessores(ArrayList<Professor> professores) {
    this.professores = professores;
  }

  public Professor getRegente() {
    return regente;
  }

  public void setRegente(Professor regente) {
    this.regente = regente;
  }

  public String getAbreviatura() {
    return this.abreviatura;
  }

  public void setAbreviatura(String abreviatura) {
    this.abreviatura = abreviatura;
  }

  public HashMap<Turno, ArrayList<Troca>> getListaTrocas() {
    return listaTrocas;
  }

  public void setListaTrocas(HashMap<Turno, ArrayList<Troca>> listaTrocas) {
    this.listaTrocas = listaTrocas;
  }

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  @Override
  public String toString() {
    return abreviatura;
  }

  // **************************************************************************************************************
  // ******************************************* Métodos Adicionais
  // ***********************************************

  public void removeProf(Professor p) {
    for (Professor prof : this.professores) {
      if (prof.getNumero() == p.getNumero()) {
        this.professores.remove(prof);
        return;
      }
    }
  }

  public void adicionaProf(Professor p) {
    this.professores.add(p);
    p.addUcs(this);
  }

  public void marcaFalta(Turno t, Aluno a) {
    boolean b = t.marcaFalta(a);

    if (b) {

      for (Turno d : turnos) {
        if (d.getTipoAula().equals("SemTurno")) {
          moveAluno(t, d, a);
        }
      }
    }
  }

  public void moveAluno(Turno origem, Turno destino, Aluno a) {
    origem.removeAluno(a);
    removeAlunoTrocas(origem, a);
    a.removeTrocas(origem);
    a.alteraTurno(this, destino);
    destino.adicionaAluno(a);
  }


  public void enviarPedidoTroca(Aluno aluno, Turno origem, Turno destino, Aluno enviar) {
    Troca troca = new PedidoTroca(origem, destino, aluno.getNumero(), this, enviar.getNumero());
    aluno.getTrocas().add(troca);
    enviar.getTrocas().add(troca);
  }

  public void cancelarPedidoTroca(Aluno aluno, Troca troca, Aluno destino) {
    aluno.getTrocas().remove(troca);
    destino.getTrocas().remove(troca);
  }

  public void cancelarInscricaoTroca(Aluno aluno, Troca troca) {
    Turno turno = troca.getOrigem();
    this.listaTrocas.get(turno).remove(troca);
    aluno.getTrocas().remove(troca);
  }

  public void rejeitarPedidoTroca(Aluno aluno, Troca troca, Aluno origem) {
    aluno.getTrocas().remove(troca);
    origem.getTrocas().remove(troca);
    troca.setEstado("REJEITADA");
  }

  public void aceitarPedidoTroca(Aluno aluno, Troca troca, Aluno origem) {

    aluno.getTrocas().remove(troca);
    origem.getTrocas().remove(troca);

    Turno t1 = troca.getOrigem();
    Turno t2 = troca.getDestino();

    moveAluno(t1, t2, origem);
    moveAluno(t2, t1, aluno);
  }

  private void removeAlunoTrocas(Turno origem, Aluno a) {
    for (Turno t : listaTrocas.keySet()) {

      if (t.getCodigo().equals(origem.getCodigo())) {

        ArrayList<Troca> lista = this.listaTrocas.get(t);

        for (Iterator<Troca> iterator = lista.iterator(); iterator.hasNext(); ) {
          Troca value = iterator.next();
          if (value.getNumOrigem() == a.getNumero()) {
            iterator.remove();
          }
        }
      }
    }
  }

  public void mudarProfALecionarTurno(Turno t, Professor p) {
    Professor antigo = t.getProfALecionar();
    t.setProfALecionar(p);
    antigo.removeTurnos(this, t);
    p.adicionaTurnos(this, t);
  }
  
  public Aluno inscreverListaTrocas(Turno origem, Turno destino, Aluno aluno) {
  
    if (listaTrocas.containsKey(destino)) {
      for (Turno t : listaTrocas.keySet()) {
        if (t.getCodigo().equals(destino.getCodigo())) {
          ArrayList<Troca> lista = listaTrocas.get(t);

          for (Troca tr : lista) {
            if (tr.getDestino().getCodigo().equals(origem.getCodigo())) {

              moveAluno(origem, destino, aluno);

              Aluno a = t.getAluno(tr.getNumOrigem());
              moveAluno(destino, origem, a);
              lista.remove(tr);
              return a;

            } 
            else {
              Troca nova = new TrocaNormal(origem, destino, aluno.getNumero(), this);

              for (Turno tu : listaTrocas.keySet()) {
                if (t.getCodigo().equals(origem.getCodigo())) {
                  listaTrocas.get(tu).add(nova);
                  aluno.getTrocas().add(nova);
                  return null;
                }
              }
            }
          }
        }
      }
    } 
    
      if (listaTrocas.containsKey(origem)) {
        ArrayList<Troca> lista = this.listaTrocas.get(origem);
        Troca nova = new TrocaNormal(origem, destino, aluno.getNumero(), this);
        lista.add(nova);
        this.listaTrocas.put(origem, lista);
        aluno.getTrocas().add(nova);
      } 
      else {
        ArrayList<Troca> lista = new ArrayList<Troca>();
        Troca nova = new TrocaNormal(origem, destino, aluno.getNumero(), this);
        lista.add(nova);
        this.listaTrocas.put(origem, lista);
        aluno.getTrocas().add(nova);
      }
    
    return null;
  }
}
