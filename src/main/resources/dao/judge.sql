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
SELECT * FROM JUDGE
WHERE JudgeStatus = :JudgeStatus;

--STATEMENT deleteJudgeById
DELETE FROM JUDGE WHERE JUDGEID = :JUDGEID;



