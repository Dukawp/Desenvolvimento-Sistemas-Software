select sgt.add_student('74092@uminho.pt', 'Tiago Fraga', '12345678', false);
select sgt.add_student('75368@uminho.pt', 'Ricardo Cardante', '12345678', true);
select sgt.add_student('1@uminho.pt', 'Cesário Miguel', '12345678', false);
select sgt.add_student('2@uminho.pt', 'Duarte Freitas', '12345678', false);
select sgt.add_student('3@uminho.pt', 'Pedro Marta', '12345678', false);
select sgt.add_student('4@uminho.pt', 'João Gomes', '12345678', false);
select sgt.add_student('5@uminho.pt', 'João Reis', '12345678', false);

select sgt.add_teacher('7@uminho.pt', 'Pedro Rangel Henriques', '12345678', true);
select sgt.add_teacher('8@uminho.pt', 'José Creissac', '12345678', false);
select sgt.add_teacher('9@uminho.pt', 'José Bernardo Barros', '12345678', false);
select sgt.add_teacher('10@uminho.pt', 'José Nuno Oliveira', '12345678', false);
select sgt.add_teacher('11@uminho.pt', 'Vítor Fontes', '12345678', false);
select sgt.add_teacher('12@uminho.pt', 'Nestor', '12345678', false);

select sgt.add_subject('HN1', 'Desenvolvimento de Sistemas de Software', 'DSS', 'MIEI', 'bachelor', 3, '8@uminho.pt');
select sgt.add_subject('HN2', 'Programação Imperativa', 'PI', 'MIEI', 'bachelor', 1, '9@uminho.pt');
select sgt.add_subject('HN3', 'Programação Functional', 'PF', 'MIEI', 'bachelor', 1, '10@uminho.pt');
select sgt.add_subject('HN4', 'Sistemas Operativos', 'SO', 'MIEI', 'bachelor', 2, '11@uminho.pt');
select sgt.add_subject('HN5', 'Sistemas Distribuídos', 'SD', 'MIEI', 'bachelor', 3, '12@uminho.pt');

select sgt.add_shift('TP1', 'HN1', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '7@uminho.pt');
select sgt.add_shift('TP2', 'HN1', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '8@uminho.pt');
select sgt.add_shift('TP3', 'HN1', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '9@uminho.pt');
select sgt.add_shift('TP4', 'HN1', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '10@uminho.pt');
select sgt.add_shift('TP5', 'HN1', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '11@uminho.pt');

select sgt.add_shift('TP1', 'HN2', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '12@uminho.pt');
select sgt.add_shift('TP2', 'HN2', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '7@uminho.pt');
select sgt.add_shift('TP3', 'HN2', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '8@uminho.pt');
select sgt.add_shift('TP4', 'HN2', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '9@uminho.pt');
select sgt.add_shift('TP5', 'HN2', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '10@uminho.pt');

select sgt.add_shift('TP1', 'HN3', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '11@uminho.pt');
select sgt.add_shift('TP2', 'HN3', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '12@uminho.pt');
select sgt.add_shift('TP3', 'HN3', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '7@uminho.pt');
select sgt.add_shift('TP4', 'HN3', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '8@uminho.pt');
select sgt.add_shift('TP5', 'HN3', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '9@uminho.pt');

select sgt.add_shift('TP1', 'HN4', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '10@uminho.pt');
select sgt.add_shift('TP2', 'HN4', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '11@uminho.pt');
select sgt.add_shift('TP3', 'HN4', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '12@uminho.pt');
select sgt.add_shift('TP4', 'HN4', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '7@uminho.pt');
select sgt.add_shift('TP5', 'HN4', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '8@uminho.pt');

select sgt.add_shift('TP1', 'HN5', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '9@uminho.pt');
select sgt.add_shift('TP2', 'HN5', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '10@uminho.pt');
select sgt.add_shift('TP3', 'HN5', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '11@uminho.pt');
select sgt.add_shift('TP4', 'HN5', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '12@uminho.pt');
select sgt.add_shift('TP5', 'HN5', 'TP', 'DI-A1', CAST (50 AS SMALLINT), CAST (30 AS SMALLINT), 'monday', '09:00:00', '11:00:00', '7@uminho.pt');

select sgt.enroll('74092@uminho.pt', 'TP1', 'HN1');
select sgt.enroll('75368@uminho.pt', 'TP2', 'HN1');
select sgt.enroll('1@uminho.pt', 'TP3', 'HN1');
select sgt.enroll('2@uminho.pt', 'TP4', 'HN1');
select sgt.enroll('3@uminho.pt', 'TP5', 'HN1');
select sgt.enroll('4@uminho.pt', 'TP5', 'HN1');
select sgt.enroll('5@uminho.pt', 'TP5', 'HN1');

select sgt.enroll('74092@uminho.pt', 'TP1', 'HN2');
select sgt.enroll('4@uminho.pt', 'TP1', 'HN2');
select sgt.enroll('5@uminho.pt', 'TP1', 'HN2');
select sgt.enroll('75368@uminho.pt', 'TP2', 'HN2');
select sgt.enroll('1@uminho.pt', 'TP3', 'HN2');
select sgt.enroll('2@uminho.pt', 'TP4', 'HN2');
select sgt.enroll('3@uminho.pt', 'TP5', 'HN2');

select sgt.enroll('74092@uminho.pt', 'TP1', 'HN3');
select sgt.enroll('4@uminho.pt', 'TP1', 'HN3');
select sgt.enroll('5@uminho.pt', 'TP1', 'HN3');
select sgt.enroll('75368@uminho.pt', 'TP2', 'HN3');
select sgt.enroll('1@uminho.pt', 'TP3', 'HN3');
select sgt.enroll('2@uminho.pt', 'TP4', 'HN3');
select sgt.enroll('3@uminho.pt', 'TP5', 'HN3');

select sgt.enroll('74092@uminho.pt', 'TP1', 'HN4');
select sgt.enroll('4@uminho.pt', 'TP1', 'HN4');
select sgt.enroll('5@uminho.pt', 'TP1', 'HN4');
select sgt.enroll('75368@uminho.pt', 'TP2', 'HN4');
select sgt.enroll('1@uminho.pt', 'TP3', 'HN4');
select sgt.enroll('2@uminho.pt', 'TP4', 'HN4');
select sgt.enroll('3@uminho.pt', 'TP5', 'HN4');

select sgt.enroll('74092@uminho.pt', 'TP1', 'HN5');
select sgt.enroll('75368@uminho.pt', 'TP2', 'HN5');
select sgt.enroll('1@uminho.pt', 'TP3', 'HN5');
select sgt.enroll('2@uminho.pt', 'TP4', 'HN5');
select sgt.enroll('3@uminho.pt', 'TP5', 'HN5');
select sgt.enroll('4@uminho.pt', 'TP5', 'HN5');
select sgt.enroll('5@uminho.pt', 'TP5', 'HN5');
