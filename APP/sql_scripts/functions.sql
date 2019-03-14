CREATE OR REPLACE FUNCTION sgt.verify_login(email VARCHAR, password VARCHAR)
RETURNS BOOLEAN AS $$
DECLARE valid BOOLEAN;
BEGIN
  SELECT (sgt.user.password = crypt($2, sgt.user.password)) INTO valid
  FROM sgt.user
  WHERE sgt.user.email = $1;

  IF valid IS NULL THEN
    RETURN 0;
  ELSE
    RETURN valid;
  END IF;

END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_role(user_email VARCHAR)
RETURNS VARCHAR AS $$
DECLARE user_role sgt.ROLE;
BEGIN
  SELECT role INTO user_role FROM sgt.user WHERE email = user_email;
  IF user_role IS NULL THEN
    RAISE EXCEPTION 'No user with provided email is registered!';
  ELSE
    RETURN user_role;
  END IF;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION sgt.add_student(email VARCHAR, name VARCHAR, password VARCHAR, worker BOOLEAN)
RETURNS void AS $$
DECLARE role sgt.ROLE;
BEGIN
  IF worker THEN
    role = 'student-worker';
  ELSE
    role = 'student';
  END IF;
  INSERT INTO sgt.user(email, name, password, role, last_login) VALUES (email, name, crypt(password, gen_salt('bf', 8)), role, NULL);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.delete_student(student VARCHAR)
RETURNS VOID AS $$
BEGIN
  DELETE FROM sgt.student_view WHERE email = student;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.add_teacher(email VARCHAR, name VARCHAR, password VARCHAR, director BOOLEAN)
RETURNS void AS $$
DECLARE role sgt.ROLE;
BEGIN
  IF director THEN
    role = 'head-teacher';
  ELSE
    role = 'teacher';
  END IF;
  INSERT INTO sgt.user(email, name, password, role, last_login) VALUES (email, name, crypt(password, gen_salt('bf', 8)), role, NULL);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.delete_teacher(teacher VARCHAR)
RETURNS VOID AS $$
BEGIN
  DELETE FROM sgt.teacher_view WHERE email = teacher;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.add_subject(code VARCHAR, description VARCHAR, abbreviation VARCHAR, course VARCHAR, degree VARCHAR, year INTEGER, coordinator VARCHAR)
RETURNS VOID AS $$
BEGIN
  PERFORM email FROM sgt.teacher_view WHERE email = coordinator;
  IF FOUND THEN
    INSERT INTO sgt.subject VALUES (code, description, abbreviation, course, CAST (degree AS sgt.DEGREE), year, coordinator);
  ELSE
    RAISE EXCEPTION 'No teacher with provided email is registered!';
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.delete_subject(subject VARCHAR)
RETURNS VOID AS $$
BEGIN
  DELETE FROM sgt.subject WHERE code = subject;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.add_shift(shift_code VARCHAR, subject_code VARCHAR, class_type VARCHAR, room VARCHAR, capacity INTEGER, scheduled_classes INTEGER, weekday VARCHAR, start_time VARCHAR, end_time VARCHAR, teacher VARCHAR)
RETURNS VOID AS $$
BEGIN
  PERFORM email FROM sgt.teacher_view WHERE email = teacher;
  IF FOUND THEN
    INSERT INTO sgt.shift VALUES (shift_code, subject_code, CAST (class_type AS sgt.CLASS_TYPE), room, capacity, scheduled_classes, CAST (weekday AS sgt.WEEKDAY), CAST (start_time AS TIME), CAST (end_time AS TIME), teacher);
  ELSE
    RAISE EXCEPTION 'No teacher with provided email is registered!';
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.delete_shift(shift_code VARCHAR, subject_code VARCHAR)
RETURNS VOID AS $$
BEGIN
  DELETE FROM sgt.shift WHERE (code = shift_code AND subject = subject_code);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.enroll(student VARCHAR, shift VARCHAR, subject VARCHAR)
RETURNS VOID AS $$
BEGIN
  PERFORM email FROM sgt.student_view WHERE email = student;
  IF FOUND THEN
    INSERT INTO sgt.student_shift(student, shift, subject) VALUES (student, shift, subject);
  ELSE
    RAISE EXCEPTION 'No student with provided email is registered!';
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.unenroll(email VARCHAR, subject_code VARCHAR)
RETURNS VOID AS $$
BEGIN
  DELETE FROM sgt.student_shift WHERE (student = email AND subject = subject_code);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.delete_direct_trade(email VARCHAR, subject_code VARCHAR)
RETURNS VOID AS $$
BEGIN
  DELETE FROM sgt.direct_trade WHERE (student = email AND intended_subject = subject_code);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.delete_trade_by_request(email VARCHAR, subject_code VARCHAR)
RETURNS VOID AS $$
BEGIN
  DELETE FROM sgt.trade_by_request WHERE (from_student = email AND subject = subject_code);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_students()
RETURNS TABLE (email VARCHAR, name VARCHAR, role sgt.ROLE, last_login timestamp, creation_date timestamp) AS $$
BEGIN
  RETURN QUERY
    SELECT *
    FROM sgt.student_view;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_teachers()
RETURNS TABLE (email VARCHAR, name VARCHAR, role sgt.ROLE, last_login timestamp, creation_date timestamp) AS $$
BEGIN
  RETURN QUERY
    SELECT *
    FROM sgt.teacher_view;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_subjects()
RETURNS TABLE (code VARCHAR, description VARCHAR, abbreviation VARCHAR, course VARCHAR, degree sgt.DEGREE, year SMALLINT, coordinator VARCHAR) AS $$
BEGIN
  RETURN QUERY
    SELECT *
    FROM sgt.subject;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_shifts()
RETURNS TABLE (code VARCHAR, subject VARCHAR, class_type sgt.CLASS_TYPE, room VARCHAR, capacity SMALLINT, scheduled_classes SMALLINT, weekday sgt.WEEKDAY, start_time TIME, end_time TIME, teacher VARCHAR) AS $$
BEGIN
  RETURN QUERY
    SELECT *
    FROM sgt.shift;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_student_shifts()
RETURNS TABLE (student VARCHAR, shift VARCHAR, subject VARCHAR, absences SMALLINT) AS $$
BEGIN
  RETURN QUERY
    SELECT *
    FROM sgt.student_shift;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_direct_trades()
RETURNS TABLE (student VARCHAR, intended_shift VARCHAR, intended_subject VARCHAR, trade_state sgt.TRADE_STATE) AS $$
BEGIN
  RETURN QUERY
    SELECT *
    FROM sgt.direct_trade;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_trades_by_request()
RETURNS TABLE (from_student VARCHAR, to_student VARCHAR, subject VARCHAR, trade_state sgt.TRADE_STATE) AS $$
BEGIN
  RETURN QUERY
    SELECT *
    FROM sgt.trade_by_request;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.mark_absence(student_email VARCHAR, subject_code VARCHAR)
RETURNS VOID AS $$
BEGIN
  PERFORM email FROM sgt.student_view WHERE email = student_email;
  IF FOUND THEN
    UPDATE sgt.student_shift
    SET absences = absences + 1
    WHERE (student = student_email AND subject = subject_code);
  ELSE
    RAISE EXCEPTION 'Either selected student is not registered or currently enrolled in a shift for the selected subject.';
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_absences(student_email VARCHAR, subject_code VARCHAR)
RETURNS SMALLINT AS $$
DECLARE absences_count SMALLINT;
BEGIN
  SELECT absences
  INTO absences_count
  FROM sgt.student_shift
  WHERE (student = student_email AND subject = subject_code);
  IF absences_count IS NULL THEN
    RAISE EXCEPTION 'Either selected student is not registered or currently enrolled in a shift for the selected subject.';
  ELSE
    RETURN absences_count;
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_coordinator(subject_code VARCHAR)
RETURNS VARCHAR AS $$
DECLARE subject_coordinator VARCHAR;
BEGIN
  SELECT coordinator
  INTO subject_coordinator
  FROM sgt.subject
  WHERE code = subject_code;
  IF subject_coordinator IS NULL THEN
    RAISE EXCEPTION 'No subject with such code registered.';
  ELSE
    RETURN subject_coordinator;
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_shifts_of(subject_code VARCHAR)
RETURNS TABLE (shift_code VARCHAR, shift_subject VARCHAR, class_type sgt.CLASS_TYPE, room VARCHAR, capacity SMALLINT, scheduled_classes SMALLINT, weekday sgt.WEEKDAY, start_time TIME, end_time TIME, teacher VARCHAR) AS $$
BEGIN
  PERFORM code FROM sgt.subject WHERE code = subject_code;
  IF FOUND THEN
    RETURN QUERY
      SELECT *
      FROM sgt.shift
      WHERE subject = subject_code;
  ELSE
    RAISE EXCEPTION 'No subject with such code registered.';
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_teachers_of(subject_code VARCHAR)
RETURNS TABLE (teacher_email VARCHAR) AS $$
BEGIN
  PERFORM code FROM sgt.subject WHERE code = subject_code;
  IF FOUND THEN
    RETURN QUERY
      SELECT teacher
      FROM sgt.shift
      WHERE subject = subject_code;
  ELSE
    RAISE EXCEPTION 'No subject with such code registered.';
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_shift_teacher(shift_code VARCHAR, subject_code VARCHAR)
RETURNS VARCHAR AS $$
DECLARE shift_teacher VARCHAR;
BEGIN
  SELECT teacher
  INTO shift_teacher
  FROM sgt.shift
  WHERE (code = shift_code AND subject = subject_code);
  IF shift_teacher IS NULL THEN
    RAISE EXCEPTION 'Teacher for this shift not found.';
  ELSE
    RETURN shift_teacher;
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_enrolled_subjects(student_email VARCHAR)
RETURNS TABLE (subject_code VARCHAR) AS $$
BEGIN
  PERFORM email FROM sgt.student_view WHERE email = student_email;
  IF FOUND THEN
    RETURN QUERY
      SELECT subject
      FROM sgt.student_shift
      WHERE student = student_email;
  ELSE
    RAISE EXCEPTION 'No student with provided email is registered!';
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_current_shift(student_email VARCHAR, subject_code VARCHAR)
RETURNS VARCHAR AS $$
DECLARE shift_code VARCHAR;
BEGIN
  SELECT shift
  INTO shift_code
  FROM sgt.student_shift
  WHERE (student = student_email AND subject = subject_code);
  IF shift_code IS NULL THEN
    RAISE EXCEPTION 'Either student does not exist or not enrolled in provided subject.';
  ELSE
    RETURN shift_code;
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_subjects_by(teacher_email VARCHAR)
RETURNS TABLE(subject_code VARCHAR) AS $$
BEGIN
  PERFORM email FROM sgt.teacher_view WHERE email = teacher_email;
  IF FOUND THEN
    RETURN QUERY
      SELECT DISTINCT subject
      FROM sgt.shift
      WHERE teacher = teacher_email;
  ELSE
    RAISE EXCEPTION 'Provided teacher email not registered.';
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_shifts_by(teacher_email VARCHAR, subject_code VARCHAR)
RETURNS TABLE(shift_code VARCHAR) AS $$
BEGIN
  PERFORM email FROM sgt.teacher_view WHERE email = teacher_email;
  IF FOUND THEN
    PERFORM code FROM sgt.subject WHERE code = subject_code;
    IF FOUND THEN
      RETURN QUERY
        SELECT code
        FROM sgt.shift
        WHERE (teacher = teacher_email AND subject = subject_code);
    ELSE
      RAISE EXCEPTION 'No subject with such code registered.';
    END IF;
  ELSE
    RAISE EXCEPTION 'Provided teacher email not registered.';
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.add_direct_trade(student_email VARCHAR, new_shift VARCHAR, subject_code VARCHAR)
RETURNS VOID AS $$
DECLARE current_shift VARCHAR;
BEGIN
  SELECT shift INTO current_shift FROM sgt.student_shift WHERE (student = student_email AND subject = subject_code);
  IF (current_shift IS NOT NULL AND current_shift <> new_shift) THEN
    INSERT INTO sgt.direct_trade(student, intended_shift, intended_subject) VALUES (student_email, new_shift, subject_code);
  ELSE
    RAISE EXCEPTION 'Either provided student email is not registered at any shift for provided subject or is already enrolled at selected shift.';
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.add_trade_by_request(from_student_email VARCHAR, to_student_email VARCHAR, subject_code VARCHAR)
RETURNS VOID AS $$
DECLARE distinct_shifts INTEGER;
BEGIN
  PERFORM student, subject FROM sgt.student_shift WHERE (student = from_student_email AND subject = subject_code);
  IF FOUND THEN
    PERFORM student, subject FROM sgt.student_shift WHERE (student = to_student_email AND subject = subject_code);
    IF FOUND THEN
      SELECT COUNT(DISTINCT shift) INTO distinct_shifts FROM sgt.student_shift WHERE ((student = from_student_email OR student = to_student_email) AND subject = subject_code);
      IF distinct_shifts <> 1 THEN
        INSERT INTO sgt.trade_by_request(from_student, to_student, subject) VALUES (from_student_email, to_student_email, subject_code);
      ELSE
        RAISE EXCEPTION 'Provided students are enrolled at same shift for selected subject';
      END IF;
    ELSE
      RAISE EXCEPTION 'Destination student is not currently enrolled at any shift for provided subject';
    END IF;
  ELSE
    RAISE EXCEPTION 'Provided student email is not registered at any shift for provided subject.';
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_direct_trade_intended_shift(student_email VARCHAR, subject_code VARCHAR)
RETURNS VARCHAR AS $$
DECLARE shift VARCHAR;
BEGIN
  SELECT intended_shift
  INTO shift
  FROM sgt.direct_trade
  WHERE (student = student_email AND intended_subject = subject_code);
  IF shift IS NULL THEN
    RAISE EXCEPTION 'There is no direct trade registered for selected student and subject.';
  ELSE
    RETURN shift;
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_trade_by_request_student_destination(student_email VARCHAR, subject_code VARCHAR)
RETURNS VARCHAR AS $$
DECLARE student VARCHAR;
BEGIN
  SELECT to_student
  INTO student
  FROM dgt.trade_by_request
  WHERE (from_student = student_email AND subject = subject_code);
  IF student IS NULL THEN
    RAISE EXCEPTION 'There is no request by the selected student and subject';
  ELSE
    RETURN student;
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_direct_trade_state(student_email VARCHAR, subject_code VARCHAR)
RETURNS VARCHAR AS $$
DECLARE state VARCHAR;
BEGIN
  SELECT trade_state
  INTO state
  FROM sgt.direct_trade
  WHERE (student = student_email AND intended_subject = subject_code);
  IF state IS NULL THEN
    RAISE EXCEPTION 'There is no direct trade registered for the selected student and subject';
  ELSE
    RETURN state;
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_trade_by_request_state(student_email VARCHAR, subject_code VARCHAR)
RETURNS VARCHAR AS $$
DECLARE state VARCHAR;
BEGIN
  SELECT trade_state
  INTO state
  FROM sgt.trade_by_request
  WHERE (from_student = student_email AND subject = subject_code);
  IF state IS NULL THEN
    RAISE EXCEPTION 'There is no direct trade registered for the selected student and subject';
  ELSE
    RETURN state;
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.update_direct_trade_state(student_email VARCHAR, subject_code VARCHAR, new_state VARCHAR)
RETURNS VOID AS $$
BEGIN
  PERFORM student, intended_subject FROM sgt.direct_trade WHERE (student = student_email AND intended_subject = subject_code);
  IF FOUND THEN
    UPDATE sgt.direct_trade
    SET trade_state = CAST (new_state AS sgt.TRADE_STATE)
    WHERE (student = student_email AND intended_subject = subject_code);
  ELSE
    RAISE EXCEPTION 'No direct trade found.';
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.update_trade_by_request_state(student_email VARCHAR, subject_code VARCHAR, new_state VARCHAR)
RETURNS VOID AS $$
BEGIN
  PERFORM from_student, subject FROM sgt.trade_by_request WHERE (from_student = student_email AND subject = subject_code);
  IF FOUND THEN
    UPDATE sgt.trade_by_request
    SET trade_state = CAST (new_state AS sgt.TRADE_STATE)
    WHERE (from_student = student_email AND subject = subject_code);
  ELSE
    RAISE EXCEPTION 'No trade by request found.';
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.change_shift_teacher(shift_code VARCHAR, subject_code VARCHAR, new_teacher_email VARCHAR)
RETURNS VOID AS $$
BEGIN
  PERFORM code, subject FROM sgt.shift WHERE (code = shift_code AND subject = subject_code);
  IF FOUND THEN
    UPDATE sgt.shift
    SET teacher = new_teacher_email
    WHERE (code = shift_code AND subject = subject_code);
  ELSE
    RAISE EXCEPTION 'Shift not found.';
  END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.get_shift_enrolled_students(shift_code VARCHAR, subject_code VARCHAR)
RETURNS TABLE (student_email VARCHAR) AS $$
BEGIN
  RETURN QUERY
    SELECT student
    FROM sgt.student_shift
    WHERE (shift = shift_code AND subject = subject_code);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION sgt.update_coordinator(subject_code VARCHAR, new_coordinator VARCHAR)
RETURNS VOID AS $$
BEGIN
  UPDATE sgt.subject
  SET coordinator = new_coordinator
  WHERE code = subject_code;
END;
$$ LANGUAGE plpgsql;
