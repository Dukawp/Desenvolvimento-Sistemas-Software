package org.uminho.sgt.business.pessoal;

import java.util.Observable;

public abstract class Utilizador extends Observable {

  private int numero;
  private String nome;
  private String mail;
  private String password;

  // **************************************************************************************************************
  // ******************************************* Construtores
  // *****************************************************

  public Utilizador(int numero, String nome, String mail, String password) {
    this.numero = numero;
    this.nome = nome;
    this.mail = mail;
    this.password = password;
  }

  public Utilizador(String nome, String mail){
    this.nome = nome;
    this.mail = mail;
    this.numero = parseMail(mail);
    this.password = "";
  }

  // **************************************************************************************************************
  // ******************************************* Get's e Set's
  // ****************************************************

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

    private int parseMail(String mail) {
        String parts[] = mail.split("@");
        int numero = Integer.parseInt(parts[0]);
        return numero;
    }
}
