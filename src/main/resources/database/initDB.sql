CREATE TABLE IF NOT EXISTS category (
  category_id   INT(11)     NOT NULL AUTO_INCREMENT,
  category_name VARCHAR(50) NOT NULL,
  PRIMARY KEY (category_id),
  INDEX (category_name),
  UNIQUE (category_name)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS startup (
  startup_id         INT(11)     NOT NULL AUTO_INCREMENT,
  startup_name       VARCHAR(50) NOT NULL,
  picture            LONGBLOB,
  attachment         LONGBLOB,
  current_investment INT(11)              DEFAULT 0,
  needed_investment  INT(11)     NOT NULL,
  description        VARCHAR(2500)        DEFAULT NULL,
  rating             DOUBLE               DEFAULT NULL,
  category           INT(11)     NOT NULL,
  status             VARCHAR(30),
  PRIMARY KEY (startup_id),
  INDEX (startup_name),
  FOREIGN KEY (category) REFERENCES category (category_id)
)
  ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS user (
  user_id      INT(11)      NOT NULL AUTO_INCREMENT,
  first_name   VARCHAR(255) NOT NULL,
  last_name    VARCHAR(255) NOT NULL,
  phone_number VARCHAR(20)           DEFAULT NULL,
  email        VARCHAR(40)  NOT NULL,
  country      VARCHAR(20)           DEFAULT NULL,
  city         VARCHAR(20)           DEFAULT NULL,
  login        VARCHAR(255) NOT NULL,
  password     VARCHAR(2500) NOT NULL,
  is_active    TINYINT               DEFAULT 1,
  PRIMARY KEY (user_id),
  INDEX (first_name, last_name)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS startup_evaluation (
  user_id    INT(11) NOT NULL,
  startup_id INT(11) NOT NULL,
  mark       INT(11) NOT NULL,
  PRIMARY KEY (user_id, startup_id),
  FOREIGN KEY (user_id) REFERENCES user (user_id),
  FOREIGN KEY (startup_id) REFERENCES startup (startup_id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS user_startup (
  user_id         INT(11) NOT NULL,
  startup_id      INT(11) NOT NULL,
  user_investment INT DEFAULT 0,
  PRIMARY KEY (user_id, startup_id),
  FOREIGN KEY (user_id) REFERENCES user (user_id),
  FOREIGN KEY (startup_id) REFERENCES startup (startup_id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS permission (
  permission_id   INT(11)      NOT NULL AUTO_INCREMENT,
  permission_name VARCHAR(300) NOT NULL,
  PRIMARY KEY (permission_id),
  INDEX (permission_name),
  UNIQUE (permission_name)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS role (
  role_id   INT(11)      NOT NULL AUTO_INCREMENT,
  role_name VARCHAR(100) NOT NULL,
  PRIMARY KEY (role_id),
  INDEX (role_name),
  UNIQUE (role_name)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS user_role (
  user_id INT(11) NOT NULL,
  role_id INT(11) NOT NULL,
  UNIQUE (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES user (user_id),
  FOREIGN KEY (role_id) REFERENCES role (role_id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS role_permission (
  role_id       INT(11) NOT NULL,
  permission_id INT(11) NOT NULL,
  UNIQUE (role_id, permission_id),
  FOREIGN KEY (role_id) REFERENCES role (role_id),
  FOREIGN KEY (permission_id) REFERENCES permission (permission_id)
)
  ENGINE = InnoDB;
