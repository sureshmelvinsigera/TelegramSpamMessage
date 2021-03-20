package org.telegram.event;

import org.telegram.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SetProperties {

    User user = new User();

    InputStream propertiesStream;
    InputStream messageStream;
    InputStream accountsStream;

    public void loadProperties() {
        try {
            propertiesStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
            messageStream = this.getClass().getClassLoader().getResourceAsStream("message");
            accountsStream = this.getClass().getClassLoader().getResourceAsStream("accounts");
            setPropertiesStream(propertiesStream, accountsStream, messageStream);
        } catch (Exception e) {
            System.out.println("Couldn't load a file: " + e.getMessage());
        }
    }

    private void setPropertiesStream(InputStream propertiesStream, InputStream accountsStream, InputStream messageStream) throws IOException {
        ArrayList<String> accounts = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(propertiesStream));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("timeout=")) {
                user.setTimeout(Integer.parseInt(line.replaceAll("timeout=", "")));
            }
            if (line.contains("firefoxprofile=")) {
                user.setFireFoxProfile(line.replaceAll("firefoxprofile=", ""));
            }
        }
        reader = new BufferedReader(new InputStreamReader(accountsStream));
        while ((line = reader.readLine()) != null) {
            accounts.add(line);
        }
        user.setAccounts(accounts);
        reader = new BufferedReader(new InputStreamReader(messageStream));
        user.setMessage("");
        while ((line = reader.readLine()) != null) {
            user.setMessage(user.getMessage() + line);
        }
    }

//    public void show() {
//        System.out.println(user.getTimeout());
//        System.out.println(user.getMessage());
//        System.out.println(user.getFireFoxProfile());
//        System.out.println(user.getAccounts());
//    }

}
