package edu.uwm.capstone.model.Score;


import edu.uwm.capstone.model.Judge.Judge;
import edu.uwm.capstone.model.Poster.Poster;

import java.util.Objects;

public class Score {
    private Poster PosterID;
    private Judge JudgeID;
    private int Round;
    private int Research_Score;
    private int Comm_Score;
    private int Poster_Score;

    public Score(Poster posterID, Judge judgeID, int round) {
        PosterID = posterID;
        JudgeID = judgeID;
        Round = round;
    }

    public Poster getPosterID() {
        return PosterID;
    }

    public void setPosterID(Poster posterID) {
        PosterID = posterID;
    }

    public Judge getJudgeID() {
        return JudgeID;
    }

    public void setJudgeID(Judge judgeID) {
        JudgeID = judgeID;
    }

    public int getRound() {
        return Round;
    }

    public void setRound(int round) {
        Round = round;
    }

    public int getResearch_Score() {
        return Research_Score;
    }

    public void setResearch_Score(int research_Score) {
        Research_Score = research_Score;
    }

    public int getComm_Score() {
        return Comm_Score;
    }

    public void setComm_Score(int comm_Score) {
        Comm_Score = comm_Score;
    }

    public int getPoster_Score() {
        return Poster_Score;
    }

    public void setPoster_Score(int poster_Score) {
        Poster_Score = poster_Score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Round == score.Round &&
                PosterID.equals(score.PosterID) &&
                JudgeID.equals(score.JudgeID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PosterID, JudgeID, Round);
    }

    @Override
    public String toString() {
        return "Score{" +
                "PosterID=" + PosterID +
                ", JudgeID=" + JudgeID +
                ", Round=" + Round +
                ", Research_Score=" + Research_Score +
                ", Comm_Score=" + Comm_Score +
                ", Poster_Score=" + Poster_Score +
                '}';
    }
}
