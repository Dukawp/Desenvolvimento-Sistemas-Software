package org.uminho.sgt.presentation;

import org.uminho.sgt.business.SGT;
import org.uminho.sgt.business.aulas.Turno;
import org.uminho.sgt.business.aulas.UnidadeCurricular;
import org.uminho.sgt.business.pessoal.Professor;

import javax.swing.*;

public class JEditarTurno extends javax.swing.JFrame {

  private SGT sgt;
  private UnidadeCurricular uc;
  // Variables declaration - do not modify
  private javax.swing.JButton jButton1;
  private javax.swing.JComboBox<Turno> jComboBox1;
  private javax.swing.JComboBox<Professor> jComboBox2;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JList<String> jList2;
  private javax.swing.JScrollPane jScrollPane2;

  public JEditarTurno(SGT sgt, UnidadeCurricular u) {
    this.sgt = sgt;
    this.uc = u;
    initComponents();

    jLabel3.setText(this.uc.getNome());
    jComboBox2.setEnabled(false);
    jButton1.setEnabled(false);

    jComboBox1.addItem(null);
    for (Turno t : this.uc.getTurnos()) {
      jComboBox1.addItem(t);
    }

    jComboBox2.addItem(null);
    for (Professor p : uc.getProfessores()) {
      jComboBox2.addItem(p);
    }
    updateList();
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jComboBox1 = new javax.swing.JComboBox<>();
    jLabel5 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jComboBox2 = new javax.swing.JComboBox<>();
    jButton1 = new javax.swing.JButton();
    jScrollPane2 = new javax.swing.JScrollPane();
    jList2 = new javax.swing.JList<>();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jLabel1.setText("Menu Turno");

    jLabel2.setText("Unidade Curricular :");

    jLabel3.setText("uc");

    jLabel4.setText("Turno: ");

    jComboBox1.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox1ActionPerformed(evt);
          }
        });

    jLabel5.setText("Trocar Professor a Lecionar Turno:");

    jLabel6.setText("Professor:");

    jComboBox2.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox2ActionPerformed(evt);
          }
        });

    jButton1.setText("Trocar");
    jButton1.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
          }
        });

    jScrollPane2.setViewportView(jList2);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                layout
                    .createSequentialGroup()
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel3))
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(
                                        jScrollPane2,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        318,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(
                                        layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(
                                                layout
                                                    .createSequentialGroup()
                                                    .addGap(20, 20, 20)
                                                    .addGroup(
                                                        layout
                                                            .createParallelGroup(
                                                                javax.swing.GroupLayout.Alignment
                                                                    .LEADING)
                                                            .addComponent(jLabel5)
                                                            .addGroup(
                                                                layout
                                                                    .createParallelGroup(
                                                                        javax.swing.GroupLayout
                                                                            .Alignment.TRAILING)
                                                                    .addGroup(
                                                                        layout
                                                                            .createSequentialGroup()
                                                                            .addComponent(jLabel4)
                                                                            .addPreferredGap(
                                                                                javax.swing
                                                                                    .LayoutStyle
                                                                                    .ComponentPlacement
                                                                                    .RELATED)
                                                                            .addComponent(
                                                                                jComboBox1,
                                                                                javax.swing
                                                                                    .GroupLayout
                                                                                    .PREFERRED_SIZE,
                                                                                100,
                                                                                javax.swing
                                                                                    .GroupLayout
                                                                                    .PREFERRED_SIZE))
                                                                    .addGroup(
                                                                        javax.swing.GroupLayout
                                                                            .Alignment.LEADING,
                                                                        layout
                                                                            .createSequentialGroup()
                                                                            .addComponent(jLabel6)
                                                                            .addPreferredGap(
                                                                                javax.swing
                                                                                    .LayoutStyle
                                                                                    .ComponentPlacement
                                                                                    .RELATED)
                                                                            .addComponent(
                                                                                jComboBox2,
                                                                                javax.swing
                                                                                    .GroupLayout
                                                                                    .PREFERRED_SIZE,
                                                                                100,
                                                                                javax.swing
                                                                                    .GroupLayout
                                                                                    .PREFERRED_SIZE)))))
                                            .addGroup(
                                                layout
                                                    .createSequentialGroup()
                                                    .addGap(53, 53, 53)
                                                    .addComponent(jButton1))))
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(232, 232, 232)
                                    .addComponent(jLabel1)))
                    .addContainerGap(19, Short.MAX_VALUE)));
    layout.setVerticalGroup(
        layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                layout
                    .createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                    .addGap(20, 20, 20)
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addGroup(
                                        layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(
                                                jComboBox1,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(
                                        layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(
                                                jComboBox2,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(27, 27, 27)
                                    .addComponent(jButton1))
                            .addComponent(
                                jScrollPane2,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                280,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(26, Short.MAX_VALUE)));

    pack();
  } // </editor-fold>

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    Turno t = (Turno) jComboBox1.getSelectedItem();
    Professor p = (Professor) jComboBox2.getSelectedItem();
    this.sgt.mudarProfALecionarTurno(uc, t, p);
    updateList();
    jComboBox2.setEnabled(false);
    jComboBox2.setSelectedIndex(0);
    jComboBox1.setSelectedIndex(0);
  }

  private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    Turno t = (Turno) jComboBox1.getSelectedItem();
    if (t == null) {
      jComboBox2.setEnabled(false);
      jButton1.setEnabled(false);
    } else {
      jComboBox2.setEnabled(true);
    }
  }

  private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {
    Professor p = (Professor) jComboBox2.getSelectedItem();
    if (p == null) {
      jButton1.setEnabled(false);
    } else {
      jButton1.setEnabled(true);
    }
  }
  // End of variables declaration

  private void updateList() {
    DefaultListModel<String> lista = new DefaultListModel<String>();
    for (Turno t : uc.getTurnos()) {
      String item;
      Professor p = t.getProfALecionar();
      item = t.getTipoAula() + " ->  " + p.getNome();
      lista.addElement(item);
    }

    jList2.setModel(lista);
  }
}
