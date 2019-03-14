package org.uminho.sgt.data.dao.internal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uminho.sgt.business.aulas.Turno;
import org.uminho.sgt.data.PostgreSql;
import org.uminho.sgt.data.dao.ShiftDaoApi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShiftDao implements ShiftDaoApi {
  private static final Logger logger = LogManager.getLogger(ShiftDao.class);
  private Connection connection;

  public List<Turno> getShifts() {
    List<Turno> shifts = new ArrayList<>();
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT * FROM sgt.get_shifts()";
      PreparedStatement statement = this.connection.prepareStatement(query);
      ResultSet rows = statement.executeQuery();

      while (rows.next()) {
        String hour = new String(rows.getString("start_time") + "-" + rows.getString("end_time"));
        String code = new String(rows.getString("code") + "-" + rows.getString("subject"));
        shifts.add(
            new Turno(
                hour,
                rows.getString("weekday"),
                code,
                rows.getString("code"),
                rows.getInt("scheduled_classes"),
                rows.getInt("capacity"),
                rows.getString("room")));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return shifts;
    }
  }

  public void addShift(
      final String code,
      final String subject,
      final String classType,
      final String room,
      final int capacity,
      final int scheduled_classes,
      final String weekday,
      final String startTime,
      final String endTime,
      final String teacher) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.add_shift(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, code);
      statement.setString(2, subject);
      statement.setString(3, classType);
      statement.setString(4, room);
      statement.setInt(5, capacity);
      statement.setInt(6, scheduled_classes);
      statement.setString(7, weekday);
      statement.setString(8, startTime);
      statement.setString(9, endTime);
      statement.setString(10, teacher);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public void deleteShift(final String code, final String subject) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.delete_shift(?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, code);
      statement.setString(2, subject);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public void enroll(final String student_email, final String code, final String subject) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.enroll(?, ?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, student_email);
      statement.setString(2, code);
      statement.setString(3, subject);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public void unenroll(final String student_email, final String subject) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.unenroll(?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, student_email);
      statement.setString(2, subject);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public void changeShiftTeacher(final String code, final String subject, final String newTeacher) {
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.change_shift_teacher(?, ?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, code);
      statement.setString(2, subject);
      statement.setString(3, newTeacher);
      statement.execute();
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
    }
  }

  public List<Turno> getShiftsOf(final String subject) {
    List<Turno> shifts = new ArrayList<>();
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT * FROM sgt.get_shifts_of(?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, subject);
      ResultSet rows = statement.executeQuery();

       while (rows.next()) {
        String hour = new String(rows.getString("start_time") + "-" + rows.getString("end_time"));
        String code = new String(rows.getString("shift_code") + "-" + rows.getString("shift_subject"));
        shifts.add(
            new Turno(
                hour,
                rows.getString("weekday"),
                code,
                rows.getString("shift_code"),
                rows.getInt("scheduled_classes"),
                rows.getInt("capacity"),
                rows.getString("room")));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return shifts;
    }
  }

  public Optional<String> getShiftTeacher(final String code, final String subject) {
    Optional<String> teacher = Optional.empty();
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT sgt.get_shift_teacher(?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, code);
      statement.setString(2, subject);
      statement.execute();
      ResultSet result = statement.getResultSet();
      if (result.next()) {
        teacher = Optional.ofNullable(result.getString(1));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return teacher;
    }
  }

  public List<String> getSubjectsBy(final String teacher_email) {
    List<String> subjects = new ArrayList<>();
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT * FROM sgt.get_subjects_by(?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, teacher_email);
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

  public List<String> getShiftsBy(final String teacher_email, final String subject) {
    List<String> shifts = new ArrayList<>();

    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT * FROM sgt.get_shifts_by(?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, teacher_email);
      statement.setString(2, subject);
      ResultSet rows = statement.executeQuery();

      while (rows.next()) {
        shifts.add(rows.getString("shift_code"));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return shifts;
    }
  }
  
  public List<String> getShiftEnrolledStudents(final String shift, final String subject) {
    List<String> enrolled_students = new ArrayList<>();
    try {
      this.connection = PostgreSql.connect();
      String query = "SELECT * FROM sgt.get_shift_enrolled_students(?, ?)";
      PreparedStatement statement = this.connection.prepareStatement(query);
      statement.setString(1, shift);
      statement.setString(2, subject);
      ResultSet rows = statement.executeQuery();
      while (rows.next()) {
        enrolled_students.add(rows.getString("student_email"));
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
    } finally {
      PostgreSql.close(this.connection);
      return enrolled_students;
    }
  }
}
