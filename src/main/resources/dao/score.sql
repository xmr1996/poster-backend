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
SELECT * FROM score WHERE id = :id;

--STATEMENT readAllScores
SELECT * FROM score;

--STATEMENT getScoreByID
SELECT * FROM score WHERE poster_id = :poster_id and judge_id = :judge_id;

--STATEMENT getScoreByPosterID
SELECT * FROM score WHERE poster_id = :poster_id;

--STATEMENT getScoreByJudgeID
SELECT * FROM score WHERE judge_id = :judge_id;

--STATEMENT getScoreByRound
SELECT * from score WHERE round = :round;

--STATEMENT updateScore
UPDATE score SET
    research_score = :research_score,
    comm_score = :comm_score,
    poster_score = :poster_score
WHERE
    poster_id = :poster_id and round = :round and judge_id = :judge_id;

--STATEMENT deleteScorebyPosterID
DELETE FROM score WHERE poster_id = :poster_id;


--STATEMENT deleteScore
DELETE FROM score where id = :id;

--STATEMENT getAllAssignments
SELECT id, poster_id, judge_id from score;
