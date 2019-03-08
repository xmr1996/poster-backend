package edu.uwm.capstone.model.Poster;

import java.util.Objects;

public class Poster {
    private String PosterID;
    private String PosterTitle;
    private String AuthorEmail;
    private String AuthorFirstName;
    private String AuthorLastName;
    private String AuthorStatus;
    private String AuthorPin;
    private String AuthorDepartment;

    public String getPosterID() {
        return PosterID;
    }

    public void setPosterID(String posterID) {
        PosterID = posterID;
    }

    public String getPosterTitle() {
        return PosterTitle;
    }

    public void setPosterTitle(String posterTitle) {
        PosterTitle = posterTitle;
    }

    public String getAuthorEmail() {
        return AuthorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        AuthorEmail = authorEmail;
    }

    public String getAuthorFirstName() {
        return AuthorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        AuthorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return AuthorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        AuthorLastName = authorLastName;
    }

    public String getAuthorStatus() {
        return AuthorStatus;
    }

    public void setAuthorStaus(String authorStatus) {
        AuthorStatus = authorStatus;
    }

    public String getAuthorPin() {
        return AuthorPin;
    }

    public void setAuthorPin(String authorPin) {
        AuthorPin = authorPin;
    }

    public String getAuthorDepartment() {
        return AuthorDepartment;
    }

    public void setAuthorDepartment(String authorDepartment) {
        AuthorDepartment = authorDepartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poster poster = (Poster) o;
        return Objects.equals(PosterID, poster.PosterID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PosterID);
    }

    @Override
    public String toString() {
        return "Poster{" +
                "PosterID='" + PosterID + '\'' +
                ", PosterTitle='" + PosterTitle + '\'' +
                ", AuthorEmail='" + AuthorEmail + '\'' +
                ", AuthorFirstName='" + AuthorFirstName + '\'' +
                ", AuthorLastName='" + AuthorLastName + '\'' +
                ", AuthorStatus='" + AuthorStatus + '\'' +
                ", AuthorDepartment='" + AuthorDepartment + '\'' +
                '}';
    }
}
