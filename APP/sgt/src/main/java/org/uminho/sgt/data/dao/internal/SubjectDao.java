package org.uminho.sgt.data.dao.internal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uminho.sgt.business.aulas.UnidadeCurricular;
import org.uminho.sgt.data.PostgreSql;
import org.uminho.sgt.data.dao.SubjectDaoApi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectDao implements SubjectDaoApi {
  private static final Logger logger = LogManager.getLogger(SubjectDao.class);
  private Connection connection;

  public List<UnidadeCurricular> getSubjects() {
    List<UnidadeCurricular> subjects = new ArrayList<>();

    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT * FROM sgt.get_subjects()";
      PreparedStatement statement = this.connection.prepareStatement(query);
      ResultSet rows = statement.executeQuery();

      while (rows.next()) {
        subjects.add(
            new UnidadeCurricular(
                rows.getString("code"),
                rows.getString("description"),
                rows.getString("abbreviation"),
                rows.getInt("year")));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return subjects;
    }
  }

  public void addSubject(
      final String code,
      final String description,
      final String abbreviation,
      final String course,
      final String degree,
      final int year,
      final String coordinator) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.add_subject(?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, code);
      statement.setString(2, description);
      statement.setString(3, abbreviation);
      statement.setString(4, course);
      statement.setString(5, degree);
      statement.setInt(6, year);
      statement.setString(7, coordinator);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public void deleteSubject(final String code) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.delete_subject(?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, code);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public Optional<String> getCoordinator(final String code) {
    Optional<String> coordinator = Optional.empty();
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.get_coordinator(?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, code);
      statement.execute();
      ResultSet result = statement.getResultSet();
      if (result.next()) {
        coordinator = Optional.ofNullable(result.getString(1));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return coordinator;
    }
  }

  public List<String> getTeachersOf(final String code) {
    List<String> teachers = new ArrayList<>();
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT * FROM sgt.get_teachers_of(?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, code);
      ResultSet rows = statement.executeQuery();

      while (rows.next()) {
        teachers.add(rows.getString("teacher_email"));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return teachers;
    }
  }
  
  public void updateCoordinator(final String code, final String newCoordinator) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.update_coordinator(?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, code);
      statement.setString(2, newCoordinator);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }
}
