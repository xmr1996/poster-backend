package edu.uwm.capstone.model.Poster;

import edu.uwm.capstone.model.Author.Author;

import java.util.Objects;

public class Poster {
    private int PosterID;
    private String PosterTitle;
    private String Abstract;
    private String PosterDepartment;
    private String PosterLink;
    private String FileName;
    private String PosterPresentationLink;
    private Author AuthorID;

    public Poster(String posterTitle, String anAbstract, String posterDepartment, String posterLink, String fileName, String posterPresentationLink) {
        PosterTitle = posterTitle;
        Abstract = anAbstract;
        PosterDepartment = posterDepartment;
        PosterLink = posterLink;
        FileName = fileName;
        PosterPresentationLink = posterPresentationLink;

    }

    public int getPosterID() {
        return PosterID;
    }

    public void setPosterID(int posterID) {
        PosterID = posterID;
    }

    public String getPosterTitle() {
        return PosterTitle;
    }

    public void setPosterTitle(String posterTitle) {
        PosterTitle = posterTitle;
    }

    public String getAbstract() {
        return Abstract;
    }

    public void setAbstract(String anAbstract) {
        Abstract = anAbstract;
    }

    public String getPosterDepartment() {
        return PosterDepartment;
    }

    public void setPosterDepartment(String posterDepartment) {
        PosterDepartment = posterDepartment;
    }

    public String getPosterLink() {
        return PosterLink;
    }

    public void setPosterLink(String posterLink) {
        PosterLink = posterLink;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getPosterPresentationLink() {
        return PosterPresentationLink;
    }

    public void setPosterPresentationLink(String posterPresentationLink) {
        PosterPresentationLink = posterPresentationLink;
    }



    public Author getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(Author authorID) {
        AuthorID = authorID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poster poster = (Poster) o;
        return PosterID == poster.PosterID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(PosterID);
    }

    @Override
    public String toString() {
        return "Poster{" +
                "PosterID=" + PosterID +
                ", PosterTitle='" + PosterTitle + '\'' +
                ", Abstract='" + Abstract + '\'' +
                ", PosterDepartment='" + PosterDepartment + '\'' +
                ", PosterLink='" + PosterLink + '\'' +
                ", FileName='" + FileName + '\'' +
                ", PosterPresentationLink='" + PosterPresentationLink + '\'' +
                '}';
    }
}
