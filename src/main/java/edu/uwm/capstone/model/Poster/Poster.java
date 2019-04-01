package edu.uwm.capstone.model.Poster;

import java.util.Objects;

public class Poster {
    protected Long id;
    private String poster_id;
    private String title;
    private String email;
    private String first_name;
    private String last_name;
    private String status;
    private String pin;
    private String department;
    private String voted_for;
    private String role;
    private double avg_r1;
    private double avg_r2;
    private double avg_comm_r1;
    private double avg_comm_r2;
    private double avg_research_r1;
    private double avg_research_r2;
    private double avg_pres_r1;
    private double avg_pres_r2;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPoster_id() {
        return poster_id;
    }

    public void setPoster_id(String poster_id) {
        this.poster_id = poster_id;
    }

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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
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

    public void setRole(String role){ this.role = role; }

    public double getAvg_r2() {
        return avg_r2;
    }

    public void setAvg_r2(double avg_r2) {
        this.avg_r2 = avg_r2;
    }

    public double getAvg_r1() {
        return avg_r1;
    }

    public void setAvg_r1(double avg_r1) {
        this.avg_r1 = avg_r1;
    }

    public String getVoted_for() {
        return voted_for;
    }

    public void setVoted_for(String voted_for) {
        this.voted_for = voted_for;
    }

    public double getAvg_comm_r1() {
        return avg_comm_r1;
    }

    public void setAvg_comm_r1(double avg_comm_r1) {
        this.avg_comm_r1 = avg_comm_r1;
    }

    public double getAvg_comm_r2() {
        return avg_comm_r2;
    }

    public void setAvg_comm_r2(double avg_comm_r2) {
        this.avg_comm_r2 = avg_comm_r2;
    }

    public double getAvg_research_r1() {
        return avg_research_r1;
    }

    public void setAvg_research_r1(double avg_research_r1) {
        this.avg_research_r1 = avg_research_r1;
    }

    public double getAvg_research_r2() {
        return avg_research_r2;
    }

    public void setAvg_research_r2(double avg_research_r2) {
        this.avg_research_r2 = avg_research_r2;
    }

    public double getAvg_pres_r1() {
        return avg_pres_r1;
    }

    public void setAvg_pres_r1(double avg_pres_r1) {
        this.avg_pres_r1 = avg_pres_r1;
    }

    public double getAvg_pres_r2() {
        return avg_pres_r2;
    }

    public void setAvg_pres_r2(double avg_pres_r2) {
        this.avg_pres_r2 = avg_pres_r2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poster poster = (Poster) o;
        return Objects.equals(id, poster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
