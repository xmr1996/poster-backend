package edu.uwm.capstone.model.Score;

public class Score {
    private String poster_id;
    private Long judge_id;
    private int round;
    private int research_score;
    private int comm_score;
    private int poster_score;


    private int total_score;

    public String getPoster_id() {
        return poster_id;
    }

    public void setPoster_id(String poster_id) {
        this.poster_id = poster_id;
    }

    public Long getJudge_id() {
        return judge_id;
    }

    public void setJudge_id(Long judge_id) {
        this.judge_id = judge_id;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getResearch_score() {
        return research_score;
    }

    public void setResearch_score(int research_score) {
        this.research_score = research_score;
    }

    public int getComm_score() {
        return comm_score;
    }

    public void setComm_score(int comm_score) {
        this.comm_score = comm_score;
    }

    public int getPoster_score() {
        return poster_score;
    }

    public void setPoster_score(int poster_score) {
        this.poster_score = poster_score;
    }

    public int getTotal_score() {
        return total_score;
    }

    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }

}
