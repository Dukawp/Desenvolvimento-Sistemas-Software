CREATE EXTENSION pgcrypto;

CREATE USER g6 WITH NOSUPERUSER NOCREATEDB NOCREATEROLE NOINHERIT NOREPLICATION NOBYPASSRLS PASSWORD 'Y+!Rfhd8pgHDr$[T';

CREATE SCHEMA sgt AUTHORIZATION rcardante;

CREATE TYPE sgt.ROLE AS ENUM ('student', 'student-worker', 'teacher', 'head-teacher');

CREATE TYPE sgt.DEGREE AS ENUM ('bachelor', 'masters');

CREATE TYPE sgt.WEEKDAY AS ENUM('monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday', 'sunday');

CREATE TYPE sgt.CLASS_TYPE AS ENUM('PL', 'TP');

CREATE TYPE sgt.TRADE_STATE AS ENUM ('pendent', 'rejected', 'concluded');

CREATE TABLE sgt.user (
  email VARCHAR(32) NOT NULL,
  name VARCHAR(64) NOT NULL,
  password VARCHAR(64) NOT NULL,
  role sgt.ROLE NOT NULL,
  last_login timestamp,
  creation_date timestamp DEFAULT current_timestamp,
  CONSTRAINT email_pattern CHECK (email ~* '[A-Za-z0-9._-]+?[@][A-Za-z0-9.-]*?uminho[.]pt$'),
  CONSTRAINT name_empty CHECK (name <> ''),
  CONSTRAINT password_length CHECK (char_length(password) >= 8),
  PRIMARY KEY(email)
);

CREATE TABLE sgt.subject (
  code VARCHAR(8) NOT NULL,
  description VARCHAR(64) NOT NULL,
  abbreviation VARCHAR(8) NOT NULL,
  course VARCHAR(32) NOT NULL,
  degree sgt.DEGREE NOT NULL,
  year SMALLINT NOT NULL,
  coordinator VARCHAR(32) references sgt.user(email) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT code_empty CHECK (code <> ''),
  CONSTRAINT description_empty CHECK (description <> ''),
  CONSTRAINT abbreviation_empty CHECK (abbreviation <> ''),
  CONSTRAINT course_empty CHECK (course <> ''),
  CONSTRAINT year_range CHECK (year > 0 AND year < 6),
  PRIMARY KEY(code)
);

CREATE TABLE sgt.shift (
  code VARCHAR(8) NOT NULL,
  subject VARCHAR(8) references sgt.subject(code) ON UPDATE CASCADE ON DELETE RESTRICT,
  class_type sgt.CLASS_TYPE NOT NULL,
  room VARCHAR(16) NOT NULL,
  capacity SMALLINT NOT NULL,
  scheduled_classes SMALLINT NOT NULL,
  weekday sgt.WEEKDAY NOT NULL,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL,
  teacher VARCHAR(32) references sgt.user(email) ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT code_empty CHECK (code <> ''),
  CONSTRAINT capacity_min CHECK (capacity > 0),
  CONSTRAINT scheduled_classes_min CHECK (scheduled_classes > 0),
  CONSTRAINT valid_schedule CHECK (start_time < end_time),
  PRIMARY KEY (code, subject)
);

CREATE TABLE sgt.student_shift (
  student VARCHAR(32) references sgt.user(email) ON UPDATE CASCADE ON DELETE RESTRICT,
  shift VARCHAR(8) NOT NULL,
  subject VARCHAR(8) NOT NULL,
  absences SMALLINT DEFAULT 0,
  FOREIGN KEY (shift, subject) references sgt.shift(code, subject) ON UPDATE CASCADE ON DELETE RESTRICT,
  PRIMARY KEY (student, subject)
);

CREATE TABLE sgt.direct_trade (
  student VARCHAR(32) references sgt.user(email) ON UPDATE CASCADE ON DELETE RESTRICT,
  intended_shift VARCHAR(8) NOT NULL,
  intended_subject VARCHAR(8) NOT NULL,
  trade_state sgt.TRADE_STATE DEFAULT 'pendent',
  FOREIGN KEY (intended_shift, intended_subject) references sgt.shift(code, subject) ON UPDATE CASCADE ON DELETE RESTRICT,
  PRIMARY KEY (student, intended_subject)
);

CREATE TABLE sgt.trade_by_request (
  from_student VARCHAR(32) references sgt.user(email) ON UPDATE CASCADE ON DELETE RESTRICT,
  to_student VARCHAR(32) references sgt.user(email) ON UPDATE CASCADE ON DELETE RESTRICT,
  subject VARCHAR(8) references sgt.subject(code) ON UPDATE CASCADE ON DELETE RESTRICT,
  trade_state sgt.TRADE_STATE DEFAULT 'pendent',
  PRIMARY KEY (from_student, subject)
);

CREATE VIEW sgt.student_view AS
SELECT sgt.user.email, sgt.user.name, sgt.user.role, sgt.user.last_login, sgt.user.creation_date
FROM sgt.user
WHERE sgt.user.role = 'student-worker' OR sgt.user.role = 'student';

CREATE VIEW sgt.student_worker_view AS
SELECT * FROM sgt.student_view
WHERE sgt.student_view.role = 'student-worker'
WITH CASCADED CHECK OPTION;

CREATE VIEW sgt.student_no_worker_view AS
SELECT * FROM sgt.student_view
WHERE sgt.student_view.role = 'student'
WITH CASCADED CHECK OPTION;

CREATE VIEW sgt.teacher_view AS
SELECT sgt.user.email, sgt.user.name, sgt.user.role, sgt.user.last_login, sgt.user.creation_date
FROM sgt.user
WHERE sgt.user.role = 'teacher' OR sgt.user.role = 'head-teacher';

CREATE VIEW sgt.teacher_not_director_view AS
SELECT * FROM sgt.teacher_view
WHERE sgt.teacher_view.role = 'teacher'
WITH CASCADED CHECK OPTION;

CREATE VIEW sgt.teacher_director_view AS
SELECT * FROM sgt.teacher_view
WHERE sgt.teacher_view.role = 'head-teacher'
WITH CASCADED CHECK OPTION;
