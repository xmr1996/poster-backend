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
  PosterAbstract TEXT(700),
  PosterDepartment VARCHAR(255),
  PosterLink  VARCHAR(255),
  PosterFileName VARCHAR(255),
  PosterPresentationLink VARCHAR(255),
  AuthorFirstName VARCHAR(255),
  AuthorLastName VARCHAR(255),
  AuthorEmail VARCHAR(255),
  AuthorStatus VARCHAR(255)
);

CREATE TABLE JUDGE(
  JudgeID INT(255) AUTO_INCREMENT PRIMARY KEY,
  JudgeFirstName VARCHAR(255),
  JudgeLastName VARCHAR(255),
  JudgeStatus VARCHAR(255),
  JudgePin INT(255),
  JudgeEmail VARCHAR(255)
);

commit;

CREATE TABLE SCORE(
  PosterID VARCHAR(50),
  JudgeID INT(255),
  Round INT(10),
  research_score INT(255),
  comm_score INT(255),
  poster_score INT(255),
  FOREIGN KEY(PosterID) REFERENCES POSTER(PosterID),
  FOREIGN KEY(JudgeID) REFERENCES JUDGE(JudgeID),
  CONSTRAINT PK_Sore PRIMARY KEY(PosterID,JudgeID,Round)
);