CREATE TABLE tasks (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  description VARCHAR(255) NOT NULL,
  priority INTEGER NOT NULL,
  status INTEGER NOT NULL
);

INSERT INTO tasks (description, priority, status)
VALUES
  ('Task 1', 1, 1),
  ('Task 2', 2, 2),
  ('Task 3', 3, 1);

CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);