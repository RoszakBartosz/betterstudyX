CREATE TABLE student (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    student_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    grade VARCHAR(255),
    registration_date DATE
    );

CREATE TABLE teacher (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    teacher_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    grade VARCHAR(255),
    rate VARCHAR(255)
    );
CREATE TABLE classroom (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    classroom_name VARCHAR(255),
    classroom_location VARCHAR(255)
    );

CREATE TABLE lesson(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    teacher_id BIGINT,
    classroom_id BIGINT,
    lesson_date_time TIMESTAMP,
    topic VARCHAR(255),
    constraint fk_teacher FOREIGN KEY (teacher_id) REFERENCES teacher(id),
    constraint fk_classroom FOREIGN KEY (classroom_id) REFERENCES classroom(id)
    );

--Dlaczego CONSTRAINT jest konieczne?

--CONSTRAINT pozwala na nadanie nazwy kluczowi obcemu (np. fk_teacher).
-- Jest to wymagane, aby w przyszłości móc łatwo odnosić się do tego klucza obcego
--  (np. podczas usuwania lub modyfikowania relacji).

CREATE TABLE students_lessons (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    student_id BIGINT,
    lesson_id BIGINT,
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student(id),
    CONSTRAINT fk_lesson FOREIGN KEY (lesson_id) REFERENCES lesson(id)
    );

CREATE TABLE users (
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    user_role VARCHAR(255)
    );

