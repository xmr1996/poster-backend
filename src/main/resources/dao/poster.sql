--STATEMENT createPoster
INSERT INTO posters (
  poster_id,
  title,
  email,
  first_name,
  last_name,
  status,
  pin,
  department,
  votes,
  has_voted,
  role
) VALUES (
  :poster_id,
  :title,
  :email,
  :first_name,
  :last_name,
  :status,
  :pin,
  :department,
  :votes,
  :has_voted,
  :role
);

--STATEMENT readPoster
SELECT * FROM posters WHERE id = :id;

--STATEMENT readPosterByID
SELECT * FROM posters where poster_id = :poster_id;

--STATEMENT readGradWinners
SELECT * FROM posters where status = 'Graduate' and votes = (SELECT MAX(votes) FROM posters where status = 'Graduate');

--STATEMENT readUndergradWinners
SELECT * FROM posters where status = 'Undergraduate' and votes = (SELECT MAX(votes) FROM posters where status = 'Undergraduate');

--STATEMENT getPosters
SELECT * FROM posters;

--STATEMENT getPostersByStatus
SELECT * FROM posters
WHERE status = :status;

--STATEMENT updatePoster
UPDATE posters SET
  id = :id,
  poster_id = :poster_id,
  title = :title,
  email = :email,
  first_name = :first_name,
  last_name = :last_name,
  pin = :pin,
  status = :status,
  department = :department,
  votes = :votes,
  has_voted = :has_voted,
  role = :role
WHERE
  id = :id;

--STATEMENT deletePoster
DELETE FROM posters WHERE id = :id;

--STATEMENT readPosterEmailPin
SELECT * FROM posters WHERE email = :email and pin = :pin;

