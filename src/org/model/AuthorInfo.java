package org.model;

public class AuthorInfo {
    private String authorName;
    private String authorPassword;
    private String authorEmail;
    private int id;

    public AuthorInfo() {
    }

    public AuthorInfo(String authorName, String authorPassword, String authorEmail) {
        this.authorName = authorName;
        this.authorPassword = authorPassword;
        this.authorEmail = authorEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorPassword() {
        return authorPassword;
    }

    public void setAuthorPassword(String authorPassword) {
        this.authorPassword = authorPassword;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    @Override
    public String toString() {
        return "AuthorInfo{" +
                "authorName='" + authorName + '\'' +
                ", authorPassword='" + authorPassword + '\'' +
                ", authorEmail='" + authorEmail + '\'' +
                ", id=" + id +
                '}';
    }
}
