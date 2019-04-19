CREATE TABLE profiles (
  id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  project VARCHAR (255) NOT NULL,
  created_date BIGINT(25) NOT NULL,
  updated_date BIGINT(25) DEFAULT NULL
);

CREATE TABLE posters (
  poster_id VARCHAR(255) PRIMARY KEY NOT NULL,
  title VARCHAR(255),
  email VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  status VARCHAR(255),
  pin VARCHAR(255),
  department VARCHAR(255),
  voted_for VARCHAR(255),
  role VARCHAR(255),
  avg_r1 DECIMAL(6,2) DEFAULT NULL,
  avg_r2 DECIMAL(6,2) DEFAULT NULL,
  avg_comm_r1 DECIMAL(6,2) DEFAULT NULL,
  avg_comm_r2 DECIMAL(6,2) DEFAULT NULL,
  avg_research_r1 DECIMAL(6,2) DEFAULT NULL,
  avg_research_r2 DECIMAL(6,2) DEFAULT NULL,
  avg_pres_r1 DECIMAL(6,2) DEFAULT NULL,
  avg_pres_r2 DECIMAL(6,2) DEFAULT NULL
);

CREATE TABLE judges (
  judge_id INT(255) PRIMARY KEY NOT NULL,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  status VARCHAR(255),
  pin VARCHAR(255),
  email VARCHAR(255),
  role VARCHAR(255)
);

commit;

CREATE TABLE score(
  poster_id VARCHAR(255) NOT NULL,
  judge_id INT(255) NOT NULL,
  round INT(10) DEFAULT 1,
  research_score INT(255) DEFAULT NULL,
  comm_score INT(255) DEFAULT NULL,
  poster_score INT(255) DEFAULT NULL,
  total_score INT(255) DEFAULT NULL,
  FOREIGN KEY(poster_id) REFERENCES posters(poster_id) ON DELETE CASCADE ,
  FOREIGN KEY(judge_id) REFERENCES judges(judge_id) ON DELETE CASCADE,
  CONSTRAINT PK_Sore PRIMARY KEY(poster_id,judge_id,round)
);

CREATE TABLE administrator(
  email VARCHAR(255) PRIMARY KEY not null ,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  read_r BOOLEAN,
  write_w BOOLEAN,
  pin VARCHAR(255),
  role VARCHAR(255)
);

CREATE TABLE round(
    round1 BOOLEAN DEFAULT FALSE,
    round2 BOOLEAN DEFAULT FALSE
);

COMMIT;
INSERT INTO round VALUES (FALSE,FALSE);
