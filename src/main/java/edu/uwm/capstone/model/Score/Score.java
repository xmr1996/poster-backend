package edu.uwm.capstone.model.Score;

public class Score {
    private String poster_id;
    private Long judge_id;
    private int round;
    private Integer research_score;
    private Integer comm_score;
    private Integer poster_score;
    private Integer total_score;

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

    public Integer getResearch_score() {
        return research_score;
    }

    public void setResearch_score(Integer research_score) {
        this.research_score = research_score;
    }

    public Integer getComm_score() {
        return comm_score;
    }

    public void setComm_score(Integer comm_score) {
        this.comm_score = comm_score;
    }

    public Integer getPoster_score() {
        return poster_score;
    }

    public void setPoster_score(Integer poster_score) {
        this.poster_score = poster_score;
    }

    public Integer getTotal_score() {
        return total_score;
    }

    public void setTotal_score(Integer total_score) {
        this.total_score = total_score;
    }
}
