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

--STATEMENT upsertPoster
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
)
ON DUPLICATE KEY UPDATE
  title = :title,
  email = :email,
  first_name = :first_name,
  last_name = :last_name,
  status = :status,
  pin = :pin,
  department = :department,
  voted_for = :voted_for,
  role = :role;

--STATEMENT readPosterByID
SELECT * FROM posters where poster_id = :poster_id;


--STATEMENT getPosters
SELECT * FROM posters;

--STATEMENT getPostersByStatus
SELECT * FROM posters
WHERE status = :status;

--STATEMENT updatePoster
UPDATE posters SET
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
  poster_id = :poster_id;

--STATEMENT setVote
UPDATE posters SET
  voted_for = :vote
WHERE
  poster_id = :poster_id;

--STATEMENT readPosterEmailPin
SELECT * FROM posters WHERE email = :email and pin = :pin;

--STATEMENT insertAvgR1
UPDATE posters
SET posters.avg_r1 = (SELECT AVG(CAST(total_score AS DOUBLE)) FROM score WHERE score.poster_id = :poster_id)
Where posters.poster_id = :poster_id;

--STATEMENT getTop6PostersR1
SELECT * FROM posters WHERE status = :status
ORDER BY avg_r1 DESC, avg_research_r1 DESC, avg_comm_r1 DESC, avg_pres_r1 DESC LIMIT 6;

--STATEMENT getTop6PostersR2
SELECT * FROM posters WHERE status = :status
ORDER BY avg_r2 DESC, avg_research_r2 DESC, avg_comm_r2 DESC, avg_pres_r2 DESC LIMIT 6;

--STATEMENT insertAvgTotalR1
UPDATE posters
SET posters.avg_r1 =
      (SELECT AVG(CAST(total_score as DECIMAL (12, 2))) AS AVG
       FROM score
       WHERE total_score is not null AND round = 1 AND score.poster_id = posters.poster_id
       group by score.poster_id
        );

--STATEMENT insertAvgTotalR2
UPDATE posters
SET posters.avg_r2 =
(SELECT AVG(CAST(total_score AS DECIMAL (12, 2))) as AVG
  FROM score
  WHERE total_score is not null
  AND round = 2
  AND poster_id =  posters.poster_id
  group by poster_id
);

--STATEMENT insertAvgCommR1
UPDATE posters
SET posters.avg_comm_r1 =
(SELECT AVG(CAST(comm_score AS DECIMAL (12, 2))) as AVG
  FROM score
  WHERE comm_score is not null
  AND round = 1
  AND poster_id =  posters.poster_id
  group by poster_id
);

--STATEMENT insertAvgCommR2
UPDATE posters
SET posters.avg_comm_r2 =
(SELECT AVG(CAST(comm_score AS DECIMAL (12, 2))) as AVG
  FROM score
  WHERE comm_score is not null
  AND round = 2
  AND poster_id =  posters.poster_id
  group by poster_id
);

--STATEMENT insertAvgResearchR1
UPDATE posters
SET posters.avg_research_r1 =
(SELECT AVG(CAST(research_score AS DECIMAL (12, 2))) as AVG
  FROM score
  WHERE research_score is not null
  AND round = 1
  AND poster_id =  posters.poster_id
  group by poster_id
);

--STATEMENT insertAvgResearchR2
UPDATE posters
SET posters.avg_research_r2 =
(SELECT AVG(CAST(research_score AS DECIMAL (12, 2))) as AVG
  FROM score
  WHERE research_score is not null
  AND round = 2
  AND poster_id =  posters.poster_id
  group by poster_id
);

--STATEMENT insertAvgPresR1
UPDATE posters
SET posters.avg_pres_r1 =
(SELECT AVG(CAST(poster_score AS DECIMAL (12, 2))) as AVG
  FROM score
  WHERE poster_score is not null
  AND round = 1
  AND poster_id =  posters.poster_id
  group by poster_id
);

--STATEMENT insertAvgPresR2
UPDATE posters
SET posters.avg_pres_r2 =
(SELECT AVG(CAST(poster_score AS DECIMAL (12, 2))) as AVG
  FROM score
  WHERE poster_score is not null
  AND round = 2
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

--STATEMENT clearPosters
DELETE FROM posters;

--STATEMENT deletePoster
DELETE FROM posters
WHERE poster_id = :poster_id;