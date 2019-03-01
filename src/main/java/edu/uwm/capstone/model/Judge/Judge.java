package edu.uwm.capstone.model.Judge;

import java.util.Objects;

public class Judge {
    private int JudgeID;
    private String JudgeFirstName;
    private String JudgeLastName;
    private String JudgeStatus;
    private String JudgeEmail;

    public int getJudgeID() {
        return JudgeID;
    }

    public void setJudgeID(int judgeID) {
        JudgeID = judgeID;
    }

    public String getJudgeFirstName() {
        return JudgeFirstName;
    }

    public void setJudgeFirstName(String judgeFirstName) {
        JudgeFirstName = judgeFirstName;
    }

    public String getJudgeLastName() {
        return JudgeLastName;
    }

    public void setJudgeLastName(String judgeLastName) {
        JudgeLastName = judgeLastName;
    }

    public String getJudgeStatus() {
        return JudgeStatus;
    }

    public void setJudgeStatus(String judgeStatus) {
        JudgeStatus = judgeStatus;
    }

    public String getJudgeEmail() {
        return JudgeEmail;
    }

    public void setJudgeEmail(String judgeEmail) {
        JudgeEmail = judgeEmail;
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
