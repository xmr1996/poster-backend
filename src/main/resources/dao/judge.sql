--STATEMENT createJudge
INSERT INTO JUDGE (
  JudgeFirstName,
  JudgeLastName,
  JudgeStatus,
  JudgePin,
  JudgeEmail
) VALUES (
  :JudgeFirstName,
  :JudgeLastName,
  :JudgeStatus,
  :JudgePin,
  :JudgeEmail
);

--STATEMENT getJudges
SELECT * FROM JUDGE;

--STATEMENT getJudgeByID
SELECT * FROM JUDGE WHERE JudgeId = :JudgeID;

--STATEMENT getJudgesByStatus
SELECT JudgeID, JudgeFirstName, JudgeStatus, JudgeEmail FROM JUDGE
WHERE JudgeStatus = :JudgeStatus;

--STATEMENT getJudgeUsernamesAndPins
SELECT JudgeEmail, JudgePin FROM JUDGE;

--STATEMENT deleteJudgeById
DELETE FROM JUDGE WHERE JUDGEID = :JUDGEID;



