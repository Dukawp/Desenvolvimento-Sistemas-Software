package org.uminho.sgt.presentation;

import org.uminho.sgt.business.SGT;
import org.uminho.sgt.business.aulas.Turno;
import org.uminho.sgt.business.aulas.UnidadeCurricular;
import org.uminho.sgt.business.pessoal.Aluno;
import org.uminho.sgt.business.pessoal.Utilizador;
import org.uminho.sgt.business.trocas.PedidoTroca;
import org.uminho.sgt.business.trocas.Troca;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import org.uminho.sgt.business.trocas.TrocaNormal;

public class JAluno extends javax.swing.JFrame implements Observer {

  private SGT sgt;
  private Aluno aluno;
  private HashMap<UnidadeCurricular, Turno> cadeiras;

  private Troca troca;
  // Variables declaration - do not modify
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton3;
  private javax.swing.JButton jButton4;
  private javax.swing.JButton jButton5;
  private javax.swing.JButton jButton7;
  private javax.swing.JComboBox<UnidadeCurricular> jComboBox1;
  private javax.swing.JComboBox<Turno> jComboBox3;
  private javax.swing.JComboBox<UnidadeCurricular> jComboBox4;
  private javax.swing.JComboBox<Turno> jComboBox5;
  private javax.swing.JComboBox<Aluno> jComboBox6;
  private javax.swing.JComboBox<UnidadeCurricular> jComboBox7;
  private javax.swing.JComboBox<Turno> jComboBox8;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel13;
  private javax.swing.JLabel jLabel14;
  private javax.swing.JLabel jLabel15;
  private javax.swing.JLabel jLabel16;
  private javax.swing.JLabel jLabel17;
  private javax.swing.JLabel jLabel18;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JList<Troca> jList1;
  private javax.swing.JList<String> jList2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;

  public JAluno(SGT sgt, Utilizador utilizador) {
    this.sgt = sgt;
    this.aluno = (Aluno) utilizador;
    this.cadeiras = this.aluno.getCadeiras();
    initComponents();

    jLabel17.setVisible(false);
    jLabel18.setVisible(false);
    aluno.addObserver(this);
    jLabel3.setText(this.aluno.getNome());

    if (this.aluno.isEstatuto()) {
      jComboBox8.setEnabled(false);
      jButton7.setEnabled(false);

      jComboBox7.addItem(null);
      for (UnidadeCurricular u : this.cadeiras.keySet()) {
        jComboBox7.addItem(u);
      }

    } else {
      jLabel6.setText("sem");
      jLabel8.setVisible(false);
      jLabel11.setVisible(false);
      jLabel12.setVisible(false);
      jComboBox7.setVisible(false);
      jComboBox8.setVisible(false);
      jButton7.setVisible(false);
    }

    jButton1.setEnabled(false);
    jButton2.setEnabled(false);
    jButton3.setEnabled(false);
    jButton4.setEnabled(false);
    jButton5.setEnabled(false);

    jComboBox3.setEnabled(false);
    jComboBox5.setEnabled(false);
    jComboBox6.setEnabled(false);

    jComboBox1.addItem(null);
    for (UnidadeCurricular u : this.cadeiras.keySet()) {
      jComboBox1.addItem(u);
    }
    jComboBox4.addItem(null);
    for (UnidadeCurricular u : this.cadeiras.keySet()) {
      jComboBox4.addItem(u);
    }

    updateListTrocas();
    updateListUcs();
  }

  // <editor-fold defaultstate="collapsed" desc="Generated Code">
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jList1 = new javax.swing.JList<>();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    jComboBox1 = new javax.swing.JComboBox<>();
    jComboBox3 = new javax.swing.JComboBox<>();
    jComboBox4 = new javax.swing.JComboBox<>();
    jComboBox5 = new javax.swing.JComboBox<>();
    jComboBox6 = new javax.swing.JComboBox<>();
    jButton4 = new javax.swing.JButton();
    jButton5 = new javax.swing.JButton();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    jComboBox7 = new javax.swing.JComboBox<>();
    jComboBox8 = new javax.swing.JComboBox<>();
    jScrollPane2 = new javax.swing.JScrollPane();
    jList2 = new javax.swing.JList<>();
    jLabel9 = new javax.swing.JLabel();
    jLabel10 = new javax.swing.JLabel();
    jButton7 = new javax.swing.JButton();
    jLabel11 = new javax.swing.JLabel();
    jLabel12 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    jLabel13 = new javax.swing.JLabel();
    jLabel14 = new javax.swing.JLabel();
    jLabel15 = new javax.swing.JLabel();
    jLabel16 = new javax.swing.JLabel();
    jLabel17 = new javax.swing.JLabel();
    jLabel18 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jLabel1.setText("Menu Aluno");

    jLabel2.setText("Bem vindo,");

    jLabel3.setText("aluno");

    jLabel4.setText("As suas notificações:");

    jList1.addMouseListener(
        new java.awt.event.MouseAdapter() {
          public void mouseClicked(java.awt.event.MouseEvent evt) {
            jList1MouseClicked(evt);
          }
        });
    jScrollPane1.setViewportView(jList1);

    jButton1.setText("Aceitar");
    jButton1.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
          }
        });

    jButton2.setText("Rejeitar");
    jButton2.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
          }
        });

    jButton3.setText("Cancelar");
    jButton3.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton3ActionPerformed(evt);
          }
        });

    jComboBox1.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox1ActionPerformed(evt);
          }
        });

    jComboBox3.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox3ActionPerformed(evt);
          }
        });

    jComboBox4.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox4ActionPerformed(evt);
          }
        });

    jComboBox5.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox5ActionPerformed(evt);
          }
        });

    jComboBox6.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox6ActionPerformed(evt);
          }
        });

    jButton4.setText("Inscrever-se Lista Trocas");
    jButton4.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
          }
        });

    jButton5.setText("Pedir Troca");
    jButton5.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton5ActionPerformed(evt);
          }
        });

    jLabel5.setText("*************************************************************** Aluno ");

    jLabel6.setText("com");

    jLabel7.setText(
        "Estatuto ******************************************************************** ");

    jComboBox7.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox7ActionPerformed(evt);
          }
        });

    jComboBox8.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox8ActionPerformed(evt);
          }
        });

    jList2.setModel(
        new javax.swing.AbstractListModel<String>() {
          String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

          public int getSize() {
            return strings.length;
          }

          public String getElementAt(int i) {
            return strings[i];
          }
        });
    jScrollPane2.setViewportView(jList2);

    jLabel9.setText("As suas Uc's:");

    jLabel10.setText("Meu Turno");

    jButton7.setText("Pedir Troca");
    jButton7.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton7ActionPerformed(evt);
          }
        });

    jLabel11.setText("UC:");

    jLabel12.setText("Turno Pretendido:");

    jLabel8.setText("Turno Pretendido:");

    jLabel13.setText("UC:");

    jLabel14.setText("UC:");

    jLabel15.setText("Turno Pretendido:");

    jLabel16.setText("Aluno:");

    jLabel17.setForeground(new java.awt.Color(255, 0, 0));
    jLabel17.setText("Erro : Ja se inscreveu a essa UC");

    jLabel18.setForeground(new java.awt.Color(255, 0, 0));
    jLabel18.setText("Erro: Ja enviou um pedido de troca");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                layout
                    .createSequentialGroup()
                    .addGap(19, 19, 19)
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGroup(
                                        layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(
                                                layout
                                                    .createSequentialGroup()
                                                    .addComponent(jLabel2)
                                                    .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement
                                                            .RELATED)
                                                    .addComponent(jLabel3)
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(
                                                layout
                                                    .createSequentialGroup()
                                                    .addGap(31, 31, 31)
                                                    .addGroup(
                                                        layout
                                                            .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment
                                                                    .TRAILING)
                                                            .addComponent(jLabel13)
                                                            .addComponent(jLabel14))
                                                    .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement
                                                            .RELATED)
                                                    .addGroup(
                                                        layout
                                                            .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment
                                                                    .LEADING,
                                                                false)
                                                            .addComponent(
                                                                jComboBox4, 0, 100, Short.MAX_VALUE)
                                                            .addComponent(
                                                                jComboBox1,
                                                                0,
                                                                javax.swing.GroupLayout
                                                                    .DEFAULT_SIZE,
                                                                Short.MAX_VALUE))
                                                    .addGroup(
                                                        layout
                                                            .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment
                                                                    .TRAILING)
                                                            .addGroup(
                                                                layout
                                                                    .createSequentialGroup()
                                                                    .addPreferredGap(
                                                                        javax.swing.LayoutStyle
                                                                            .ComponentPlacement
                                                                            .RELATED,
                                                                        javax.swing.GroupLayout
                                                                            .DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                    .addComponent(jLabel10)
                                                                    .addGap(93, 93, 93)
                                                                    .addComponent(jLabel8)
                                                                    .addPreferredGap(
                                                                        javax.swing.LayoutStyle
                                                                            .ComponentPlacement
                                                                            .RELATED)
                                                                    .addComponent(
                                                                        jComboBox3,
                                                                        javax.swing.GroupLayout
                                                                            .PREFERRED_SIZE,
                                                                        100,
                                                                        javax.swing.GroupLayout
                                                                            .PREFERRED_SIZE)
                                                                    .addGap(34, 34, 34)
                                                                    .addComponent(jButton4))
                                                            .addGroup(
                                                                layout
                                                                    .createSequentialGroup()
                                                                    .addGap(58, 58, 58)
                                                                    .addComponent(jLabel15)
                                                                    .addPreferredGap(
                                                                        javax.swing.LayoutStyle
                                                                            .ComponentPlacement
                                                                            .RELATED)
                                                                    .addComponent(
                                                                        jComboBox5,
                                                                        javax.swing.GroupLayout
                                                                            .PREFERRED_SIZE,
                                                                        100,
                                                                        javax.swing.GroupLayout
                                                                            .PREFERRED_SIZE)
                                                                    .addPreferredGap(
                                                                        javax.swing.LayoutStyle
                                                                            .ComponentPlacement
                                                                            .RELATED,
                                                                        javax.swing.GroupLayout
                                                                            .DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                    .addComponent(jLabel16)
                                                                    .addPreferredGap(
                                                                        javax.swing.LayoutStyle
                                                                            .ComponentPlacement
                                                                            .RELATED)
                                                                    .addComponent(
                                                                        jComboBox6,
                                                                        javax.swing.GroupLayout
                                                                            .PREFERRED_SIZE,
                                                                        100,
                                                                        javax.swing.GroupLayout
                                                                            .PREFERRED_SIZE)
                                                                    .addGap(89, 89, 89)
                                                                    .addComponent(
                                                                        jButton5,
                                                                        javax.swing.GroupLayout
                                                                            .PREFERRED_SIZE,
                                                                        130,
                                                                        javax.swing.GroupLayout
                                                                            .PREFERRED_SIZE)
                                                                    .addGap(37, 37, 37)))))
                                    .addContainerGap())
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addGroup(
                                        layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(
                                                jScrollPane2,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                223,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9))
                                    .addGap(18, 18, 18)
                                    .addGroup(
                                        layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(
                                                layout
                                                    .createSequentialGroup()
                                                    .addComponent(jLabel4)
                                                    .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(
                                                layout
                                                    .createSequentialGroup()
                                                    .addComponent(jScrollPane1)
                                                    .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement
                                                            .RELATED)
                                                    .addGroup(
                                                        layout
                                                            .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment
                                                                    .LEADING)
                                                            .addComponent(jButton3)
                                                            .addGroup(
                                                                layout
                                                                    .createParallelGroup(
                                                                        javax.swing.GroupLayout
                                                                            .Alignment.TRAILING)
                                                                    .addComponent(
                                                                        jButton2,
                                                                        javax.swing.GroupLayout
                                                                            .PREFERRED_SIZE,
                                                                        98,
                                                                        javax.swing.GroupLayout
                                                                            .PREFERRED_SIZE)
                                                                    .addComponent(
                                                                        jButton1,
                                                                        javax.swing.GroupLayout
                                                                            .PREFERRED_SIZE,
                                                                        98,
                                                                        javax.swing.GroupLayout
                                                                            .PREFERRED_SIZE)))
                                                    .addContainerGap())))))
            .addGroup(
                layout
                    .createSequentialGroup()
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(392, 392, 392)
                                    .addComponent(jLabel1))
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(
                                        layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(
                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                layout
                                                    .createSequentialGroup()
                                                    .addComponent(jLabel11)
                                                    .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement
                                                            .RELATED)
                                                    .addComponent(
                                                        jComboBox7,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        93,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(51, 51, 51)
                                                    .addComponent(jLabel12))
                                            .addGroup(
                                                layout
                                                    .createSequentialGroup()
                                                    .addComponent(jLabel5)
                                                    .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement
                                                            .RELATED)
                                                    .addComponent(jLabel6)))
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(
                                        layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(
                                                layout
                                                    .createSequentialGroup()
                                                    .addComponent(
                                                        jComboBox8,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        100,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement
                                                            .RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)
                                                    .addComponent(
                                                        jButton7,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        131,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(197, 197, 197))
                                            .addComponent(jLabel7))))
                    .addGap(0, 2, Short.MAX_VALUE))
            .addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout
                    .createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel17))
                    .addGap(22, 22, 22)));
    layout.setVerticalGroup(
        layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                layout
                    .createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addGroup(
                                        layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel9))
                                    .addGap(18, 18, 18)
                                    .addGroup(
                                        layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(
                                                jScrollPane1,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                191,
                                                Short.MAX_VALUE)
                                            .addComponent(jScrollPane2)))
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(61, 61, 61)
                                    .addComponent(jButton1)
                                    .addGap(31, 31, 31)
                                    .addComponent(jButton2)
                                    .addGap(33, 33, 33)
                                    .addComponent(jButton3)))
                    .addGap(18, 18, 18)
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addGroup(
                                layout
                                    .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(
                                        jComboBox3,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                            .addGroup(
                                layout
                                    .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(
                                        jComboBox1,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel17)
                    .addGap(27, 27, 27)
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(
                                jComboBox6,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(
                                jComboBox5,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(
                                jComboBox4,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel18)
                    .addPreferredGap(
                        javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                    .addGap(18, 18, 18)
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(
                                jComboBox7,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(
                                jComboBox8,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7))
                    .addGap(50, 50, 50)));

    pack();
  } // </editor-fold>

  private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
    Troca troca = (Troca) jList1.getSelectedValue();
    System.out.println("Aqui");
    this.sgt.cancelarTroca(this.aluno, troca);
    updateListTrocas();
  }

  private void jList1MouseClicked(java.awt.event.MouseEvent evt) {
    this.troca = jList1.getSelectedValue();
    if (troca != null) {
      if (this.troca.getNumOrigem() == this.aluno.getNumero()) {
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(true);

      } else {
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
        jButton3.setEnabled(false);
      }
    }
  }

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    Troca troca = (Troca) jList1.getSelectedValue();
    this.sgt.aceitarTroca(this.aluno, troca);
    updateListTrocas();
    updateListUcs();
    jButton1.setEnabled(false);
    jButton2.setEnabled(false);
  }

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    Troca troca = (Troca) jList1.getSelectedValue();
    this.sgt.rejeitarTroca(this.aluno, troca);
    updateListTrocas();
    jButton1.setEnabled(false);
    jButton2.setEnabled(false);
  }

  private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {
    Turno t = (Turno) jComboBox3.getSelectedItem();
    if (t == null) {
      jButton4.setEnabled(false);
    } else {
      jButton4.setEnabled(true);
    }
  }

  private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {
    UnidadeCurricular u = (UnidadeCurricular) jComboBox4.getSelectedItem();
    if (u == null) {
      jComboBox5.setEnabled(false);
      jComboBox6.setEnabled(false);
      jButton5.setEnabled(false);
    } else {
      jComboBox5.removeAllItems();
      String meuTurno = cadeiras.get(u).getTipoAula();
      jComboBox5.addItem(null);
      for (Turno t : u.getTurnos()) {
        if (!meuTurno.equals(t.getTipoAula())) {
          jComboBox5.addItem(t);
        }
      }
      jComboBox5.setEnabled(true);
    }
  }

  private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    UnidadeCurricular u = (UnidadeCurricular) jComboBox1.getSelectedItem();
    if (u == null) {
      jLabel10.setText("Meu Turno");
      jComboBox3.setEnabled(false);
      jButton4.setEnabled(false);
    } else {
      jComboBox3.removeAllItems();
      String meuTurno = cadeiras.get(u).getTipoAula();
      jLabel10.setText(meuTurno);

      jComboBox3.addItem(null);
      for (Turno t : u.getTurnos()) {
        if (!meuTurno.equals(t.getTipoAula())) {
          jComboBox3.addItem(t);
        }
      }

      jComboBox3.setEnabled(true);
    }
  }

  private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {
    Turno t = (Turno) jComboBox5.getSelectedItem();
    if (t == null) {
      jComboBox6.setEnabled(false);
      jButton5.setEnabled(false);
    } else {
      jComboBox6.removeAllItems();
      HashMap<Aluno, Integer> listaAlunos = t.getFaltas();
      jComboBox6.addItem(null);
      for (Aluno a : listaAlunos.keySet()) {
        jComboBox6.addItem(a);
      }

      jComboBox6.setEnabled(true);
    }
  }

  private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {
    Aluno a = (Aluno) jComboBox6.getSelectedItem();
    if (a == null) {
      jButton5.setEnabled(false);
    } else {
      jButton5.setEnabled(true);
    }
  }

  private void jComboBox7ActionPerformed(java.awt.event.ActionEvent evt) {
    UnidadeCurricular u = (UnidadeCurricular) jComboBox7.getSelectedItem();
    if (u == null) {
      jComboBox8.setEnabled(false);
      jButton7.setEnabled(false);
    } else {
      jComboBox8.removeAllItems();
      String meuTurno = cadeiras.get(u).getTipoAula();
      jComboBox8.addItem(null);
      for (Turno t : u.getTurnos()) {
        if (!meuTurno.equals(t.getTipoAula())) {
          if (t.getFaltas().size() != t.getCapacidadeMaxima()) {
            jComboBox8.addItem(t);
          }
        }
      }
      jComboBox8.setEnabled(true);
    }
  }

  private void jComboBox8ActionPerformed(java.awt.event.ActionEvent evt) {
    Turno t = (Turno) jComboBox8.getSelectedItem();
    if (t == null) {
      jButton7.setEnabled(false);
    } else {
      jButton7.setEnabled(true);
    }
  }

  private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
    UnidadeCurricular u = (UnidadeCurricular) jComboBox1.getSelectedItem();
    Turno destino = (Turno) jComboBox3.getSelectedItem();
    Turno origem = cadeiras.get(u);

    for (Troca t : this.aluno.getTrocas()) {
      if (t.getUc().getNome().equals(u.getNome()) && t instanceof TrocaNormal) {
        jLabel17.setVisible(true);
        jComboBox3.removeAllItems();
        jComboBox3.setEnabled(false);
        jLabel10.setText("Meu Turno");
        jComboBox1.setSelectedIndex(0);
        return;
      }
    }

    boolean b = this.sgt.inscreverListaTrocas(u, origem, destino, this.aluno);
    if (b) {
      updateListUcs();
    } else {
      updateListTrocas();
    }

    jComboBox3.removeAllItems();
    jComboBox3.setEnabled(false);
    jLabel10.setText("Meu Turno");
    jComboBox1.setSelectedIndex(0);
  }

  private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
    UnidadeCurricular u = (UnidadeCurricular) jComboBox4.getSelectedItem();
    Turno destino = (Turno) jComboBox5.getSelectedItem();
    Turno origem = cadeiras.get(u);
    Aluno enviar = (Aluno) jComboBox6.getSelectedItem();

    for (Troca t : this.aluno.getTrocas()) {
      if (t.getUc().getNome().equals(u.getNome())) {
        if (t instanceof PedidoTroca) {
          jLabel18.setVisible(true);
          jComboBox5.removeAllItems();
          jComboBox5.setEnabled(false);
          jComboBox6.removeAllItems();
          jComboBox6.setEnabled(false);
          jComboBox4.setSelectedIndex(0);
          return;
        }
      }
    }

    this.sgt.enviarPedidoTroca(u, this.aluno, origem, destino, enviar);
    updateListTrocas();

    jComboBox5.removeAllItems();
    jComboBox5.setEnabled(false);
    jComboBox6.removeAllItems();
    jComboBox6.setEnabled(false);
    jComboBox4.setSelectedIndex(0);
  }

  private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
    UnidadeCurricular u = (UnidadeCurricular) jComboBox7.getSelectedItem();
    Turno destino = (Turno) jComboBox8.getSelectedItem();
    Turno meuTurno = this.cadeiras.get(u);
    this.sgt.moveAluno(u, meuTurno, destino, this.aluno);
    updateListUcs();
    jComboBox8.removeAllItems();
    jComboBox8.setEnabled(false);
    jComboBox7.setSelectedIndex(0);
  }
  // End of variables declaration

  public void updateListTrocas() {
    DefaultListModel<Troca> lista = new DefaultListModel<Troca>();
    ArrayList<Troca> trocas = this.aluno.getTrocas();
    for (Troca t : trocas) {

      lista.addElement(t);
    }

    jList1.setModel(lista);
  }

  public void updateListUcs() {
    DefaultListModel<String> lista = new DefaultListModel<String>();
    for (UnidadeCurricular u : cadeiras.keySet()) {
      String item;
      Turno t = cadeiras.get(u);
      item = u.getNome() + " -> " + t.getTipoAula();
      lista.addElement(item);
    }

    jList2.setModel(lista);
  }

  @Override
  public void update(Observable o, Object arg) {
    JOptionPane.showMessageDialog(this, "Nova atualização! ");
    updateListUcs();
    updateListTrocas();
  }


}
