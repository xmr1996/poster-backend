--STATEMENT createAdmin
INSERT INTO administrator (
  email,
  first_name,
  last_name,
  read_r,
  write_w,
  pin,
  role
) VALUES (
  :email,
  :first_name,
  :last_name,
  :read_r,
  :write_w,
  :pin,
  :role
);

--STATEMENT readAllAdmins
SELECT * FROM administrator;

--STATEMENT readAdmin
SELECT * FROM administrator
WHERE email = :email;

--STATEMENT readAdminEmailPin
SELECT * FROM administrator WHERE email = :email and pin = :pin;

--STATEMENT updateAdmin
UPDATE administrator
SET
  first_name = :first_name,
  last_name = :last_name,
  read_r = :read_r,
  write_w = :write_w,
  pin = :pin
WHERE
  email = :email;

--STATEMENT deleteAdmin
DELETE FROM administrator WHERE email = :email;

--STATEMENT clearAdmins
DELETE FROM administrator;
