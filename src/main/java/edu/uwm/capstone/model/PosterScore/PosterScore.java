package edu.uwm.capstone.model.PosterScore;

import java.util.Objects;

public class PosterScore {
    private String title;
    private String email;
    private String first_name;
    private String last_name;
    private String status;
    private int pin;
    private String department;
    private String role;
    private String poster_id;
    private Long judge_id;
    private int round;
    private Integer research_score;
    private Integer comm_score;
    private Integer poster_score;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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
}
