CREATE TABLE tasks (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  description VARCHAR(255),
  priority INTEGER,
  status INTEGER
);

INSERT INTO tasks (description, priority, status)
VALUES
  ('Task 1', 1, 1),
  ('Task 2', 2, 2),
  ('Task 3', 3, 1);
