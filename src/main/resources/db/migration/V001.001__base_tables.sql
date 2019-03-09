CREATE TABLE profiles (
  id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  project VARCHAR (255) NOT NULL,
  created_date BIGINT(25) NOT NULL,
  updated_date BIGINT(25) DEFAULT NULL
);

CREATE TABLE POSTER(
  PosterID VARCHAR(50) PRIMARY KEY,
  PosterTitle VARCHAR(255),
  AuthorEmail VARCHAR(255),
  AuthorFirstName VARCHAR(255),
  AuthorLastName VARCHAR(255),
  AuthorStatus VARCHAR(255),
  AuthorPin INT(255),
  PosterDepartment VARCHAR(255)
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
  FOREIGN KEY(PosterID) REFERENCES POSTER(PosterID),
  FOREIGN KEY(JudgeID) REFERENCES judges(id),
  CONSTRAINT PK_Sore PRIMARY KEY(PosterID,JudgeID,Round),
  created_date BIGINT(25) NOT NULL,
  updated_date BIGINT(25) DEFAULT NULL
);