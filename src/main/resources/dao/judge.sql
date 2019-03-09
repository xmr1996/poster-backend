--STATEMENT createJudge
INSERT INTO judges (
  first_name,
  last_name,
  status,
  pin,
  email
) VALUES (
  :first_name,
  :last_name,
  :status,
  :pin,
  :email,
);

--STATEMENT readJudges
SELECT * FROM judges;

--STATEMENT readJudge
SELECT * FROM judges WHERE id = :id;

--STATEMENT readJudgesByStatus
SELECT * FROM judges
WHERE status = :status;

--STATEMENT deleteJudge
DELETE FROM judges WHERE id = :id;

--STATEMENT updateJudge
UPDATE judges SET
  first_name = :first_name,
  last_name = :last_name,
  status = :status,
  pin = :pin,
  email = :email
WHERE
  id = :id;

