package org.uminho.sgt.data.dao;

import org.uminho.sgt.business.pessoal.Aluno;
import org.uminho.sgt.business.pessoal.Professor;

import java.util.List;
import java.util.Optional;

public interface UserDaoApi {

  /**
   * Fetch all registered students.
   *
   * @return List of currently registered students, wrapped into Aluno objects.
   */
  List<Aluno> getStudents();

  /**
   * Fetch all registered teachers.
   *
   * @return List of currently registered teachers, wrapped into Professor objects.
   */
  List<Professor> getTeachers();

  /**
   * Creates a new student.
   *
   * @param email The student unique identifier. Should follow the regex pattern
   *     [A-Za-z0-9._-]+?[@][A-Za-z0-9.-]*?uminho[.]pt$
   * @param name Student complete name.
   * @param password Student password, which will be ciphered when persisted (it must have a minimum
   *     length of eight characters).
   * @param worker A boolean which states if new student works.
   */
  void addStudent(final String email, final String name, final String password, boolean worker);

  /**
   * Deletes a student, if exists.
   *
   * @param email Unique identifier to delete student, as provided on creation.
   */
  void deleteStudent(final String email);

  /**
   * Creates a new teacher.
   *
   * @param email The teacher unique identifier. Should follow the regex pattern
   *     [A-Za-z0-9._-]+?[@][A-Za-z0-9.-]*?uminho[.]pt$
   * @param name Teacher complete name.
   * @param password Teacher password, which will be ciphered when persisted (it must have a minimum
   *     length of eight characters).
   * @param director A boolean which states if new teacher is course director.
   */
  void addTeacher(final String email, final String name, final String password, boolean director);

  /**
   * Deletes a teacher, if exists.
   *
   * @param email The teacher unique identifier, as provided on creation.
   */
  void deleteTeacher(final String email);

  /**
   * Checks if email and password combination are authorized to access login resources.
   *
   * @param email User unique identifier, which should follow pattern
   *     [A-Za-z0-9._-]+?[@][A-Za-z0-9.-]*?uminho[.]pt$
   * @param password User password.
   * @return A boolean which states if login is valid or not.
   */
  boolean verifyLogin(final String email, final String password);

  /**
   * Gets user role (student, student-worker, teacher or head-teacher).
   *
   * @param email User unique identifier, as provided on creation.
   * @return An optional String containing either the role or an empty (depending on user's
   *     existence), protecting program from null pointers.
   */
  Optional<String> getRole(final String email);

  /**
   * Marks an absence to a student for a specific subject (if enrolled).
   *
   * @param email The student unique identifier, as specified on creation.
   * @param subject The subject code.
   */
  void markAbsence(final String email, final String subject);

  // Returns -1 when exception occurs

  /**
   * Gets student absences count for a specific subject.
   *
   * @param email The unique student identifier, as provided on creation.
   * @param subject The subject to mark absence.
   * @return -1 if student is not enrolled at any shift for provided subject; absences count
   *     otherwise.
   */
  int getAbsences(final String email, final String subject);

  /**
   * Gets list of enrolled subjects identifiers for a specific student.
   *
   * @param email The student unique identifier, as specified on creation.
   * @return List of subjects codes.
   */
  List<String> getStudentSubjects(final String email);

  /**
   * Gets student current shift for a specific subject, if enrolled.
   *
   * @param email The student unique identifier, as provided on creation.
   * @param subject The subject to search for current shift.
   * @return An optional string, which contains either shift code (if enrolled) or an empty (if not
   *     enrolled), protecting from null pointers.
   */
  Optional<String> getStudentShift(final String email, final String subject);
}
