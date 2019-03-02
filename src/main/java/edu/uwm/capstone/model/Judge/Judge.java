package edu.uwm.capstone.model.Judge;

import java.util.Objects;

public class Judge {
    protected Long JudgeID;
    private String JudgeFirstName;
    private String JudgeLastName;
    private String JudgeStatus;
    private String JudgeEmail;

    public Judge(String judgeFirstName, String judgeLastName, String judgeStatus, String judgeEmail) {
        JudgeFirstName = judgeFirstName;
        JudgeLastName = judgeLastName;
        JudgeStatus = judgeStatus;
        JudgeEmail = judgeEmail;
    }

    public Long getJudgeID() {
        return JudgeID;
    }

    public void setJudgeID(Long judgeID) {
        this.JudgeID = judgeID;
    }

    public String getJudgeFirstName() {
        return JudgeFirstName;
    }

    public void setJudgeFirstName(String judgeFirstName) {
        this.JudgeFirstName = judgeFirstName;
    }

    public String getJudgeLastName() {
        return this.JudgeLastName;
    }

    public void setJudgeLastName(String judgeLastName) {
        this.JudgeLastName = judgeLastName;
    }

    public String getJudgeStatus() {
        return JudgeStatus;
    }

    public void setJudgeStatus(String judgeStatus) {
        this.JudgeStatus = judgeStatus;
    }

    public String getJudgeEmail() {
        return JudgeEmail;
    }

    public void setJudgeEmail(String judgeEmail) {
        this.JudgeEmail = judgeEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Judge judge = (Judge) o;
        return JudgeID == judge.JudgeID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(JudgeID);
    }

    @Override
    public String toString() {
        return "Judge{" +
                "JudgeID=" + JudgeID +
                ", JudgeFirstName='" + JudgeFirstName + '\'' +
                ", JudgeLastName='" + JudgeLastName + '\'' +
                ", JudgeStatus='" + JudgeStatus + '\'' +
                ", JudgeEmail='" + JudgeEmail + '\'' +
                '}';
    }
}
