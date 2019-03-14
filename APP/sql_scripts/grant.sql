GRANT ALL ON SCHEMA sgt TO g6;

GRANT EXECUTE ON FUNCTION sgt.add_direct_trade(student_email character varying, new_shift character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.add_shift(shift_code character varying, subject_code character varying, class_type character varying, room character varying, capacity integer, scheduled_classes integer, weekday character varying, start_time character varying, end_time character varying, teacher character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.add_student(email character varying, name character varying, password character varying, worker boolean) TO g6;

GRANT EXECUTE ON FUNCTION sgt.add_subject(code character varying, description character varying, abbreviation character varying, course character varying, degree character varying, year integer, coordinator character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.add_teacher(email character varying, name character varying, password character varying, director boolean) TO g6;

GRANT EXECUTE ON FUNCTION sgt.add_trade_by_request(from_student_email character varying, to_student_email character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.change_shift_teacher(shift_code character varying, subject_code character varying, new_teacher_email character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.delete_direct_trade(email character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.delete_shift(shift_code character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.delete_student(student character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.delete_subject(subject character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.delete_teacher(teacher character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.delete_trade_by_request(email character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.enroll(student character varying, shift character varying, subject character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_absences(student_email character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_coordinator(subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_current_shift(student_email character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_direct_trade_intended_shift(student_email character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_direct_trade_state(student_email character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_direct_trades() TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_enrolled_subjects(student_email character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_role(user_email character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_shift_teacher(shift_code character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_shifts() TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_shifts_by(teacher_email character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_shifts_of(subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_student_shifts() TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_students() TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_subjects() TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_subjects_by(teacher_email character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_teachers() TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_teachers_of(subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_trade_by_request_state(student_email character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_trade_by_request_student_destination(student_email character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_trades_by_request() TO g6;

GRANT EXECUTE ON FUNCTION sgt.mark_absence(student_email character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.unenroll(email character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.update_direct_trade_state(student_email character varying, subject_code character varying, new_state character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.update_trade_by_request_state(student_email character varying, subject_code character varying, new_state character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.verify_login(email character varying, password character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.get_shift_enrolled_students(shift_code character varying, subject_code character varying) TO g6;

GRANT EXECUTE ON FUNCTION sgt.update_coordinator(subject_code character varying, new_coordinator character varying) TO g6;

GRANT ALL ON TABLE sgt.direct_trade TO g6;

GRANT ALL ON TABLE sgt.shift TO g6;

GRANT ALL ON TABLE sgt.student_shift TO g6;

GRANT ALL ON TABLE sgt.subject TO g6;

GRANT ALL ON TABLE sgt.trade_by_request TO g6;

GRANT ALL ON TABLE sgt."user" TO g6;

GRANT ALL ON TABLE sgt.student_no_worker_view TO g6;

GRANT ALL ON TABLE sgt.student_view TO g6;

GRANT ALL ON TABLE sgt.student_worker_view TO g6;

GRANT ALL ON TABLE sgt.teacher_director_view TO g6;

GRANT ALL ON TABLE sgt.teacher_not_director_view TO g6;

GRANT ALL ON TABLE sgt.teacher_view TO g6;
