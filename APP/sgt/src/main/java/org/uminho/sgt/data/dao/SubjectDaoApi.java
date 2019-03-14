package org.uminho.sgt.data.dao;

import org.uminho.sgt.business.aulas.UnidadeCurricular;

import java.util.List;
import java.util.Optional;

public interface SubjectDaoApi {
  /**
   * Fetch registered subjects from database.
   *
   * @return List of subjects wrapped into UnidadeCurricular objects.
   */
  List<UnidadeCurricular> getSubjects();

  /**
   * Creates a new subject.
   *
   * @param code The subject unique identifier.
   * @param description The subject full description/name.
   * @param abbreviation The subject common abbreviation.
   * @param course The subject course.
   * @param degree The subject degree level (bachelor or masters).
   * @param year The subject year within degree level.
   * @param coordinator The subject coordinator (a teacher email. otherwise it will fail to create).
   */
  void addSubject(final String code,final String description,final String abbreviation,final String course,final String degree,final int year,final String coordinator);

  /**
   * Deletes a subject, if exists.
   *
   * @param code Subject unique code.
   */
  void deleteSubject(final String code);

  /**
   * Gets subject coordinator teacher.
   *
   * @param code Subject unique identifier.
   * @return An optional string, containing either a teacher email or an empty (if subject code does
   *     not exist).
   */
  Optional<String> getCoordinator(final String code);

  /**
   * Gets teachers of specific subject.
   *
   * @param code Subject unique identifier.
   * @return List of teachers associated with provided subject (empty if subject does not exist or
   *     no shifts are yet created).
   */
  List<String> getTeachersOf(final String code);
  
  /**
   * Updates current subject coordinator.
   * @param code Subject code.
   * @param newCoordinator New coordinator email.
   */
  void updateCoordinator(final String code, final String newCoordinator);
}
