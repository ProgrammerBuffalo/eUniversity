INSERT INTO ACCOUNTS(LOGIN, PASSWORD, ROLE) VALUES('student1', 'student1', 'STUDENT');
INSERT INTO ACCOUNTS(LOGIN, PASSWORD, ROLE) VALUES('teacher1', 'teacher1', 'TEACHER');

INSERT INTO STUDENTS(ID, FULL_NAME, ACCOUNT_ID) VALUES(RANDOM_UUID(), 'Student Studentson', 1);
INSERT INTO STUDENTS(ID, FULL_NAME, ACCOUNT_ID) VALUES(RANDOM_UUID(), 'Teacher Teacherson', 2);