UPDATE lesson SET lesson_version = 0 WHERE lesson_version IS NULL;
ALTER TABLE lesson ALTER COLUMN lesson_version SET DEFAULT 0;