package org.uminho.sgt.business;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.uminho.sgt.business.aulas.Turno;
import org.uminho.sgt.business.aulas.UnidadeCurricular;
import org.uminho.sgt.business.pessoal.Aluno;
import org.uminho.sgt.business.pessoal.Professor;
import org.uminho.sgt.business.pessoal.Utilizador;
import org.uminho.sgt.business.trocas.PedidoTroca;
import org.uminho.sgt.business.trocas.Troca;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.uminho.sgt.Start;
import org.uminho.sgt.business.trocas.TrocaNormal;
import org.uminho.sgt.data.dao.internal.ShiftDao;
import org.uminho.sgt.data.dao.internal.SubjectDao;
import org.uminho.sgt.data.dao.internal.TradeDao;
import org.uminho.sgt.data.dao.internal.UserDao;

public class SGT {
    
  private static final Logger logger = LogManager.getLogger(Start.class);

  private Utilizador online;

  private HashMap<String, UnidadeCurricular> listaUCs;
  private HashMap<Integer, Professor> listaProfs;
  private HashMap<Integer, Aluno> listaAlunos;
  private ArrayList<TrocaNormal> trocasNormais;
  private ArrayList<PedidoTroca> pedidoTrocas;

  // *******************************************************************************************************************
  // *********************************** Construtores e Getter's e Setter's
  // ********************************************

  public SGT() {
    this.online = null;
    this.listaUCs = new HashMap<String, UnidadeCurricular>();
    this.listaProfs = new HashMap<Integer, Professor>();
    this.listaAlunos = new HashMap<Integer, Aluno>();
    this.trocasNormais = new ArrayList<TrocaNormal>();
    this.pedidoTrocas = new ArrayList<PedidoTroca>();
    
    loadListaAlunos();
    loadListaProfs();
    loadListaUcs();
    carregarCadeiras();
    carregarTrocasNormais();
    carregarPedidosTrocas();
    carregarAlunosTrocas();
    carregarProfs();
    
    
  }

  public SGT(HashMap<String, UnidadeCurricular> listaUCs,HashMap<Integer, Professor> listaProfs,HashMap<Integer, Aluno> listaAlunos) {
    this.listaUCs = listaUCs;
    this.listaProfs = listaProfs;
    this.listaAlunos = listaAlunos;
  }

  public Utilizador getOnline() {
    return this.online;
  }

  public void setOnline(Utilizador online) {
    this.online = online;
  }

  public HashMap<String, UnidadeCurricular> getListaUCs() {
    return this.listaUCs;
  }

  public void setListaUCs(HashMap<String, UnidadeCurricular> listaUCs) {
    this.listaUCs = listaUCs;
  }

  public HashMap<Integer, Professor> getListaProfs() {
    return this.listaProfs;
  }

  public void setListaProfs(HashMap<Integer, Professor> listaProfs) {
    this.listaProfs = listaProfs;
  }

  public HashMap<Integer, Aluno> getListaAlunos() {
    return this.listaAlunos;
  }

  public void setListaUtilizadores(HashMap<Integer, Aluno> listaAlunos) {
    this.listaAlunos = listaAlunos;
  }

  // *******************************************************************************************************************
  // ******************************************** LOGIN E LOGOUT
  // *******************************************************

  public Utilizador logIn(String numero, String password) {   
    boolean bol; 
    String mail = numero+"@uminho.pt";
    
    UserDao user = new UserDao();
    bol = user.verifyLogin(mail,password);
    
    if(bol){
        for (Integer i : this.listaProfs.keySet()) {
            if (i == Integer.parseInt(numero)) {
                Professor u = this.listaProfs.get(i);
                return u;
            }
        }

    
        for (Integer i : this.listaAlunos.keySet()) {
            if (i == Integer.parseInt(numero)) {
                Aluno u = this.listaAlunos.get(i);
                return u;
            }
        }
    }

    return null;
  }

 
  // *******************************************************************************************************************
  // ******************************************** Utilizador == Professor
  // **********************************************

  public void moveAluno(UnidadeCurricular u, Turno origem, Turno destino, Aluno a) {
    u.moveAluno(origem, destino, a);
    ShiftDao shift = new ShiftDao();
    shift.unenroll(a.getMail(), u.getCodigo());
    shift.enroll(a.getMail(), destino.getTipoAula(), u.getCodigo());
    
  }

  public void marcaFalta(UnidadeCurricular u, Turno t, Aluno a) {
    u.marcaFalta(t, a);
    UserDao user = new UserDao();
    user.markAbsence(a.getMail(), u.getCodigo());
    
  }

  public void setRegente(UnidadeCurricular u, Professor p) {
    u.setRegente(p);
    SubjectDao subject = new SubjectDao();
    subject.updateCoordinator(u.getCodigo(), p.getMail());
  }

  public void mudarProfALecionarTurno(UnidadeCurricular uc, Turno t, Professor p) {
    uc.mudarProfALecionarTurno(t, p);
    ShiftDao shift = new ShiftDao();
    shift.changeShiftTeacher(t.getTipoAula(), uc.getCodigo(), p.getMail());
  }

  public void gerarTurno() {}

  // *******************************************************************************************************************
  // ******************************************** Utilizador == Aluno
  // **************************************************

  public void aceitarTroca(Aluno aluno, Troca troca) {
    UnidadeCurricular u = troca.getUc();
    if (troca instanceof PedidoTroca) {
      PedidoTroca pt = (PedidoTroca) troca;
      Aluno origem = this.listaAlunos.get(pt.getNumOrigem());
      u.aceitarPedidoTroca(aluno, troca, origem);
      
      TradeDao trade = new TradeDao();
      trade.deleteTradeByRequest(origem.getMail(), troca.getUc().getCodigo());
      trade.deleteDirectTrade(origem.getMail(), troca.getUc().getCodigo());
      trade.deleteDirectTrade(aluno.getMail(), troca.getUc().getCodigo());
      
      
      String sOrigem = troca.getOrigem().getTipoAula();
      String sDestino = troca.getDestino().getTipoAula();
      
      ShiftDao shift = new ShiftDao();
        
        
      shift.unenroll(origem.getMail(), troca.getUc().getCodigo());
      shift.enroll(origem.getMail(), sDestino, troca.getUc().getCodigo());
      shift.unenroll(aluno.getMail(), troca.getUc().getCodigo());
      shift.enroll(aluno.getMail(), sOrigem, troca.getUc().getCodigo());
      
      
    }
  }

  public void rejeitarTroca(Aluno aluno, Troca troca) {
    UnidadeCurricular u = troca.getUc();
    if (troca instanceof PedidoTroca) {
      PedidoTroca pt = (PedidoTroca) troca;
      Aluno origem = this.listaAlunos.get(pt.getNumOrigem());
      u.rejeitarPedidoTroca(aluno, troca, origem);
      
      TradeDao trade = new TradeDao();
      trade.deleteTradeByRequest(origem.getMail(), troca.getUc().getCodigo());
      trade.deleteTradeByRequest(aluno.getMail(),u.getCodigo());
      
    }
  }

  public void cancelarTroca(Aluno aluno, Troca troca) {
    UnidadeCurricular u = troca.getUc();
    if (troca instanceof PedidoTroca) {
      PedidoTroca pt = (PedidoTroca) troca;
      Aluno destino = this.listaAlunos.get(pt.getNumDestino());
      u.cancelarPedidoTroca(aluno, troca, destino);
      
      TradeDao trade = new TradeDao();
      trade.deleteTradeByRequest(aluno.getMail(), troca.getUc().getCodigo());
      trade.deleteTradeByRequest(destino.getMail(),u.getCodigo());
      
    } else {
      u.cancelarInscricaoTroca(aluno, troca);
      TradeDao trade = new TradeDao();
      trade.deleteDirectTrade(aluno.getMail(), troca.getUc().getCodigo());
      
    }
  }

  public boolean inscreverListaTrocas(UnidadeCurricular u, Turno origem, Turno destino, Aluno aluno) {
    Aluno a = u.inscreverListaTrocas(origem, destino, aluno);

    if(a!=null){
        TradeDao trade = new TradeDao();
        trade.addDirectTrade(aluno.getMail(), destino.getTipoAula(), u.getCodigo());
        
        ShiftDao shift = new ShiftDao();
        
        shift.unenroll(aluno.getMail(), u.getCodigo());
        shift.enroll(aluno.getMail(), destino.getTipoAula(), u.getCodigo());
        shift.unenroll(a.getMail(), u.getCodigo());
        shift.enroll(a.getMail(), origem.getTipoAula(), u.getCodigo());
        
        trade.deleteDirectTrade(aluno.getMail(), u.getCodigo());
        trade.deleteDirectTrade(a.getMail(), u.getCodigo());
        trade.deleteTradeByRequest(aluno.getMail(), u.getCodigo());
        trade.deleteTradeByRequest(a.getMail(), u.getCodigo());
        return true;
    }else{
        TradeDao trade = new TradeDao();
        trade.addDirectTrade(aluno.getMail(), destino.getTipoAula(), u.getCodigo());
        
    }
    return false;
  }

  public void enviarPedidoTroca(UnidadeCurricular u, Aluno aluno, Turno origem, Turno destino, Aluno enviar) {
    u.enviarPedidoTroca(aluno, origem, destino, enviar);
    
    TradeDao trade = new TradeDao();
    trade.addTradeByRequest(aluno.getMail(), enviar.getMail(), u.getCodigo());
    
  }
  
  //**************************************************************************************************************
  
  private void loadListaAlunos(){
      UserDao user = new UserDao();
      ArrayList<Aluno> lista = (ArrayList<Aluno>) user.getStudents();
      for(Aluno a : lista){
          this.listaAlunos.put(a.getNumero(),a);
      }
  }
  
  private void loadListaProfs(){
      UserDao user = new UserDao();
      ArrayList<Professor> lista = (ArrayList<Professor>) user.getTeachers();
      for(Professor p : lista){
          this.listaProfs.put(p.getNumero(),p);
      }
  }
  
  private void loadListaUcs(){
      SubjectDao subject = new SubjectDao();
      ArrayList<UnidadeCurricular> lista = (ArrayList<UnidadeCurricular>) subject.getSubjects();
      
      for(UnidadeCurricular u : lista){
          Optional<String> coordenador = subject.getCoordinator(u.getCodigo());
          String s = coordenador.orElse("0");
          int number = parseMail(s);
          u.setRegente(this.listaProfs.get(number));
          
          loadProfsUcs(u);
          loadTurnos(u); 
          loadTrocas(u);
          
          this.listaUCs.put(u.getCodigo(),u);
      }
  }
  
  private int parseMail(String mail) {
        String parts[] = mail.split("@");
        int numero = Integer.parseInt(parts[0]);
        return numero;
    }

  private void loadTurnos(UnidadeCurricular u) {
        ShiftDao shift = new ShiftDao();
        List<Turno> lista = shift.getShiftsOf(u.getCodigo());
        for(Turno t : lista ){
            Optional<String> prof = shift.getShiftTeacher(t.getCodigo().split("-")[0], u.getCodigo());
            String numero = prof.orElse("0");
            int num = parseMail(numero);
            Professor p = this.listaProfs.get(num);
            t.setProfALecionar(p);
            loadAluno(u,t); 
        }
        
        u.setTurnos((ArrayList<Turno>) lista);
    }

  private void loadProfsUcs(UnidadeCurricular u) {
        SubjectDao subject = new SubjectDao();
        ArrayList<String> lista = (ArrayList<String>) subject.getTeachersOf(u.getCodigo());
        ArrayList<Professor> profs = new ArrayList<Professor> ();
        
        for(String s : lista){
            int num = parseMail(s);
            Professor p = this.listaProfs.get(num);
            profs.add(p);  
        }
        u.setProfessores(profs);
        
    }

  private void carregarCadeiras(){
       for (Integer e : this.listaAlunos.keySet()){
           Aluno a = this.listaAlunos.get(e);
           carregarCadeirasAluno(a);
       }
    }
    
  private void carregarTrocasNormais(){
        TradeDao trade = new TradeDao();
        this.trocasNormais = (ArrayList<TrocaNormal>) trade.getDirectTrades();
        
        for(TrocaNormal t : this.trocasNormais){
            carregarUcTroca(t);
        }
    }
    
  private void carregarPedidosTrocas(){
        TradeDao trade = new TradeDao();
        this.pedidoTrocas = (ArrayList<PedidoTroca>)  trade.getTradesByRequest();
        
        for(PedidoTroca pt : this.pedidoTrocas){
            carregarUcPedidoTroca(pt);
        }
        
    }
    
  private void carregarAlunosTrocas(){
        for(Integer e : this.listaAlunos.keySet()){
            ArrayList<Troca> nova = new ArrayList<Troca> ();
            Aluno a = this.listaAlunos.get(e);
            
            for(TrocaNormal t : this.trocasNormais){
                if(t.getNumOrigem() == a.getNumero()){
                    nova.add(t);
                }
            }
            
            for(PedidoTroca pt : this.pedidoTrocas){
                if(pt.getNumOrigem() == a.getNumero()){
                    nova.add(pt);
                }else if (pt.getNumDestino() == a.getNumero()){
                    nova.add(pt);
                }
            }
            a.setTrocas(nova);
        }
        
    }
    
  private void carregarProfs(){
        for(Integer e : this.listaProfs.keySet()){
            Professor p = this.listaProfs.get(e);
            carregarCadeirasProf(p);
        }
        
    }

  private void carregarCadeirasAluno(Aluno a) {
        UserDao user = new UserDao();
        
        ArrayList<String> lista = (ArrayList<String>) user.getStudentSubjects(a.getMail());
        
        HashMap<UnidadeCurricular,Turno> cadeiras = new HashMap<UnidadeCurricular,Turno>();
        
        for(String uc : lista){
            for(String s : this.listaUCs.keySet() ){
                if(uc.equals(s)){
                    UnidadeCurricular u = this.listaUCs.get(s);
                    Turno t = getTurnoCadeiraAluno(a.getMail(),u);
                    cadeiras.put(u,t);
                    
                }
            }
            
        }
        a.setCadeiras(cadeiras);
        
    }

  private Turno getTurnoCadeiraAluno(String mail,UnidadeCurricular uc) {
        UserDao user = new UserDao();
        
        Optional<String> turno = user.getStudentShift(mail, uc.getCodigo());
        String op = turno.orElse("nada");
        
        ArrayList<Turno> lista = uc.getTurnos();
        
        for(Turno t: lista ){
            if(t.getTipoAula().equals(op)){
                return t;
            }
        }
        return null;
    }

  private void carregarCadeirasProf(Professor p) {
        ShiftDao shift = new ShiftDao();
        ArrayList<String> lista = (ArrayList<String>) shift.getSubjectsBy(p.getMail());
        
        HashMap<UnidadeCurricular,ArrayList<Turno>> cadeiras = new HashMap<UnidadeCurricular,ArrayList<Turno>>();
        
        for(String s : lista){
            for(String uc : this.listaUCs.keySet()){
                if(uc.equals(s)){
                    UnidadeCurricular u = this.listaUCs.get(uc);
                    ArrayList<Turno> turnos = getTurnoCadeiraProf(p,u);
                    cadeiras.put(u, turnos);
                    
                    
                }
            }
        }
        p.setCadeiras(cadeiras);
    }

  private ArrayList<Turno> getTurnoCadeiraProf(Professor p, UnidadeCurricular u) {
        ShiftDao shift = new ShiftDao();
        ArrayList<Turno> nova = new ArrayList<Turno>();
        
        List<String> lista = shift.getShiftsBy(p.getMail(),u.getCodigo());
        ArrayList<Turno> turnos = u.getTurnos();
        
        for(String s : lista){
            for(Turno t: turnos){
                if(t.getTipoAula().equals(s)){
                    nova.add(t);
                }
            }
        }
        
       return nova; 
        
    }

  private void carregarUcTroca(TrocaNormal t) {
        
       Aluno a = this.listaAlunos.get(t.getNumOrigem());
       UnidadeCurricular u = this.listaUCs.get(t.getUcString());
       Turno origem = a.getCadeiras().get(u);
    
       t.setUc(u);
       t.setOrigem(origem);
     
       ArrayList<Turno> lista = u.getTurnos();
       
       for(Turno destino : lista){
           if(destino.getTipoAula().equals((t.getDestinoString()))){
               t.setDestino(destino);
           }
       }
       
       
    }

  private void carregarUcPedidoTroca(PedidoTroca pt) {
        Aluno origem = this.listaAlunos.get(pt.getNumOrigem());
        Aluno destino = this.listaAlunos.get(pt.getNumDestino());
        
        UnidadeCurricular u = this.listaUCs.get(pt.getUcString());
        pt.setUc(u);
        Turno tOrigem = origem.getCadeiras().get(u);
        Turno tDestino = destino.getCadeiras().get(u);
        
        pt.setOrigem(tOrigem);
        pt.setDestino(tDestino);
        
    }

  private void loadTrocas(UnidadeCurricular u) {
        HashMap<Turno, ArrayList<Troca>> novoHashMap = new HashMap<Turno,ArrayList<Troca>>();
        
        ArrayList<Turno> turnos = u.getTurnos();
        
        for(Turno t : turnos){
            ArrayList<Troca> nova = new ArrayList<Troca>();
            for(TrocaNormal tn : this.trocasNormais){
                if(tn.getUcString().equals(u.getCodigo())){
                    if(tn.getOrigem().getCodigo().equals(t.getCodigo())){
                        nova.add(tn);
                    }
                }
            }
            novoHashMap.put(t,nova); 
        }
        
        u.setListaTrocas(novoHashMap);
    }
 
  private void loadAluno(UnidadeCurricular u, Turno t) {
      ShiftDao shift = new ShiftDao();
      UserDao user = new UserDao();
      
      ArrayList<String> lista = (ArrayList<String>) shift.getShiftEnrolledStudents(t.getTipoAula(), u.getCodigo());
      HashMap<Aluno,Integer> novo = new HashMap<Aluno,Integer>();
      
      
      
      for(String s : lista){
          for(Integer e : this.listaAlunos.keySet()){
              Aluno a = this.listaAlunos.get(e);
              if(a.getMail().equals(s)){
                  int faltas = user.getAbsences(a.getMail(), u.getCodigo());
                  novo.put(a, faltas);
              }
          }
      }
      
      t.setFaltas(novo);
      
  }
 
  
  
  
}


