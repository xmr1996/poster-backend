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

--STATEMENT insertAvgR1
UPDATE posters
SET posters.avg_r1 = (SELECT AVG(CAST(total_score AS DOUBLE)) FROM score WHERE SCORE.poster_id = :poster_id)
Where posters.poster_id = :poster_id;

--STATEMENT getTop6Posters
SELECT * FROM posters WHERE status = :status ORDER BY avg_r1 DESC LIMIT 6;

--STATEMENT test
UPDATE posters
SET posters.avg_r1 =
(SELECT AVG(CAST(total_score AS DOUBLE)) as AVG
  FROM score
  WHERE total_score is not NULL
  AND round = :round
  AND poster_id =  posters.poster_id
  group by poster_id
);
