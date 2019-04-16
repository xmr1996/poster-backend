--STATEMENT createJudge
INSERT INTO judges (
  judge_id,
  first_name,
  last_name,
  status,
  pin,
  email,
  role
) VALUES (
  :judge_id,
  :first_name,
  :last_name,
  :status,
  :pin,
  :email,
  :role
);

--STATEMENT upsertJudge
INSERT INTO judges (
  judge_id,
  first_name,
  last_name,
  status,
  pin,
  email,
  role
) VALUES (
  :judge_id,
  :first_name,
  :last_name,
  :status,
  :pin,
  :email,
  :role
)
ON DUPLICATE KEY UPDATE
  first_name = :first_name,
  last_name = :last_name,
  status = :status,
  pin = :pin,
  email = :email,
  role = :role;


--STATEMENT readJudges
SELECT * FROM judges;

--STATEMENT readJudgeByJudgeId
SELECT * FROM judges WHERE judge_id = :judge_id;

--STATEMENT readJudgesByStatus
SELECT * FROM judges
WHERE status = :status;

--STATEMENT deleteJudgeByJudgeId
DELETE FROM judges WHERE judge_id = :judge_id;

--STATEMENT updateJudge
UPDATE judges SET
  first_name = :first_name,
  last_name = :last_name,
  status = :status,
  pin = :pin,
  email = :email
WHERE
  judge_id = :judge_id;

--STATEMENT readJudgeEmailPin
SELECT * FROM judges WHERE email = :email and pin = :pin;

--STATEMENT clearJudges
DELETE FROM judges;
