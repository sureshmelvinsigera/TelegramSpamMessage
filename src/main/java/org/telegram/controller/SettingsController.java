package org.telegram.controller;

import org.telegram.model.Settings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SettingsController {

    InputStream propertiesStream;
    InputStream messageStream;
    InputStream accountsStream;

    Settings settings = new Settings();

    public Settings loadProperties() {
        try {
            propertiesStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
            messageStream = this.getClass().getClassLoader().getResourceAsStream("message");
            accountsStream = this.getClass().getClassLoader().getResourceAsStream("accounts");
            setPropertiesStream(propertiesStream, accountsStream, messageStream);
        } catch (Exception e) {
            System.out.println("Couldn't load a file: " + e.getMessage());
        }
        return settings;
    }

    private void setPropertiesStream(InputStream propertiesStream, InputStream accountsStream, InputStream messageStream) throws IOException {
        ArrayList<String> accounts = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(propertiesStream));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains("timeout=")) {
                settings.setTimeout(Integer.parseInt(line.replaceAll("timeout=", "")));
            }
            if (line.contains("firefoxprofile=")) {
                settings.setFireFoxProfile(line.replaceAll("firefoxprofile=", ""));
            }
            if (line.contains("accountgenerator=")) {
                settings.setAccountGenerator(Boolean.parseBoolean(line.replaceAll("accountgenerator=", "")));
            }
        }
        if (settings.isAccountGenerator()) {
            settings.setAccounts(accountGenerator());
        } else {
            reader = new BufferedReader(new InputStreamReader(accountsStream));
            while ((line = reader.readLine()) != null) {
                accounts.add(line);
            }
            settings.setAccounts(accounts);
        }
        reader = new BufferedReader(new InputStreamReader(messageStream));
        settings.setMessage("");
        while ((line = reader.readLine()) != null) {
            settings.setMessage(settings.getMessage() + line);
        }
    }

    private List accountGenerator() {
        ArrayList<String> list = new ArrayList<>();
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 8;
        Random random = new Random();
        for (int x = 1; x <= 50; x++) {
            StringBuilder buffer = new StringBuilder(targetStringLength);
            for (int i = 0; i < targetStringLength; i++) {
                int randomLimitedInt = leftLimit + (int)
                        (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            list.add("@" + buffer.toString());
        }
        return list;
    }
}
