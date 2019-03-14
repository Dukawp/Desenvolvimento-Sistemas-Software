package org.uminho.sgt.data.dao.internal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uminho.sgt.business.pessoal.Aluno;
import org.uminho.sgt.business.pessoal.Professor;
import org.uminho.sgt.data.PostgreSql;
import org.uminho.sgt.data.dao.UserDaoApi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements UserDaoApi {
  private static final Logger logger = LogManager.getLogger(UserDao.class);
  private Connection connection;

  public List<Aluno> getStudents() {
    List<Aluno> students = new ArrayList<>();

    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT * FROM sgt.get_students()";
      PreparedStatement statement = this.connection.prepareStatement(query);
      ResultSet rows = statement.executeQuery();

      while (rows.next()) {
        String role = rows.getString("role");
        boolean worker = role.equals("student-worker");
        students.add(new Aluno(worker, rows.getString("name"), rows.getString("email")));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return students;
    }
  }

  public void addStudent(
      final String email, final String name, final String password, boolean worker) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.add_student(?, ?, ?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, email);
      statement.setString(2, name);
      statement.setString(3, password);
      statement.setBoolean(4, worker);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public void deleteStudent(final String email) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.delete_student(?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, email);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public List<Professor> getTeachers() {
    List<Professor> teachers = new ArrayList<>();

    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT * FROM sgt.get_teachers()";
      PreparedStatement statement = this.connection.prepareStatement(query);
      ResultSet rows = statement.executeQuery();

      while (rows.next()) {
        String role = rows.getString("role");
        boolean isDc = role.equals("head-teacher");
        teachers.add(new Professor(isDc, rows.getString("name"), rows.getString("email")));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return teachers;
    }
  }

  public void addTeacher(
      final String email, final String name, final String password, boolean director) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.add_teacher(?, ?, ?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, email);
      statement.setString(2, name);
      statement.setString(3, password);
      statement.setBoolean(4, director);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public void deleteTeacher(final String email) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.delete_teacher(?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, email);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public boolean verifyLogin(final String email, final String password) {
    boolean valid = false;
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.verify_login(?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, email);
      statement.setString(2, password);
      statement.execute();
      ResultSet result = statement.getResultSet();
      if (result.next()) {
        valid = result.getBoolean(1);
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return valid;
    }
  }

  public Optional<String> getRole(final String email) {
    Optional<String> role = Optional.empty();
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.get_role(?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, email);
      statement.execute();
      ResultSet result = statement.getResultSet();
      if (result.next()) {
        role = Optional.ofNullable(result.getString(1));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return role;
    }
  }

  public void markAbsence(final String email, final String subject) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.mark_absence(?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, email);
      statement.setString(2, subject);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public int getAbsences(final String email, final String subject) {
    int absences = -1;
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.get_absences(?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, email);
      statement.setString(2, subject);
      statement.execute();
      ResultSet result = statement.getResultSet();
      if (result.next()) {
        absences = result.getInt(1);
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return absences;
    }
  }

  public List<String> getStudentSubjects(final String email) {
    List<String> subjects = new ArrayList<>();
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT * FROM sgt.get_enrolled_subjects(?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, email);
      ResultSet rows = statement.executeQuery();

      while (rows.next()) {
        subjects.add(rows.getString("subject_code"));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return subjects;
    }
  }

  public Optional<String> getStudentShift(final String email, final String subject) {
    Optional<String> student_shift = Optional.empty();
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.get_current_shift(?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, email);
      statement.setString(2, subject);
      statement.execute();
      ResultSet result = statement.getResultSet();
      if (result.next()) {
        student_shift = Optional.ofNullable(result.getString(1));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return student_shift;
    }
  }
}
