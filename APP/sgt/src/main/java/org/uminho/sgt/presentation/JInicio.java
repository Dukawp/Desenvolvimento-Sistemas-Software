package org.uminho.sgt.presentation;

import org.uminho.sgt.business.SGT;
import org.uminho.sgt.business.pessoal.Aluno;
import org.uminho.sgt.business.pessoal.Professor;
import org.uminho.sgt.business.pessoal.Utilizador;

import javax.swing.*;

public class JInicio extends javax.swing.JFrame {

  private SGT sgt;
  // Variables declaration - do not modify
  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JPasswordField jPasswordField1;
  private javax.swing.JTextField jTextField1;

  public JInicio(SGT sgt) {
    this.sgt = sgt;
    initComponents();
    jLabel6.setVisible(false);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    jTextField1 = new javax.swing.JTextField();
    jButton1 = new javax.swing.JButton();
    jLabel6 = new javax.swing.JLabel();
    jPasswordField1 = new javax.swing.JPasswordField();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jLabel1.setText("Sistema de Gestão de Turnos");

    jLabel2.setText("Mestrado Integrado em Engenharia Informática");

    jLabel3.setText("Universidade do Minho");

    jLabel4.setText("Número:");

    jLabel5.setText("Password:");

    jButton1.setText("Login");
    jButton1.addActionListener(
        new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
          }
        });

    jLabel6.setForeground(new java.awt.Color(255, 0, 0));
    jLabel6.setText("Erro");
    jLabel6.setEnabled(false);

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
                                    .addGap(235, 235, 235)
                                    .addComponent(jLabel1))
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(177, 177, 177)
                                    .addComponent(jLabel2))
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(247, 247, 247)
                                    .addComponent(jLabel3))
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(141, 141, 141)
                                    .addGroup(
                                        layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5))
                                    .addGap(18, 18, 18)
                                    .addGroup(
                                        layout
                                            .createParallelGroup(
                                                javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(
                                                jTextField1,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                290,
                                                Short.MAX_VALUE)
                                            .addComponent(jPasswordField1)))
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(261, 261, 261)
                                    .addComponent(jButton1))
                            .addGroup(
                                layout
                                    .createSequentialGroup()
                                    .addGap(163, 163, 163)
                                    .addComponent(jLabel6)))
                    .addContainerGap(127, Short.MAX_VALUE)));
    layout.setVerticalGroup(
        layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
                layout
                    .createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel2)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel3)
                    .addGap(54, 54, 54)
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(
                                jTextField1,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(25, 25, 25)
                    .addGroup(
                        layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(
                                jPasswordField1,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(
                        jLabel6,
                        javax.swing.GroupLayout.PREFERRED_SIZE,
                        16,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(8, 8, 8)
                    .addComponent(jButton1)
                    .addContainerGap(19, Short.MAX_VALUE)));

    pack();
  } // </editor-fold>

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    String username = jTextField1.getText();
    String password = jPasswordField1.getText();
    Utilizador utilizador = this.sgt.logIn(username, password);

    if (utilizador != null) {
      if (utilizador instanceof Aluno) {
        JAluno janelaAluno = new JAluno(this.sgt, utilizador);
        janelaAluno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janelaAluno.setResizable(false);
        janelaAluno.setVisible(true);

        jLabel6.setVisible(false);
        jTextField1.setText("");
        jPasswordField1.setText("");
      } else if (utilizador instanceof Professor) {
        JProfessor janelaProf = new JProfessor(this.sgt, utilizador);
        janelaProf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janelaProf.setResizable(false);
        janelaProf.setVisible(true);
        jLabel6.setVisible(false);
        jTextField1.setText("");
        jPasswordField1.setText("");
      }
    } else {

      jTextField1.setText("");
      jPasswordField1.setText("");
      jLabel6.setText("ERRO: Numero ou Password errados, tente outra vez !!!");
      jLabel6.setVisible(true);
    }
  }
  // End of variables declaration
}
