--STATEMENT createScore
INSERT INTO SCORE(
    PosterID,
    JudgeID,
    Round,
    research_score,
    comm_score,
    poster_score
)
VALUES(
    :PosterID,
    :JudgeID,
    :Round,
    :research_score,
    :comm_score,
    :poster_score
);

--STATEMENT getScore
SELECT * FROM SCORE WHERE ScoreID = :ScoreID;

--STATEMENT getAllScores
SELECT * FROM SCORE;

--STATEMENT getScoreByID
SELECT * FROM SCORE WHERE PosterID = :PosterID and JudgeID = :JudgeID;

--STATEMENT getScoreByPosterID
SELECT * FROM SCORE WHERE PosterID = :PosterID;

--STATEMENT getScoreByJudgeID
SELECT * FROM SCORE WHERE JudgeID = :JudgeID;

--STATEMENT getScoreByRound
SELECT * from SCORE WHERE Round = :Round;

--STATEMENT updateScore
UPDATE SCORE SET 
    research_score = :research_score,
    comm_score = :comm_score,
    poster_score = :poster_score
WHERE
    PosterID = :PosterID and Round = :Round;

--STATEMENT deleteScorebyPosterID
DELETE FROM SCORE WHERE PosterID=:PosterID;

--STATEMENT deleteScoreByID
DELETE FROM SCORE WHERE ScoreID = :ScoreID;
