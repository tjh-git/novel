package org.model;

public class ReaderInfo {
    private String readerName;
    private String readerPassword;
    private String readerEmail;
    private int id;

    public ReaderInfo() {
    }

    public ReaderInfo(String readerName, String readerPassword, String readerEmail) {
        this.readerName = readerName;
        this.readerPassword = readerPassword;
        this.readerEmail = readerEmail;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getReaderPassword() {
        return readerPassword;
    }

    public void setReaderPassword(String readerPassword) {
        this.readerPassword = readerPassword;
    }

    public String getReaderEmail() {
        return readerEmail;
    }

    public void setReaderEmail(String readerEmail) {
        this.readerEmail = readerEmail;
    }
}
