package org.uminho.sgt.presentation;

import org.uminho.sgt.business.SGT;
import org.uminho.sgt.business.aulas.UnidadeCurricular;
import org.uminho.sgt.business.pessoal.Professor;

import javax.swing.*;
import java.util.HashMap;

public class JEditarUC extends javax.swing.JFrame {

  private SGT sgt;
  private HashMap<String, UnidadeCurricular> listaUcs;
  private HashMap<Integer, Professor> listaProfs;
  // Variables declaration - do not modify
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton4;
  private javax.swing.JComboBox<UnidadeCurricular> jComboBox1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JList<Professor> jList1;
  private javax.swing.JScrollPane jScrollPane1;

  public JEditarUC(
      SGT sgt,
      HashMap<String, UnidadeCurricular> listaUcs,
      HashMap<Integer, Professor> listaProfs) {
    this.sgt = sgt;
    this.listaUcs = listaUcs;
    this.listaProfs = listaProfs;
    initComponents();

    jLabel4.setText("");
    jList1.setEnabled(false);

    jButton2.setEnabled(false);

    jButton4.setEnabled(false);

    jComboBox1.addItem(null);
    for (String s : this.listaUcs.keySet()) {
      jComboBox1.addItem(this.listaUcs.get(s));
    }
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jComboBox1 = new javax.swing.JComboBox<>();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jList1 = new javax.swing.JList<>();
    jButton2 = new javax.swing.JButton();
    jButton4 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jLabel1.setText("Menu Unidade Curricular");

    jLabel2.setText("Escolha a UC:");

    jComboBox1.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jComboBox1ActionPerformed(evt);
          }
        });

    jLabel3.setText("Regente:");

    jLabel4.setText("prof");

    jList1.addMouseListener(
        new java.awt.event.MouseAdapter() {
          public void mouseClicked(java.awt.event.MouseEvent evt) {
            jList1MouseClicked(evt);
          }
        });
    jScrollPane1.setViewportView(jList1);

    jButton2.setText("Regente");
    jButton2.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
          }
        });

    jButton4.setText("Editar Turno");
    jButton4.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton4ActionPerformed(evt);
          }
        });

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
                                    .addGap(13, 13, 13)
                                    .addGroup(
                                        layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(
                                                layout
                                                    .createSequentialGroup()
                                                    .addComponent(
                                                        jScrollPane1,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        415,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18))
                                            .addGroup(
                                                layout
                                                    .createSequentialGroup()
                                                    .addComponent(jLabel2)
                                                    .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement
                                                            .RELATED)
                                                    .addComponent(
                                                        jComboBox1,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        100,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement
                                                            .RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE)
                                                    .addComponent(jLabel3)
                                                    .addPreferredGap(
                                                        javax.swing.LayoutStyle.ComponentPlacement
                                                            .RELATED)
                                                    .addComponent(jLabel4)
                                                    .addGap(36, 36, 36)))
                                    .addComponent(jButton2))
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(187, 187, 187)
                                    .addComponent(jLabel1))
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(156, 156, 156)
                                    .addComponent(
                                        jButton4,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        132,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(51, Short.MAX_VALUE)));
    layout.setVerticalGroup(
        layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                layout
                    .createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(24, 24, 24)
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(
                                jComboBox1,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(
                                        jScrollPane1,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        183,
                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(101, 101, 101)
                                    .addComponent(jButton2)))
                    .addGap(30, 30, 30)
                    .addComponent(jButton4)
                    .addContainerGap(50, Short.MAX_VALUE)));

    pack();
  } // </editor-fold>

  private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    UnidadeCurricular u = (UnidadeCurricular) jComboBox1.getSelectedItem();
    if (u == null) {
      jLabel4.setText("");
      jList1.setEnabled(false);

      jButton2.setEnabled(false);

      jButton4.setEnabled(false);
      clearList();
    } else {
      jLabel4.setText(u.getRegente().getNome());
      jList1.setEnabled(true);
      jButton4.setEnabled(true);

      updateList();
      for (Integer e : listaProfs.keySet()) {
        int cont = 0;

        for (Professor p : u.getProfessores()) {
          if (p.getNumero() != e) {
            cont++;
          }
        }

        if (cont == u.getProfessores().size()) {}
      }
    }
  }

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    UnidadeCurricular u = (UnidadeCurricular) jComboBox1.getSelectedItem();
    Professor p = (Professor) jList1.getSelectedValue();
    this.sgt.setRegente(u, p);
    jLabel4.setText(p.getNome());
  }

  private void jList1MouseClicked(java.awt.event.MouseEvent evt) {

    jButton2.setEnabled(true);
  }

  private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
    UnidadeCurricular u = (UnidadeCurricular) jComboBox1.getSelectedItem();
    JEditarTurno novaJanela = new JEditarTurno(this.sgt, u);
    novaJanela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    novaJanela.setResizable(false);
    novaJanela.setVisible(true);
  }
  // End of variables declaration

  private void updateList() {

    DefaultListModel<Professor> lista = new DefaultListModel<Professor>();
    UnidadeCurricular u = (UnidadeCurricular) jComboBox1.getSelectedItem();

    for (Professor p : u.getProfessores()) {
      lista.addElement(p);
    }

    jList1.setModel(lista);
  }

  private void clearList() {
    DefaultListModel model = new DefaultListModel();
    model.clear();
    jList1.setModel(model);
  }
}
