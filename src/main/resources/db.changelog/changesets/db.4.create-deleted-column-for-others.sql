ALTER TABLE classroom
ADD deleted BOOLEAN;

ALTER TABLE lesson
ADD deleted BOOLEAN;

ALTER TABLE student
ADD deleted BOOLEAN;
