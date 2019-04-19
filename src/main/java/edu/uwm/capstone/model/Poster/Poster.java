package edu.uwm.capstone.model.Poster;

import java.util.Objects;

public class Poster {
    private String posterId;
    private String title;
    private String email;
    private String firstName;
    private String lastName;
    private String status;
    private String pin;
    private String department;
    private String votedFor;
    private String role;
    private Double avgR1;
    private Double avgR2;
    private Double avgCommR1;
    private Double avgCommR2;
    private Double avgResearchR1;
    private Double avgResearchR2;
    private Double avgPresR1;
    private Double avgPresR2;

    public String getPosterId() { return posterId; }

    public void setPosterId(String posterId) {
        this.posterId = posterId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public void setRole(String role) { this.role = role; }

    public String getVotedFor() {
        return votedFor;
    }

    public void setVotedFor(String votedFor) {
        this.votedFor = votedFor;
    }

    public Double getAvgR1() {
        return avgR1;
    }

    public void setAvgR1(Double avgR1) {
        this.avgR1 = avgR1;
    }

    public Double getAvgR2() {
        return avgR2;
    }

    public void setAvgR2(Double avgR2) {
        this.avgR2 = avgR2;
    }

    public Double getAvgCommR1() {
        return avgCommR1;
    }

    public void setAvgCommR1(Double avgCommR1) {
        this.avgCommR1 = avgCommR1;
    }

    public Double getAvgCommR2() {
        return avgCommR2;
    }

    public void setAvgCommR2(Double avgCommR2) {
        this.avgCommR2 = avgCommR2;
    }

    public Double getAvgResearchR1() {
        return avgResearchR1;
    }

    public void setAvgResearchR1(Double avgResearchR1) {
        this.avgResearchR1 = avgResearchR1;
    }

    public Double getAvgResearchR2() {
        return avgResearchR2;
    }

    public void setAvgResearchR2(Double avgResearchR2) {
        this.avgResearchR2 = avgResearchR2;
    }

    public Double getAvgPresR1() {
        return avgPresR1;
    }

    public void setAvgPresR1(Double avgPresR1) {
        this.avgPresR1 = avgPresR1;
    }

    public Double getAvgPresR2() {
        return avgPresR2;
    }

    public void setAvgPresR2(Double avgPresR2) {
        this.avgPresR2 = avgPresR2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poster poster = (Poster) o;
        return Objects.equals(posterId, poster.posterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posterId);
    }
}
