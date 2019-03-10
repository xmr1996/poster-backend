CREATE TABLE profiles (
  id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  project VARCHAR (255) NOT NULL,
  created_date BIGINT(25) NOT NULL,
  updated_date BIGINT(25) DEFAULT NULL
);

CREATE TABLE posters (
  id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  poster_id VARCHAR(50),
  title VARCHAR(255),
  email VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  status VARCHAR(255),
  pin INT(255),
  department VARCHAR(255)
);

CREATE TABLE judges (
  id INT(255) AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  status VARCHAR(255),
  pin INT(255),
  email VARCHAR(255)
);

commit;

CREATE TABLE SCORE(
  ScoreID INT(255) AUTO_INCREMENT,
  PosterID VARCHAR(50),
  JudgeID INT(255),
  Round INT(10),
  research_score INT(255),
  comm_score INT(255),
  poster_score INT(255),
  FOREIGN KEY(PosterID) REFERENCES posters(poster_id),
  FOREIGN KEY(JudgeID) REFERENCES judges(id),
  CONSTRAINT PK_Sore PRIMARY KEY(PosterID,JudgeID,Round),
  created_date BIGINT(25) NOT NULL,
  updated_date BIGINT(25) DEFAULT NULL
);