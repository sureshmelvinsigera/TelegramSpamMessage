package org.telegram.model;

import java.util.List;

public class Settings {
    private int timeout;
    private String fireFoxProfile;
    private String message;
    private List<String> accounts;
    private boolean accountGenerator;

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

    public boolean isAccountGenerator() {
        return accountGenerator;
    }

    public void setAccountGenerator(boolean accountGenerator) {
        this.accountGenerator = accountGenerator;
    }
}
