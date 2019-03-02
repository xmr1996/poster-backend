CREATE TABLE profiles (
  id BIGINT(20) AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  project VARCHAR (255) NOT NULL,
  created_date BIGINT(25) NOT NULL,
  updated_date BIGINT(25) DEFAULT NULL
);


CREATE TABLE AUTHOR(
  AuthorID SMALLINT(255) PRIMARY KEY,
  AuthorFirstName VARCHAR(255),
  AuthorLastName VARCHAR(255),
  AuthorEmail VARCHAR(255),
  AuthorStatus VARCHAR(255)
);
commit;

CREATE TABLE POSTER(
  PosterID VARCHAR(50) PRIMARY KEY,
  PosterTitle VARCHAR(255),
  PosterAbstract TEXT(700),
  PosterDepartment VARCHAR(255),
  PosterLink  VARCHAR(255),
  PosterFileName VARCHAR(255),
  PosterPresentationLink VARCHAR(255),
  AdvisorFirstName VARCHAR(255),
  AdvisorLastName VARCHAR(255),
  AdvisorEmail VARCHAR(255),
  AuthorID SMALLINT(255),
  FOREIGN KEY (AuthorID) REFERENCES AUTHOR(AuthorID)
);

CREATE TABLE JUDGE(
  JudgeID INT(255) PRIMARY KEY,
  JudgeFirstName VARCHAR(255),
  JudgeLastName VARCHAR(255),
  JudgeStatus VARCHAR(255),
  JudgePin INT(255),
  JudgeEmail VARCHAR(255)
);

commit;

ALTER TABLE AUTHOR ADD PosterID VARCHAR(50);
ALTER TABLE AUTHOR ADD FOREIGN KEY(PosterID) REFERENCES POSTER(PosterID);

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