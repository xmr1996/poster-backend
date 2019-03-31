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
  voted_for,
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
  :voted_for,
  :role
);

--STATEMENT readPoster
SELECT * FROM posters WHERE id = :id;

--STATEMENT readPosterByID
SELECT * FROM posters where poster_id = :poster_id;


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
  voted_for = :voted_for,
  role = :role
WHERE
  id = :id;

--STATEMENT setVote
UPDATE posters SET
  voted_for = :vote
WHERE
  poster_id = :poster_id;

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

--STATEMENT readPostersByStatus
SELECT * FROM posters where status = :status;

--STATEMENT readVotesByStatus
SELECT    voted_for AS poster_id, COUNT(*) AS votes
FROM      posters
WHERE     status = :status
          AND  voted_for is not null
GROUP BY  voted_for
ORDER BY  votes DESC;