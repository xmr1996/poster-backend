--STATEMENT createScore
INSERT INTO score(
    poster_id,
    judge_id,
    round,
    research_score,
    comm_score,
    poster_score
)
VALUES(
    :poster_id,
    :judge_id,
    :round,
    :research_score,
    :comm_score,
    :poster_score
);

--STATEMENT readScore
SELECT * FROM scores WHERE id = :id;

--STATEMENT getAllScores
SELECT * FROM scores;

--STATEMENT getScoreByID
SELECT * FROM scores WHERE poster_id = :poster_id and judge_id = :judge_id;

--STATEMENT getScoreByPosterID
SELECT * FROM scores WHERE poster_id = :poster_id;

--STATEMENT getScoreByJudgeID
SELECT * FROM scores WHERE judge_id = :judge_id;

--STATEMENT getScoreByRound
SELECT * from scores WHERE round = :round;

--STATEMENT updateScore
UPDATE scores SET
    research_score = :research_score,
    comm_score = :comm_score,
    poster_score = :poster_score
WHERE
    poster_id = :poster_id and round = :round;

--STATEMENT deleteScorebyPosterID
DELETE FROM scores WHERE poster_id = :poster_id;


--STATEMENT deleteScore
DELETE FROM scores where id = :id;
