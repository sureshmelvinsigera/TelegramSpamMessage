package org.telegram.model;

import java.util.List;

public class User {
    private int userId;
    private int timeout;
    private String fireFoxProfile;
    private String message;
    private List<String> accounts;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<String> accounts) {
        this.accounts = accounts;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getFireFoxProfile() {
        return fireFoxProfile;
    }

    public void setFireFoxProfile(String fireFoxProfile) {
        this.fireFoxProfile = fireFoxProfile;
    }
}
