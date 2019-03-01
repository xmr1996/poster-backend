package edu.uwm.capstone.model.Author;

import edu.uwm.capstone.model.Poster.Poster;

import java.util.Objects;

public class Author {
    private int AuthorID;
    private String AuthorFirstName;
    private String AuthorLastName;
    private String AuthorEmail;
    private String AuthorStatus;
    private Poster PosterID;

    public Author(String authorFirstName, String authorLastName, String authorEmail, String authorStatus, Poster posterID) {
        AuthorFirstName = authorFirstName;
        AuthorLastName = authorLastName;
        AuthorEmail = authorEmail;
        AuthorStatus = authorStatus;
        PosterID = posterID;
    }

    public int getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(int authorID) {
        AuthorID = authorID;
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

    public String getAuthorEmail() {
        return AuthorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        AuthorEmail = authorEmail;
    }

    public String getAuthorStatus() {
        return AuthorStatus;
    }

    public void setAuthorStatus(String authorStatus) {
        AuthorStatus = authorStatus;
    }

    public Poster getPosterID() {
        return PosterID;
    }

    public void setPosterID(Poster posterID) {
        PosterID = posterID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return AuthorID == author.AuthorID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(AuthorID);
    }

    @Override
    public String toString() {
        return "Author{" +
                "AuthorFirstName='" + AuthorFirstName + '\'' +
                ", AuthorLastName='" + AuthorLastName + '\'' +
                ", AuthorEmail='" + AuthorEmail + '\'' +
                ", AuthorStatus='" + AuthorStatus + '\'' +
                ", PosterID=" + PosterID +
                '}';
    }
}
