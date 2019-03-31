--STATEMENT createAdmin
INSERT INTO admin (
  email,
  first_name,
  last_name,
  read,
  write,
  pin,
  role
) VALUES (
  :email,
  :first_name,
  :last_name,
  :read,
  :write,
  :pin,
  :role
);

--STATEMENT readAllAdmins
SELECT * FROM admin;

--STATEMENT readAdmin
SELECT * FROM admin
WHERE email = :email;

--STATEMENT updateAdmin
UPDATE admin
SET
  first_name = :first_name,
  last_name = :last_name,
  read = :read,
  write = :write,
  pin = :pin
WHERE
  email = :email;

--STATEMENT deleteAdmin
DELETE FROM admin WHERE email = :email;

--STATEMENT clearAdmins
DELETE FROM admin;
