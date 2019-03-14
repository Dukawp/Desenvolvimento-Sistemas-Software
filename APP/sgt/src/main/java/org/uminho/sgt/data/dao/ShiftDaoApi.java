package org.uminho.sgt.data.dao;

import org.uminho.sgt.business.aulas.Turno;

import java.util.List;
import java.util.Optional;

public interface ShiftDaoApi {
  /**
   * Fetch shifts from database.
   *
   * @return List of registered shifts, wrapped into Turno objects.
   */
  List<Turno> getShifts();

  /**
   * Creates a new shift.
   *
   * @param code Shift code.
   * @param subject Subject code (to which this shift will belong). Must exist in database,
   *     otherwise shift creation will fail.
   * @param classType Shift class type (PL, TP).
   * @param room Room where the shift is lectured.
   * @param capacity Maximum shift capacity.
   * @param scheduled_classes Shift planned classes.
   * @param weekday The weekday the shift occurs.
   * @param startTime The start time for provided weekday.
   * @param endTime The end time for provided weekday.
   * @param teacher The teacher responsible to teach the shift. Must exist in database, otherwise
   *     shift creation will fail.
   */
  void addShift(final String code,final String subject,final String classType,final String room,final int capacity,final int scheduled_classes,final String weekday,final String startTime,final String endTime,final String teacher);

  /**
   * Deletes shift, if exists.
   *
   * @param code Shift code.
   * @param subject Subject to which the provided shift belong.
   */
  void deleteShift(final String code, final String subject);

  /**
   * Enrolls a student in a shift, if params are registered in database.
   *
   * @param student_email The student unique identifier, as provided on creation.
   * @param code The shift code to enroll.
   * @param subject The subject code (to which the shift belong).
   */
  void enroll(final String student_email, final String code, final String subject);

  /**
   * Unenrolls a student from a subject shift, if it is currently enrolled.
   *
   * @param student_email The student unique identifier, as provided on creation.
   * @param subject The subject to unenroll.
   */
  void unenroll(final String student_email, final String subject);

  /**
   * Change a shift teacher.
   *
   * @param code The shift code.
   * @param subject The subject code (to which the shift belong).
   * @param newTeacher The new teacher. It must exist, otherwise it will fail.
   */
  void changeShiftTeacher(final String code, final String subject, final String newTeacher);

  /**
   * Gets shifts associated with a provided subject.
   *
   * @param subject The subject code.
   * @return List of shifts wrapped into Turno objects.
   */
  List<Turno> getShiftsOf(final String subject);

  /**
   * Gets current shift teacher.
   *
   * @param code Shift code.
   * @param subject Subject code.
   * @return As optional string, containing either a teacher email or an empty (depending on shift's
   *     existence).
   */
  Optional<String> getShiftTeacher(final String code, final String subject);

  /**
   * Gets list of subject codes lectured by a provided teacher email.
   *
   * @param teacher_email The provided teacher email.
   * @return List of subject codes currently lectured by specified teacher.
   */
  List<String> getSubjectsBy(final String teacher_email);

  /**
   * Gets list of shift codes lectured by a provided teacher email for a specific subject.
   *
   * @param teacher_email The provided teacher email.
   * @param subject The subject to get shifts from.
   * @return List of shift codes lectured by provided teacher email for specified subject.
   */
  List<String> getShiftsBy(final String teacher_email, final String subject);
  
  
/**
   * Gets a list of currently enrolled students for a specific shift/subject.
   * @param shift The shift code.
   * @param subject The subject code.
   * @return A list of student emails currently enrolled at specified shift/subject.
   */
  List<String> getShiftEnrolledStudents(final String shift, final String subject);
}
