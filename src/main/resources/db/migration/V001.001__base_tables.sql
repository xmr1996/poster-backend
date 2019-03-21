CREATE TABLE profiles (
  id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  project VARCHAR (255) NOT NULL,
  created_date BIGINT(25) NOT NULL,
  updated_date BIGINT(25) DEFAULT NULL
);

CREATE TABLE posters (
  id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  poster_id VARCHAR(255),
  title VARCHAR(255),
  email VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  status VARCHAR(255),
  pin INT(255),
  department VARCHAR(255),
  votes INT(255),
  has_voted VARCHAR(255),
  role VARCHAR(255),
  avg_r1 DOUBLE(255) DEFAULT NULL,
  avg_r2 DOUBLE(255) DEFAULT NULL
);

CREATE TABLE judges (
  id INT(255) AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  status VARCHAR(255),
  pin INT(255),
  email VARCHAR(255),
  role VARCHAR(255),
);

commit;

CREATE TABLE SCORE(
  id INT(255) AUTO_INCREMENT,
  poster_id VARCHAR(255),
  judge_id INT(255),
  round INT(10) DEFAULT NULL,
  research_score INT(255) DEFAULT NULL,
  comm_score INT(255) DEFAULT NULL,
  poster_score INT(255) DEFAULT NULL,
  total_score INT(255) DEFAULT NULL,
  FOREIGN KEY(poster_id) REFERENCES posters(poster_id),
  FOREIGN KEY(judge_id) REFERENCES judges(id),
  CONSTRAINT PK_Sore PRIMARY KEY(poster_id,judge_id,round),
 -- created_date BIGINT(25) NOT NULL,
  --updated_date BIGINT(25) DEFAULT NULL
);
