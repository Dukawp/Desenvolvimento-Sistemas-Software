package org.uminho.sgt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uminho.sgt.business.SGT;
import org.uminho.sgt.business.aulas.Turno;
import org.uminho.sgt.business.aulas.UnidadeCurricular;
import org.uminho.sgt.business.pessoal.Aluno;
import org.uminho.sgt.business.pessoal.Professor;
import org.uminho.sgt.data.dao.internal.ShiftDao;
import org.uminho.sgt.data.dao.internal.SubjectDao;
import org.uminho.sgt.data.dao.internal.TradeDao;
import org.uminho.sgt.data.dao.internal.UserDao;
import org.uminho.sgt.presentation.JInicio;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Start {
  private static final Logger logger = LogManager.getLogger(Start.class);

  public static void main(String[] args) {
    SGT sgt = new SGT();
    
    JInicio inicio = new JInicio(sgt);
    inicio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    inicio.setVisible(true);
  }
}
